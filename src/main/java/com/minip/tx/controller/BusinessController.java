package com.minip.tx.controller;

import com.minip.tx.service.BusinessService;
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

@RestController
@RequestMapping(value = "/business")
@Api(value = "业务处理",tags = {"business-op"})
public class BusinessController {
    private final static Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private BusinessService businessService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result login(@RequestParam("openId")String openId, @RequestParam("avatarUrl")String avatarUrl,
                        @RequestParam("nickname")String nickname){
        logger.info("-- Start to login/register --");
        Result result = businessService.login(openId, avatarUrl, nickname);
        return result;
    }

    @RequestMapping(value = "/getBonus", method = RequestMethod.GET)
    @ApiOperation(value = "根据日期查询奖金池")
    public Result getBonus(@RequestParam("day")String day){
        logger.info("-- Start to query bonus pool --");
        Result result = businessService.getBonus(day);
        return result;
    }

    @RequestMapping(value = "/attendSport", method = RequestMethod.GET)
    @ApiOperation(value = "参与明日比赛")
    public Result attendSport(@RequestParam("openId")String openId, @RequestParam("day")String day){
        //day如：2018-07-19
        logger.info("-- Start to attend tomorrow's sport --");
        Result result = businessService.attendTomSport(openId, day);
        return result;
    }

    @ApiOperation(value = "提交今日成绩")
    @RequestMapping(value = "/submissionToday", method = RequestMethod.GET)
    public Result submissionToday(@RequestParam("openId")String openId, @RequestParam("day")String day,
                                  @RequestParam("steps")int steps){
        logger.info("-- Start to submit today's steps --");
        Result result = businessService.submissionToday(openId, day, steps);
        return result;
    }

    @ApiOperation(value = "获取用户运动状态")
    @RequestMapping(value = "/querySportStatus", method = RequestMethod.GET)
    public Result querySportStatus(@RequestParam("openId")String openId, @RequestParam("day")String day){
        logger.info("-- Start to query sport status --");
        Result result = businessService.querySportStatusByUser(openId, day);
        return result;
    }

}
