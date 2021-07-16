package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/5/28 17:32
 *@version:1.1
 */

import com.miku.lab.service.imp.MailServiceImp;
import com.miku.lab.entity.vo.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
@Api(value="MailController",tags="邮箱接口")
public class MailController {

    @Autowired
    private MailServiceImp mailServiceImp;

    @ApiOperation(value = "邮件验证码接口")
    @ApiImplicitParam()
    @GetMapping("/sendMail")
    public ReturnResult sendMail(@RequestParam String email){
        return mailServiceImp.sendActiveMail(email);
    }

    @ApiOperation(value = "邮件接口")
    @ApiImplicitParam()
    @GetMapping("/sendNotifyMail")
    public ReturnResult sendNotifyMail(@RequestParam String email){
        return mailServiceImp.sendNotifyMail(email);
    }
}
