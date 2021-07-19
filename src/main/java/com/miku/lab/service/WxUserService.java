package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/12 20:50
 *@version:1.1
 */

import com.miku.lab.entity.WxUser;
import org.springframework.stereotype.Service;

@Service
public interface WxUserService {
    Object getAllWxUser();

    int updateWxUser(WxUser wxUser);

    int addWxUser(String openId,String username);

    int updateWxPush(String openId,String isReceptMsg,String isReceptPush);
}
