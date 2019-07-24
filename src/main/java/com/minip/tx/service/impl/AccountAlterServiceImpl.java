package com.minip.tx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minip.tx.dao.AccountAlterInfoDao;
import com.minip.tx.dao.bean.AccountAlteration;
import com.minip.tx.service.AccountAlterService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountAlterServiceImpl implements AccountAlterService {
    private final static Logger logger = LoggerFactory.getLogger(AccountAlterServiceImpl.class);
    @Autowired
    private AccountAlterInfoDao accountAlterInfoDao;
    @Override
    public Result getAlterList(String openId, int pageNow, int pageSize) {
        pageNow = pageNow == 0 ? 1 : pageNow;
        pageSize = pageSize == 0 ? 20 : pageSize;
        List<AccountAlteration> accountAlterations;
        try{
            PageHelper.startPage(pageNow, pageSize);
            accountAlterations = accountAlterInfoDao.getAlterInfoList(openId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("-- Failed to get account alterationInfo List --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("-- Succeed to get account alterationInfo List --");
        PageInfo pageInfo = new PageInfo(accountAlterations);
        return Result.ok(pageInfo);
    }
}
