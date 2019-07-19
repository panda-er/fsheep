package com.minip.tx.service.impl;

import com.minip.tx.dao.*;
import com.minip.tx.dao.bean.*;
import com.minip.tx.service.BusinessService;
import com.minip.tx.service.UserInfoService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.KeyGenerator;
import com.minip.tx.utils.Result;
import com.minip.tx.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BusinessServiceImpl implements BusinessService {
    private final static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    private final static BigDecimal TWO_YUAN = new BigDecimal(2.00);

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private AccountAlterInfoDao accountAlterInfoDao;
    @Autowired
    private SportInfoDao sportInfoDao;
    @Autowired
    private SportOverviewDao sportOverviewDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoService userInfoService;


    @Override
    public Result login(String openId, String avatarUrl, String nickname) {
        //重新授权后，触发登录按钮：检查用户是否已存在，已存在则更新头像；不存在则注册
        UserInfo userExist;
        try{
            userExist = userInfoDao.checkExist(openId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Fail to login/register, cause: fail to heck whether the user exists");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        if(userExist != null){
            logger.info("User already exists: "+ userExist.toString());
            //存在则更新头像和昵称
            UserInfo userInfo = new UserInfo();
            userInfo.setOpenId(openId);
            userInfo.setAvatarUrl(avatarUrl);
            userInfo.setNickname(nickname);
            userInfo.setUpdateTime(TimeUtils.getCurrentTimeStamp());
            try{
                userInfoDao.updatePhotoAndNickname(userInfo);
            }catch(Exception e){
                e.printStackTrace();
                logger.error("-- Fail to login/register --");
                return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
            }
            logger.info("-- Succeed to login/register --");
            return Result.ok();
        }else{
            //用户不存在，注册
            Result result = userInfoService.register(openId, avatarUrl, nickname);
            return result;
        }
    }

    @Override
    public Result getBonus(String day){
        String key = KeyGenerator.bonusByDay(day);
        String gameBonus;
        try{
            gameBonus = stringRedisTemplate.opsForValue().get(key);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Fail to query bonus pool --");
            return Result.error(ErrorTypeEnum.REDIS_OP_FAILED);
        }
        logger.info("day: " + day + ";" + "bonus: "+ gameBonus);
        logger.info("-- Succeed to query bonus pool --");
        return Result.ok(gameBonus);
    }

    @Override
    @Transactional
    public Result attendTomSport(String openId, String day) {
        //Mysql事务：每日的参赛状态记录、账户余额、账户变更记录
        BigDecimal accountNum;
        Timestamp ts = TimeUtils.getCurrentTimeStamp();
        try{
            //账户余额减2
            accountNum = accountInfoDao.queryAccountNum(openId);
            logger.info("Current account balance: "+ accountNum.toString());
            //余额是否不足，前端需要做一次判断
            int compare = accountNum.compareTo(TWO_YUAN);
            if(compare == -1){
                logger.error("Account balance not enough");
                return Result.error(ErrorTypeEnum.ACCOUNT_NOT_ENOUGH);
            }
            accountNum = accountNum.subtract(TWO_YUAN);
            accountInfoDao.updateAccountNumByUser(openId, accountNum);
            logger.info("Account balance decrease result: "+ accountNum);
            //账户变更记录
            AccountAlterationVo accountAlterationVo = new AccountAlterationVo();
            accountAlterationVo.setOpenId(openId);
            accountAlterationVo.setAlterMoney(TWO_YUAN);
            accountAlterationVo.setAlterType(ALTER_TYPE_ATTEND);
            accountAlterationVo.setAlterTime(ts);
            accountAlterInfoDao.addAlterInfo(accountAlterationVo);
            logger.info("Add account alter info: "+ accountAlterationVo.toString());
            //新增参赛记录状态
            SportInfoVo sportInfoVo = new SportInfoVo();
            sportInfoVo.setDay(day);
            sportInfoVo.setOpenId(openId);
            sportInfoVo.setOutput(TWO_YUAN);
            sportInfoVo.setStatus(ATTEND_TO_ACHIEVE_INT);
            sportInfoDao.addSportInfo(sportInfoVo);
            logger.info("Add sport info: "+ sportInfoVo.toString());
            //参赛次数加 1
            sportOverviewDao.increJoinDays(openId);
            logger.info("Succeed to update sport_overview_info: joinDays++");
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("-- Fail to attend tomorrow's sport,cause: DB operation failed --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        //奖金池金额加 2
        String bonusKey = KeyGenerator.bonusByDay(day);
        try{
            stringRedisTemplate.opsForValue().increment(bonusKey, 2);
            logger.info("Bonus pool + 2");
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("-- Fail to attend tomorrow's sport,cause: Redis operation failed --");
            return Result.error(ErrorTypeEnum.REDIS_OP_FAILED);
        }
        logger.info("-- Succeed to attend tomorrow's sport --");
        return Result.ok(accountNum);
    }

    @Override
    @Transactional
    public Result submissionToday(String openId, String day, int steps) {
        //mysql:参赛记录状态更新、运动总览更新
        Timestamp ts = TimeUtils.getCurrentTimeStamp();
        try{
            //参赛记录状态更新
            SportInfoVo sportInfoVo = new SportInfoVo();
            sportInfoVo.setDay(day);
            sportInfoVo.setOpenId(openId);
            sportInfoVo.setStepsSubmit(steps);
            sportInfoVo.setTimeSubmit(ts);
            sportInfoVo.setStatus(ACHIEVE_TO_ACCOUNT_INT);
            int submit = sportInfoDao.submitSport(sportInfoVo);
            //问题：除了异常捕捉，是否需要再判断int结果0/1
            logger.info("Operation of updating today's sport status,result: "+ submit);
            //运动总览更新
            int increVicDays = sportOverviewDao.increVicDays(openId);
            logger.info("Operation of victoryDays++, result："+ increVicDays);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("-- Fail to submit today's steps,cause: DB operation failed --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        //redis:完成人数、状态删除
        String achieveKey = KeyGenerator.keyOfAchieveSet(day);
        String statusKey = KeyGenerator.keyOfStatus(openId, day);
        try{
            stringRedisTemplate.opsForSet().add(achieveKey, openId);
            stringRedisTemplate.delete(statusKey);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("achieveSet add failed or sport status delete failed");
            logger.error("-- Fail to submit today's steps --");
            return  Result.error(ErrorTypeEnum.REDIS_OP_FAILED);
        }
        logger.info("-- Succeed to submit today's steps --");
        return Result.ok();
    }

    @Override
    public void initBonusPool() {
        //0点初始化明日奖金池
        String day = TimeUtils.getDay(TimeUtils.TOMORROW);
        String bonusKey = KeyGenerator.bonusByDay(day);
        try{
            stringRedisTemplate.opsForValue().set(bonusKey, "0");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Fail to init bonus pool --");
        }
        logger.info("-- Succeed to init bonus pool --");
    }

    @Override
    public Result querySportStatusByUser(String openId, String day) {
        String result;
        String statusKey = KeyGenerator.keyOfStatus(openId, day);
        SportInfo sportInfo;
        //读数据，无对应的key，不报异常，结果为空
        try{
            result = stringRedisTemplate.opsForValue().get(statusKey);
            //如果map为空或者无指定日期状态，则都要到mysql里读
            if(result == null){
                sportInfo = sportInfoDao.getSportInfo(openId, day);
                if(sportInfo == null){
                    result = BEFORE_ATTEND;
                }else{
                    //判断status == 3的时候结果为结算的红包
                    if(sportInfo.getStatus() == 3) {
                        result = sportInfo.getRedPacket();
                    }else{
                        result = sportInfo.getStatus().toString();
                    }
                    stringRedisTemplate.opsForValue().set(statusKey, result);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Fail to query sport status,cause: Redis operation failed --");
            return Result.error(ErrorTypeEnum.REDIS_OP_FAILED);
        }
        logger.info("-- Succeed to query sport status --");
        return Result.ok(result);
    }

    //对完成比赛的人进行结算
    @Override
    @Transactional
    public Result transToDatabase() {
        //获取昨天的日期
        String yesterday = TimeUtils.getDay(TimeUtils.YESTERDAY);
        //获取bonus-key
        String bonusKey = KeyGenerator.bonusByDay(yesterday);
        //获取昨日奖金池金额
        String yesterdayBonus = stringRedisTemplate.opsForValue().get(bonusKey);
        //获取昨日完成比赛的人数
        String achieveSetKey = KeyGenerator.keyOfAchieveSet(yesterday);
        logger.info(achieveSetKey);
        long achieveNum = getAchieveNum(yesterday);
        logger.info(String.valueOf(achieveNum));
        //计算平均奖金
        BigDecimal avgBonus;
        try{
            avgBonus = countAvgBonus(yesterdayBonus, achieveNum);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Decimal average error");
            logger.info("-- Fail to settle account --");
            return Result.error(ErrorTypeEnum.DECIMAL_ERROR);
        }

        //循环spop操作
        String openId = stringRedisTemplate.opsForSet().pop(achieveSetKey);
        while(openId != null){
            //事务操作：账户操作、账户变更记录、运动详情、运动总览
            settleAccounts(openId, yesterday, avgBonus);
            openId = stringRedisTemplate.opsForSet().pop(achieveSetKey);
        }
        logger.info("-- Succeed to settle all users' accounts --");
        return Result.ok();
    }
    //获取昨日比赛完成人数
    private long getAchieveNum(String day){
        String key = KeyGenerator.keyOfAchieveSet(day);
        long achieveNum = 0;
        try{
            achieveNum = stringRedisTemplate.opsForSet().size(key);
        }catch(Exception e){
            e.printStackTrace();
            //mysql查询完成人数
        }
        return achieveNum;
    }
    //计算平均奖金
    private BigDecimal countAvgBonus(String bonusStr, long numStr)throws Exception{
        BigDecimal bonus = new BigDecimal(bonusStr);
        BigDecimal num = new BigDecimal(numStr);
        BigDecimal avgBonus = bonus.divide(num).setScale(2, BigDecimal.ROUND_HALF_UP);
        return avgBonus;
    }
    //账户操作、账户变更记录、运动详情、运动总览
    public void settleAccounts(String openId, String day, BigDecimal avgBonus){
        logger.info("Start to settle account of user: " + openId);
        AccountAlterationVo accountAlterationVo = new AccountAlterationVo();
        try{
            //更新账户余额
            int aa = accountInfoDao.increAccountNumByUser(openId, avgBonus);
            logger.info("Operation of updating account balance,result: "+ aa);
            //生成账户变更记录:alter_type = 1  是否可以用享元模式或者原型模式
            accountAlterationVo.setOpenId(openId);
            accountAlterationVo.setAlterMoney(avgBonus);
            accountAlterationVo.setAlterType(ALTER_TYPE_BONUS);
            accountAlterationVo.setAlterTime(TimeUtils.getCurrentTimeStamp());
            int bb = accountAlterInfoDao.addAlterInfo(accountAlterationVo);
            logger.info("Operation of adding account alteration info,result: "+ bb);
            //更新运动详情的状态和红包
            int cc = sportInfoDao.accountSport(openId, day, avgBonus);
            logger.info("Operation of updating everyday_sport_info,result: "+ cc);
            //用户结算成功
            logger.info("Succeed to settle account of user--"+ openId);
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            logger.error("Fail to settle account of user: "+ openId);
        }
    }
}
