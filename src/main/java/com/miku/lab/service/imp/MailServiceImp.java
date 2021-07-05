package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/5/28 17:13
 *@version:1.1
 */

import com.miku.lab.service.MailService;
import com.miku.lab.util.ResultUtil;
import com.miku.lab.vo.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class MailServiceImp implements MailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public ReturnResult sendActiveMail(String eamil) {
       try {
           String checkCode = String.valueOf(new Random().nextInt(899999)+100000);
           System.out.println(checkCode);
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("1257322785@qq.com");
           message.setTo(eamil);
           message.setSubject("涛涛的测试邮箱接口");
           message.setText("验证码："+checkCode+"，5分钟内有效。5分钟过后，求我我也不给略略略");
           javaMailSender.send(message);
           System.out.println("邮箱发送成功");
           return ResultUtil.success(checkCode,1000,1);
       }catch (Exception e){
           e.printStackTrace();
           return ResultUtil.error(1001,"发送失败");
       }
    }
}
