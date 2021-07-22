package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/12 21:24
 *@version:1.1
 */

import com.miku.lab.entity.BookMachine;
import com.miku.lab.entity.OrderCheck;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderCheckDao {

    List<OrderCheck> getAllOrderCheck();

    //通过openid获取对应的预约仪器记录
    List<BookMachine> getWxBookLogById(String openId);

    List<Map> getAllBookingInfo();

    List<Map> getPageBookingInfo(int p, int m);

    int getBookingInfoCount();

    List<Map> searchBookingInfo(Map<String, Object> map);

    int searchBookingInfoCount(Map<String, Object> map);

    List<Map> getPageOrderMachine(Map<String, Object> map);

    int getPageOrderMachineCount(Map<String, Object> map);

    Integer checkBooking(Map<String, Object> param);

    Integer addOrderCheckLog(Map<String, Object> param);

    List<Map> getPageBookingLog(Map<String, Object> map);

    int getPageBookingLogCount(Map<String, Object> map);

    List<Map> searchBookingLog(Map<String, Object> map);

    int searchBookingLogCount(Map<String, Object> map);
}
