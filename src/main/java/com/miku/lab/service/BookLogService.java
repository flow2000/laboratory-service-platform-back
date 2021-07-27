package com.miku.lab.service;/*
 *@author miku
 *@data 2021/7/9 14:27
 *@version:1.1
 */

import com.miku.lab.entity.OrderCheck;
import com.miku.lab.entity.vo.ReturnResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookLogService {
    Object getAllBookLog();

    Object getAllBookMachineById(String openId);

    String addBookMachineLog(String openId,String machine_id,String book_number);

    //String addLabLog(OrderCheck orderCheck);

    //添加一个仪器数目
    String addBookingNumber(String openId,String machine_id);
    //减少一个仪器数目
    String subBookingNumber(String openId,String machine_id);

    //撤销申请
    int drawApply(String openId,String lab_id);

    //提交按钮
    public ReturnResult addBookingLog(Map<String, Object> map);


}
