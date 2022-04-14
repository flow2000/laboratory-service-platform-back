package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/12 21:26
 *@version:1.1
 */

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.miku.lab.dao.OrderCheckDao;
import com.miku.lab.entity.BookMachine;
import com.miku.lab.entity.OrderCheck;
import com.miku.lab.service.MailService;
import com.miku.lab.service.OrderCheckService;
import com.miku.lab.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
        Map<String ,Object> map = new HashMap<>();
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        String [] key = searchKey.split(";");
        String [] value = searchValue.split(";");
        map.put("usernameKey",key[0]);
        map.put("lab_addressKey",key[1]);
        map.put("lab_nameKey",key[2]);

        if(value.length>=1 && !value[0].equals("")){
            map.put("usernameValue",value[0]);
        }
        if(value.length>=2 && !value[1].equals("")){
            map.put("lab_addressValue",value[1]);
        }
        if(value.length>=3 && !value[2].equals("")) {
            map.put("lab_nameValue", value[2]);
        }
        List<Map> pageBookingInfoList = orderCheckDao.searchBookingInfo(map);
        int count = orderCheckDao.searchBookingInfoCount(map);
        Map<String ,Object> resMap = new HashMap<String ,Object>();
        resMap.put("bookingInfos",pageBookingInfoList);
        resMap.put("count",count);
        return resMap;
    }

    @Override
    public Object getOneBookingInfo(String booking_code, String openId) {
        return orderCheckDao.getOneBookingInfo(booking_code,openId);
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

    /**
     * 审核结果推送
     * @param param
     * @return
     */
    @Override
    public Integer checkBooking(Map<String, Object> param) {
        if("1".equals(param.get("check_status"))){ //审核通过
            param.put("check_result",1);
        }else if("2".equals(param.get("check_status"))){ //审核不通过
            param.put("check_result",0);
        }
        send(param);  //推送
        orderCheckDao.addOrderCheckLog(param);
        return orderCheckDao.checkBooking(param);
    }

    /**
     * 重新审核预约
     * @param param
     * @return
     */
    @Override
    public int againCheckBooking(Map<String, Object> param) {
        if("1".equals(param.get("check_status"))){ //审核通过
            param.put("check_result",1);
        }else if("2".equals(param.get("check_status"))){ //审核不通过
            param.put("check_result",0);
        }
        send(param);  //推送
        orderCheckDao.addOrderCheckLog(param);
        return orderCheckDao.againCheckBooking(param);
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
        map.put("p",(page-1)*limit);
        map.put("m",limit);
        String [] key = searchKey.split(";");
        String [] value = searchValue.split(";");
        map.put("baseInfoKey",key[0]);
        map.put("statusKey",key[1]);
        map.put("baseInfoValue",value[0]);
        if(value.length>=2){
            map.put("statusValue",value[1]);
        }
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

    /**
     * 获取小程序token
     * @return token
     */
    public  String getAccessToken(String openid) {
        List<Map> list = orderCheckDao.getWxUserToken(openid);
        if(list!=null&&!list.isEmpty()){
            System.out.println("token====="+ list.get(0).get("token"));
            return (String) list.get(0).get("token");
        }
        return "";
    }

    /**
     * 推送到小程序
     * phrase1：预约结果
     * thing6：预约业务
     * character_string4：预约时段
     * thing9：预约地点
     * thing8：温馨提示
     */
    public String send(Map<String,Object>map){
        JSONObject body=new JSONObject();
        body.set("touser",map.get("openid"));
        body.set("template_id","Y9FgbI6u8_xp7UOfxGUtQ8xZ8_QyeW5tMNWQanOHa9g");
        JSONObject json=new JSONObject();
        json.set("phrase1",new JSONObject().set("value",map.get("phrase1")));
        json.set("thing6",new JSONObject().set("value",map.get("thing6")));
        json.set("character_string4",new JSONObject().set("value",map.get("character_string4")));
        json.set("thing9",new JSONObject().set("value", map.get("thing9")));
        json.set("thing8",new JSONObject().set("value",map.get("thing8")));
        body.set("data",json);

        //发送
        String accessToken= getAccessToken(String.valueOf(map.get("openid")));
        String post =  HttpRequest.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken)
                .body(String.valueOf(body))
                .execute().body();
        System.out.println("推送结果："+post);
        return "ok";
    }

}
