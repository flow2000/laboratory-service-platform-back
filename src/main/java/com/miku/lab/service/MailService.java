package com.miku.lab.service;/*
 *@author miku
 *@data 2021/5/28 17:06
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    ReturnResult sendActiveMail( String email);

    ReturnResult sendNotifyMail( String email);
}
