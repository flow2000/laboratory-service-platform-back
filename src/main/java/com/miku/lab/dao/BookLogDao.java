package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 14:26
 *@version:1.1
 */

import com.miku.lab.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookLogDao {
    //获取所有预约记录
    List<BookLog> getAllBookLog();
    //获取所有预约仪器的记录
    List<BookMachine> getAllBookMachineById(String openId);
    //通过opneid获取微信用户
    WxUser getWxUserByOpenId(String openId);
    //获取仪器的数量
    Machine getMachineAndCount(Map<String,Object> map);

    Machine getMachine(Map<String,Object> map);
    //增加仪器
    int addBookMachine(Map<String,Object>map);
    //更新仪器信息
    int updateMachine(Map<String,Object>map);
    //通过id获取仪器
    List<BookMachine> getMachineById(Map<String,Object>map);
    //通过id获取实验室
    LabInfo getLabById(String lab_id);
    //更新实验室的状态
    int updateLabSetStatus(Map<String,Object>map);
    //添加预约实验室
    int addBookLab(Map<String,Object>map);
    //通过Id取微信预约信息
    BookLog getBookLogById(String bookingCode);

    //通过仪器编号和openid更新对应预约的数量
    BookMachine getBookLogByOpenIdAndMachineId(Map<String,Object>map);

    //更新数量
    int updateNumberByOpenIdAndMachineId(Map<String,Object>map);

    //删除预约信息
    int delWxBookMachine(Map<String,Object>map);

    //微信端预约时添加日志到此
    int addBookingLog(Map<String,Object>map);

    //更改审核表状态
    int setWxOrderCheckStatus(Map<String,Object>map);
    //获得微信预约审核信息
    OrderCheck getWxOrderCheck(Map<String,Object>map);
    //更改预约日志表
    int setBookingLogStatus(Map<String,Object>map);

    int setWxBookingMachineStatus(Map<String,Object>map);
    int setMachineNumber(Map<String,Object>map);

    int updateWxUserToken(String token,String openid);
}
