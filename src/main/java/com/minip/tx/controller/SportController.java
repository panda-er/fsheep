package com.minip.tx.controller;

import com.minip.tx.service.SportInfoService;
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
@RequestMapping(value = "/sport")
@Api(value = "运动详情")
public class SportController {
    private final static Logger logger = LoggerFactory.getLogger(SportController.class);
    @Autowired
    private SportInfoService sportInfoService;

    @RequestMapping(value = "/getSportListByUser", method = RequestMethod.GET)
    @ApiOperation(value = "根据openId查询每日运动详情")
    public Result getSportListByUser(@RequestParam("openId")String openId, @RequestParam("pageNow")int pageNow, @RequestParam("pageSize")int pageSize){
        logger.info("-- Start to get sportInfo list --");
        logger.info("OpenId: "+ openId);
        Result result = sportInfoService.getSportListByUser(openId, pageNow, pageSize);
        return result;
    }

}
