package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/5/28 17:13
 *@version:1.1
 */

import com.miku.lab.service.MailService;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.util.AjaxUtil;
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

    /**
     * 发送邮箱验证码
     * @param eamil
     * @return
     */
    @Override
    public ReturnResult sendActiveMail(String eamil) {
       try {
           String checkCode = String.valueOf(new Random().nextInt(899999)+100000);
           System.out.println(checkCode);
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("1257322785@qq.com");
           message.setTo(eamil);
           message.setSubject("注册验证码为：");
           message.setText("验证码："+checkCode+"，5分钟内有效。请尽快填写");
           javaMailSender.send(message);
           System.out.println("邮箱发送成功");
           return AjaxUtil.success(checkCode,1000,1);
       }catch (Exception e){
           e.printStackTrace();
           return AjaxUtil.error(1001,"发送失败");
       }
    }

    /**
     * 通知邮箱
     * @param eamil
     * @return
     */
    @Override
    public ReturnResult sendNotifyMail(String eamil) {
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom("1257322785@qq.com");
           message.setTo(eamil);
           message.setSubject("预约结果");
           message.setText("你好，你的相应仪器或实验室预约已经完成，请准时到指定地点领取并签名");
           javaMailSender.send(message);
           System.out.println("邮箱发送成功");
           return AjaxUtil.success("预约成功",1000,1);
       }catch (Exception e){
           e.printStackTrace();
           return AjaxUtil.error(1001,"发送失败");
       }
    }

    /**
     * 通知申请失效邮箱
     * @param email
     * @return
     */
    @Override
    public ReturnResult sendApplicationExpireMail(String email,String booking_code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1257322785@qq.com");
            message.setTo(email);
            message.setSubject("申请失效通知");
            message.setText("你好，你的申请已经失效\n失效的预约编号为:"+booking_code+"\n请您重新申请！");
            javaMailSender.send(message);
            return AjaxUtil.success("邮箱发送成功",1000,1);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxUtil.error(1001,"发送失败");
        }
    }

    /**
     * 通知审核通过邮箱
     * @param email
     * @return
     */
    @Override
    public ReturnResult sendApplicationSucMail(String email,String remark) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1257322785@qq.com");
            message.setTo(email);
            message.setSubject("审核通过");
            if("".equals(remark)) {
                message.setText("你好，你的申请已经通过审核\n请准时到指定地点领取仪器并签名\n");
            }else{
                message.setText("你好，你的申请已经通过审核\n"+remark+"\n");
            }
            javaMailSender.send(message);
            return AjaxUtil.success("邮箱发送成功",1000,1);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxUtil.error(1001,"发送失败");
        }
    }

    /**
     * 通知审核不通过邮箱
     * @param email
     * @return
     */
    @Override
    public ReturnResult sendApplicationErrMail(String email,String remark) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1257322785@qq.com");
            message.setTo(email);
            message.setSubject("审核不通过");
            if("".equals(remark)) {
                message.setText("你好，你的申请没有通过审核\n请重新填写\n");
            }else{
                message.setText("你好，你的申请已经通过审核\n"+remark+"\n");
            }
            javaMailSender.send(message);
            return AjaxUtil.success("邮箱发送成功",1000,1);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxUtil.error(1001,"发送失败");
        }
    }

}
