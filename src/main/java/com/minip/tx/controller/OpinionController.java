package com.minip.tx.controller;

import com.minip.tx.service.OpinionInfoService;
import com.minip.tx.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/opinion")
@Api(value = "用户意见")
public class OpinionController {
    private final static Logger logger = LoggerFactory.getLogger(OpinionController.class);
    @Autowired
    private OpinionInfoService opinionInfoService;

    @RequestMapping(value = "/submitOpinion", method = RequestMethod.GET)
    @ApiOperation(value = "用户提交意见")
    public Result submitOpinion(@RequestParam("openId")String openId, @RequestParam("nickname")String nickname,
                                @RequestParam("mail")String mail, @RequestParam("content")String content){
        logger.info("-- Start to submit opinion --");
        Result result = opinionInfoService.submitOpinion(openId, nickname, mail, content);
        return result;
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test()throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("col1", "1");
        map.put("col2", "2");


        String day = "2018-03-01";
        Date dayTime = tran2Date(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayTime);
        calendar.add(Calendar.DATE, 1);
        day = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        return Result.ok(day);

    }

    private Date tran2Date(String day){
        try{
            Date indexDay = new SimpleDateFormat("yyyy-MM-dd").parse(day);//定义起始日期
            return indexDay;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
