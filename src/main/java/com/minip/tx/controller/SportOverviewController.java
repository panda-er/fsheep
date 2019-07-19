package com.minip.tx.controller;

import com.minip.tx.service.SportOverviewService;
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
@RequestMapping(value = "/sportOverview")
@Api(value = "用户运动总览")
public class SportOverviewController {
    private final static Logger logger = LoggerFactory.getLogger(SportOverviewController.class);

    @Autowired
    private SportOverviewService sportOverviewService;

    @RequestMapping(value = "/getSportOverview", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户的运动总览信息")
    public Result getSportOverview(@RequestParam("openId")String openId){
        logger.info("-- Start to get sport overview info --");
        logger.info("OpenId: "+ openId);
        Result result = sportOverviewService.getOverviewByUser(openId);
        return result;
    }
}
