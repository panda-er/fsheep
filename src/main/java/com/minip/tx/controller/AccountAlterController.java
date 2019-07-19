package com.minip.tx.controller;

import com.minip.tx.service.AccountAlterService;
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
@RequestMapping(value = "/accountAlter")
@Api(value = "账户变化信息")
public class AccountAlterController {
    private final static Logger logger = LoggerFactory.getLogger(AccountAlterController.class);
    @Autowired
    private AccountAlterService accountAlterService;

    @RequestMapping(value = "/getAlterList", method = RequestMethod.GET)
    @ApiOperation(value = "根据openId获取账户变化列表")
    public Result getAlterList(@RequestParam("openId")String openId, @RequestParam("pageNow")int pageNow, @RequestParam("pageSize")int pageSize){
        logger.info("-- Start to get account alterationInfo List --");
        Result result = accountAlterService.getAlterList(openId, pageNow, pageSize);
        return result;
    }
}
