package com.minip.tx.scheduler;

import com.minip.tx.service.BusinessService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class BalanceTimer extends QuartzJobBean {
    private final static Logger logger = LoggerFactory.getLogger(BalanceTimer.class);
    @Autowired
    private BusinessService businessService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Job---balance account,job time:{}", new Date());
        businessService.transToDatabase();
    }
}
