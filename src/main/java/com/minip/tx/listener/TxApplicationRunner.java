package com.minip.tx.listener;

import com.minip.tx.scheduler.BalanceTimer;
import com.minip.tx.scheduler.BonusPoolTimer;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TxApplicationRunner implements ApplicationRunner{
    private final static Logger logger = LoggerFactory.getLogger(TxApplicationRunner.class);
    @Autowired
    private Scheduler scheduler;
    @Override
    public void run(ApplicationArguments args) {
        /*logger.info("启动定时任务调度器");
        try{
            buildBonusPoolTimer();
        }catch(ObjectAlreadyExistsException e){
            //e.printStackTrace();
            logger.info("-- 定时任务已存在 --");
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
    public void buildBonusPoolTimer()throws Exception{
        //任务名称
        //String name = "测试"+ UUID.randomUUID().toString();
        String name = "init";
        //任务所属分组
        String group = "奖金池初始化";
        //可以设置重启后不执行错过的任务
        //CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 42 10 * * ?").withMisfireHandlingInstructionDoNothing();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 * * ?").withMisfireHandlingInstructionDoNothing();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(BonusPoolTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void buildBalanceTimer()throws Exception{
        //任务名称
        String name = "balance";
        //任务所属分组
        String group = "奖励结算";
        //可以设置重启后不执行错过的任务
        //CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 42 10 * * ?").withMisfireHandlingInstructionDoNothing();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 2 * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(BalanceTimer.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
