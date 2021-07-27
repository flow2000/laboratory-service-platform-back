package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/12 21:26
 *@version:1.1
 */

import com.miku.lab.dao.OrderCheckDao;
import com.miku.lab.entity.BookMachine;
import com.miku.lab.entity.OrderCheck;
import com.miku.lab.service.MailService;
import com.miku.lab.service.OrderCheckService;
import com.miku.lab.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderCheckServiceImp implements OrderCheckService {

    @Autowired
    private OrderCheckDao orderCheckDao;

    @Autowired
    private MailService mailService;

    @Override
    public Object getAllOrderCheck() {
        List<OrderCheck> orderChecks = orderCheckDao.getAllOrderCheck();
        if(orderChecks!=null){
            return orderChecks;
        }else{
            return null;
        }
    }

    @Override
    public Object getWxMachineLog(String openId) {
        Map<String,Object> map = new HashMap<>();
        List<BookMachine> bookMachines = orderCheckDao.getWxBookLogById(openId);
        if(bookMachines!=null){
            map.put("machine",bookMachines);
            return map;
        }else{
            return null;
        }
    }

    @Override
    public Object getAllBookingInfo() {
        return orderCheckDao.getAllBookingInfo();
    }

    @Override
    public Object getPageBookingInfo(int page,int limit) {
        Map<String,Object> map = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (page-1)*limit;
            m = limit;
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<Map> pageBookingInfoList = orderCheckDao.getPageBookingInfo(p,m);
        int count = orderCheckDao.getBookingInfoCount();
        map.put("bookingInfos",pageBookingInfoList);
        map.put("count",count);
        return map;
    }

    @Override
    public Object searchBookingInfo(int page,int limit,String searchKey,String searchValue) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("searchKey",searchKey);
        map.put("searchValue",searchValue);
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        List<Map> pageBookingInfoList = orderCheckDao.searchBookingInfo(map);
        int count = orderCheckDao.searchBookingInfoCount(map);
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("bookingInfos",pageBookingInfoList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Object getPageOrderMachine(String openid, String booking_code, int page, int limit) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> resMap = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (page-1)*limit;
            m = limit;
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        map.put("p",p);
        map.put("m",m);
        map.put("openid",openid);
        map.put("booking_code",booking_code);
        List<Map> pageBookingInfoList = orderCheckDao.getPageOrderMachine(map);
        int count = orderCheckDao.getPageOrderMachineCount(map);
        resMap.put("orderMachines",pageBookingInfoList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Integer checkBooking(Map<String, Object> param) {
        String email =(String) param.get("email");
        String remark =(String) param.get("remark");
        if("1".equals(param.get("check_status"))){ //审核通过
            param.put("check_result",1);
            if(StringUtil.isEmail(email)){
                mailService.sendApplicationSucMail(email, remark);
            }
        }else if("2".equals(param.get("check_status"))){ //审核不通过
            param.put("check_result",0);
            if(StringUtil.isEmail(email)){
                mailService.sendApplicationErrMail(email,remark);
            }
        }
        orderCheckDao.addOrderCheckLog(param);
        return orderCheckDao.checkBooking(param);
    }

    @Override
    public Object getPageBookingLog(int page, int limit) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> resMap = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (page-1)*limit;
            m = limit;
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        map.put("p",p);
        map.put("m",m);
        List<Map> pageBookingLogList = orderCheckDao.getPageBookingLog(map);
        int count = orderCheckDao.getPageBookingLogCount(map);
        resMap.put("bookingLogs",pageBookingLogList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Object searchBookingLog(int page, int limit, String searchKey, String searchValue) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("searchKey",searchKey);
        map.put("searchValue",searchValue);
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        List<Map> pageBookingLogList = orderCheckDao.searchBookingLog(map);
        int count = orderCheckDao.searchBookingLogCount(map);
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("bookingLogs",pageBookingLogList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Object getAllBookingLog() {
        return orderCheckDao.getAllBookingLog();
    }

}
