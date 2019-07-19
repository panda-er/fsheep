package com.minip.tx.service.impl;

import com.minip.tx.controller.OpinionController;
import com.minip.tx.dao.OpinionInfoDao;
import com.minip.tx.dao.bean.OpinionInfo;
import com.minip.tx.service.OpinionInfoService;
import com.minip.tx.utils.ErrorTypeEnum;
import com.minip.tx.utils.Result;
import com.minip.tx.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OpinionInfoServiceImpl implements OpinionInfoService {

    private final static Logger logger = LoggerFactory.getLogger(OpinionInfoServiceImpl.class);

    @Autowired
    private OpinionInfoDao opinionInfoDao;
    @Override
    public Result submitOpinion(String openId, String nickname, String mail, String content) {
        Timestamp submitTime = TimeUtils.getCurrentTimeStamp();
        OpinionInfo opinionInfo = new OpinionInfo();
        opinionInfo.setOpenId(openId);
        opinionInfo.setNickname(nickname);
        opinionInfo.setMail(mail);
        opinionInfo.setContent(content);
        opinionInfo.setSubmitTime(submitTime);
        int result = 0;
        try{
            result = opinionInfoDao.addOpinion(opinionInfo);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Fail to submit opinion");
            return Result.error(ErrorTypeEnum.DB_OPERATION_FAILED);
        }
        logger.info("-- Succeed to submit opinion --");
        return Result.ok(result);
    }
}
