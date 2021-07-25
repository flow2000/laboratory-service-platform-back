package com.miku.lab.service;/*
 *@author 邓涛
 *@data 2021/7/25 18:31
 *@version:1.1
 */

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

import java.util.Map;

public interface AliyunSmsSenderService {

    public Map<String, Object> SmsVerification(String phone);
}
