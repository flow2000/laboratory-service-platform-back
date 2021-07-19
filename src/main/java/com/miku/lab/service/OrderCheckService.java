package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/12 21:26
 *@version:1.1
 */

import org.springframework.stereotype.Service;

@Service
public interface OrderCheckService {

    Object getWxMachineLog(String openId);

    Object getAllOrderCheck();
}
