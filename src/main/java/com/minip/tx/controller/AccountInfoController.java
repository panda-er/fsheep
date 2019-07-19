package com.minip.tx.controller;

import com.minip.tx.service.AccountInfoService;
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
@RequestMapping(value = "/accountInfo")
@Api(value = "用户账户信息")
public class AccountInfoController {
    private final static Logger logger = LoggerFactory.getLogger(AccountInfoController.class);

    @Autowired
    private AccountInfoService accountInfoService;


    @RequestMapping(value = "/getUserAccount", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户账户当前信息")
    public Result getUserAccount(@RequestParam("openId")String openId){
        logger.info("-- Start to get user account info --");
        logger.info("OpenId: "+ openId);
        Result result = accountInfoService.getUserAccount(openId);
        return result;
    }

}
