package com.minip.tx.service.impl;

import com.minip.tx.dao.AccountInfoDao;
import com.minip.tx.dao.bean.AccountInfo;
import com.minip.tx.service.AccountInfoService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    private final static Logger logger = LoggerFactory.getLogger(AccountInfoServiceImpl.class);

    @Autowired
    private AccountInfoDao accountInfoDao;
    @Override
    public Result getUserAccount(String openId) {
        AccountInfo accountInfo;
        try{
            accountInfo = accountInfoDao.getUserAccountByOpenId(openId);
        }catch(Exception e){
            logger.info("-- Fail to get account info --");
            e.printStackTrace();
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("AccountInfo: "+ accountInfo.toString());
        logger.info("-- Succeed to get account info --");
        return Result.ok(accountInfo);
    }
}
