package com.miku.lab.controller;/*
 *@author 邓涛
 *@data 2021/7/25 18:52
 *@version:1.1
 */

import com.miku.lab.service.AliyunSmsSenderService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="SmsController",tags="手机短信接口")
public class SmsController {

    @Autowired
    private AliyunSmsSenderService aliyunSmsSenderService;

    /**
     * @Function: 短信验证接口
     * @author: 涛
     * @Date: 2019/4/11 15:39
     */
    @ApiOperation(value = "验证码短信接口,若无需要，请勿随便尝试")
    @ApiImplicitParam(name = "phone",value="手机号码",required=true)
    @GetMapping("/smsverification")
    public Object SmsVerification(@RequestParam("phone") String phone) {
        return aliyunSmsSenderService.SmsVerification(phone);
    }
}
