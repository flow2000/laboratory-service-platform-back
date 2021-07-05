package com.miku.lab.service;/*
 *@author miku
 *@data 2021/6/1 20:12
 *@version:1.1
 */

import com.miku.lab.entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
    public String login(UserInfo userInfo, int rememberme);
    public UserInfo findUserById(String userId);
}
