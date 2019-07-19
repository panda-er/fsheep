package com.minip.tx.controller;

import com.minip.tx.service.UserInfoService;
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
@RequestMapping(value = "/userInfo")
@Api(value = "用户信息")
public class UserInfoController {
    private final static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息")
    public Result getUserInfo(@RequestParam("openId")String openId){
        logger.info("-- Start to get user info --");
        logger.info("OpenId: "+ openId);
        Result result = userInfoService.getUserInfo(openId);
        return result;
    }
}
