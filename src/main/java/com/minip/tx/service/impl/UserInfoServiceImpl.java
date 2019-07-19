package com.minip.tx.service.impl;

import com.minip.tx.dao.AccountInfoDao;
import com.minip.tx.dao.SportOverviewDao;
import com.minip.tx.dao.bean.AccountInfo;
import com.minip.tx.dao.UserInfoDao;
import com.minip.tx.dao.bean.AccountInfoVo;
import com.minip.tx.dao.bean.SportOverviewInfo;
import com.minip.tx.dao.bean.UserInfo;
import com.minip.tx.service.UserInfoService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import com.minip.tx.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private SportOverviewDao sportOverviewDao;
    @Override
    public Result getUserInfo(String openId) {
        UserInfo userInfo;
        try{
            userInfo = userInfoDao.getUserInfoByOpenId(openId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Fail to get user info --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("UserInfo: "+ userInfo.toString());
        logger.info("-- Succeed to get user info --");
        return Result.ok(userInfo);
    }

    @Override
    @Transactional
    public Result register(String openId, String avatarUrl, String nickname) {
        Timestamp ts = TimeUtils.getCurrentTimeStamp();
        UserInfo userInfo = new UserInfo();
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        SportOverviewInfo sportOverviewInfo = new SportOverviewInfo();
        logger.info("-- Start to register user info --");
        try{
            //user_info新增用户
            userInfo.setOpenId(openId);
            userInfo.setAvatarUrl(avatarUrl);
            userInfo.setNickname(nickname);
            userInfo.setLevel(0);
            userInfo.setCreateTime(ts);
            userInfo.setUpdateTime(ts);
            int register = userInfoDao.register(userInfo);
            logger.info("Add user_info result: "+ register);
            //account_info新增账户
            BigDecimal zero =  new BigDecimal(0);
            accountInfoVo.setOpenId(openId);
            accountInfoVo.setAccountBalance(zero);
            accountInfoVo.setWithdrawCash(zero);
            accountInfoVo.setCreateTime(ts);
            accountInfoVo.setUpdateTime(ts);
            int account = accountInfoDao.addAccountInfo(accountInfoVo);
            logger.info("Add account_info result: "+ account);
            //sport_overview_info新增运功总览
            sportOverviewInfo.setOpenId(openId);
            sportOverviewInfo.setJoinDays(0);
            sportOverviewInfo.setVictoryDays(0);
            sportOverviewInfo.setCreateTime(ts);
            sportOverviewInfo.setUpdateTime(ts);
            int sportOverview = sportOverviewDao.addSportOverviewInfo(sportOverviewInfo);
            logger.info("Add sport_overview_info result: "+ sportOverview);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.info("-- Fail to register user --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("-- Succeed to register user info --");
        return Result.ok();
    }
}
