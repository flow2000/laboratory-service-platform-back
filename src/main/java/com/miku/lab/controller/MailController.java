package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/5/28 17:32
 *@version:1.1
 */

import com.miku.lab.service.imp.MailServiceImp;
import com.miku.lab.entity.vo.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
public class MailController {

    @Autowired
    private MailServiceImp mailServiceImp;

    @RequestMapping("/sendMail")
    public ReturnResult sendMail(@RequestParam String email){
        return mailServiceImp.sendActiveMail(email);
    }
}
