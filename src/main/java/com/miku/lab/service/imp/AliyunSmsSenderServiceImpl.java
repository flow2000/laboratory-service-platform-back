package com.miku.lab.service.imp;



import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import com.miku.lab.config.AliyunConfig;

import com.miku.lab.service.AliyunSmsSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
 *@author 邓涛
 *@data 2021/7/25 18:34
 *@version:1.1
 */
@Slf4j
@Service
public class AliyunSmsSenderServiceImpl implements AliyunSmsSenderService {

    /**
     * @Function: 短信验证
     * @author:   Yangxf
     * @Date:     2019/4/11 15:56
     * @param:    phone 手机号
     */
    @Override
    public Map<String, Object> SmsVerification(String phone) {
        Map<String, Object> map = new HashMap<>();
        try {
            SendSmsResponse sendSmsResponse = AliyunConfig.sendSms(phone);
            if(sendSmsResponse!=null){
                map.put("code", 200);
                map.put("msg", "短信验证发送成功");
                return map;
            }else{
                map.put("code", 300);
                map.put("msg", "短信发送失败");
                return map;
            }

        } catch (ClientException e) {
            map.put("code", 300);
            map.put("msg", "短信发送失败");
            return map;
        }
    }
}

