package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/12 21:26
 *@version:1.1
 */

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface OrderCheckService {

    Object getWxMachineLog(String openId);

    Object getAllOrderCheck();

    Object getAllBookingInfo();

    Integer checkBooking(Map<String, Object> param);

    Object getPageBookingInfo(int page,int limit);

    Object searchBookingInfo(int page,int limit,String searchKey,String searchValue);

    Object getOneBookingInfo(String booking_code, String openId);

    Object getPageOrderMachine(String openid, String booking_code, int page, int limit);

    Object getPageBookingLog(int page, int limit);

    Object searchBookingLog(int page, int limit, String searchKey, String searchValue);

    Object getAllBookingLog();
}
