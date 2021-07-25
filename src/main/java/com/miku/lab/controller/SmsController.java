package com.miku.lab.controller;/*
 *@author 邓涛
 *@data 2021/7/25 18:52
 *@version:1.1
 */

import com.miku.lab.service.AliyunSmsSenderService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @Autowired
    private AliyunSmsSenderService aliyunSmsSenderService;

    /**
     * @Function: 短信验证接口
     * @author: Yangxf
     * @Date: 2019/4/11 15:39
     */
    @RequestMapping("/smsverification")
    public Object SmsVerification(@RequestParam("phone") String phone) {
        return aliyunSmsSenderService.SmsVerification(phone);
    }
}
