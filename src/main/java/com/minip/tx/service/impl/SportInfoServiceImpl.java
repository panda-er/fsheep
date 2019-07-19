package com.minip.tx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.minip.tx.dao.SportInfoDao;
import com.minip.tx.dao.bean.SportInfo;
import com.minip.tx.service.SportInfoService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportInfoServiceImpl implements SportInfoService{
    private final static Logger logger = LoggerFactory.getLogger(SportInfoServiceImpl.class);
    @Autowired
    private SportInfoDao sportInfoDao;
    @Override
    public Result getSportListByUser(String openId, int pageNow, int pageSize){
        pageNow = pageNow == 0 ? 1 : pageNow;
        pageSize = pageSize == 0 ? 20 : pageSize;
        List<SportInfo> sportInfos;
        try{
            PageHelper.startPage(pageNow, pageSize);
            sportInfos = sportInfoDao.getSportInfoListByUser(openId);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("--Fail to get sportInfo list --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("-- Succeed to get sportInfo list --");
        PageInfo pageInfo = new PageInfo<SportInfo>(sportInfos);
        return Result.ok(pageInfo);
    };
}
