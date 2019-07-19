package com.minip.tx.service.impl;

import com.minip.tx.dao.SportOverviewDao;
import com.minip.tx.dao.bean.SportOverviewInfo;
import com.minip.tx.service.SportOverviewService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportOverviewServiceImpl implements SportOverviewService {
    private final static Logger logger = LoggerFactory.getLogger(SportOverviewServiceImpl.class);

    @Autowired
    private SportOverviewDao sportOverviewDao;

    @Override
    public Result getOverviewByUser(String openId) {
        SportOverviewInfo sportOverviewInfo;
        try{
            sportOverviewInfo = sportOverviewDao.getOverviewByUser(openId);
        }catch(Exception e){
            e.printStackTrace();
            logger.info("-- Fail to get sport overview info --");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("-- Succeed to get sport overview info --");
        return Result.ok(sportOverviewInfo);
    }
}
