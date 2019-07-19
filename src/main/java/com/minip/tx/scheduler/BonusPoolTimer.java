package com.minip.tx.scheduler;

import com.minip.tx.service.BusinessService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class BonusPoolTimer extends QuartzJobBean {
    private final static Logger logger = LoggerFactory.getLogger(BonusPoolTimer.class);

    @Autowired
    private BusinessService businessService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("初始化明日奖金池，任务时间：{}",new Date());
        businessService.initBonusPool();
    }
}
