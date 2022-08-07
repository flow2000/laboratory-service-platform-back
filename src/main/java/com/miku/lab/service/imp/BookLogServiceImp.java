package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 14:28
 *@version:1.1
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miku.lab.dao.BookLogDao;
import com.miku.lab.entity.*;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.*;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookLogServiceImp implements BookLogService{

    @Autowired
    private BookLogDao bookLogDao;

    //生成编号


    /**
     * 获取所有预约记录
     * @return
     */
    @Override
    public Object getAllBookLog() {
        List<BookLog> bookLogs = bookLogDao.getAllBookLog();
        if(bookLogs!=null){
            return bookLogs;
        }else{
            return null;
        }
    }

    /**
     * 根据openid获取所有的预约仪器的的数量
     * @return
     */
    @Override
    public Object getAllBookMachineById(String openId) {
        List<BookMachine>bookMachines = bookLogDao.getAllBookMachineById(openId);
        if(bookMachines!=null){
            return bookMachines;
        }else {
            return null;
        }
    }

    /**
     * 预约按钮实现
     * @param map
     * @return
     */
    @Override
    public ReturnResult addBookingLog(Map<String, Object>map){

        //一次预约生成一个唯一编号
        String bookId = new StringUtil().generateBookId();

        map.put("bookingCode",bookId);

        //判断是否是合法的账户
        WxUser wxUser = bookLogDao.getWxUserByOpenId(String.valueOf(map.get("openId")));

        //确保用户已登录
        if (wxUser==null){
            return AjaxUtil.error(601,"用户未授权,不可预约");
        }

        //处理时间格式问题
        String endTime = String.valueOf(map.get("endTime"));
        if(!TimeUtil.isParseDate(endTime)){
            return AjaxUtil.error(610,"结束时间格式错误");
        }
        String startTime = String.valueOf(map.get("startTime"));
        if(!TimeUtil.isParseDate(startTime)){
            return AjaxUtil.error(611,"开始时间格式错误");
        }
        //结束时间不能大于开始时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date s = format.parse(startTime);
            Date e = format.parse(endTime);
            if (e.before(s)) {
                return AjaxUtil.error(612,"结束时间不能早于开始时间");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //处理实验室的预约---start
        LabInfo labInfo = bookLogDao.getLabById(String.valueOf(map.get("labId")));

        if(labInfo!=null){
            if(labInfo.getLabStatus()==1){
                return AjaxUtil.error(602,"实验室已借用，请更换！");
            }else if((labInfo.getLabStatus()==(-1))){
                return AjaxUtil.error(603,"实验室已锁定，请更换！");
            }
            map.put("labStatus","1");
            int affectupdate = bookLogDao.updateLabSetStatus(map);   //更改状态(1=已借用，0=可借用，-1=已锁定；默认1)
            if(affectupdate>0){
                map.put("username",wxUser.getUsername());
                map.put("orderApplyer",wxUser.getRealName());
                map.put("orderPhone",wxUser.getUserPhone());
                //没有审核人
                if(map.get("checker")==null){
                    map.put("checker","root");
                }

                //生成借用记录在预约审核表中
                map.put("chechStatus","0");
                int affectAdd = bookLogDao.addBookLab(map);

                if (affectAdd>0){
                    map.put("creater",wxUser.getRealName());
                    int addBookingLog = bookLogDao.addBookingLog(map);
                    if(addBookingLog<0){
                        return AjaxUtil.error(620,"添加预约日志失败");
                    }
                }else{
                    return AjaxUtil.error(607,"添加审核失败,请确保你的信息正确");
                }
            }
        }else{
            return AjaxUtil.error(604,"实验室为空");

        }
        //处理实验室---end

        //更新微信用户的token以便推送
        String token = (String) map.get("token");
        if(token!=null){
            System.out.println("前端：token=="+token);
            bookLogDao.updateWxUserToken(token,String.valueOf(map.get("openId")));
        }

        //处理仪器---start
        String machines =  String.valueOf(map.get("machines"));
        JSONArray jsonMachines = JSONArray.parseArray(machines);

        //封装失败的仪器预约
        List<Map<String,String>> machineListError = new ArrayList<>();

        //记录预约成功的仪器数
        int count = 0 ;
        int shouldSuccess = jsonMachines.size();
        for(int i=0;i<shouldSuccess;i++){             //遍历json数组

            Map<String, Object> machine = new HashMap<>();
            String one = jsonMachines.getString(i);          //获取json数组中的单个json对象
            JSONObject jsonMachine = JSONObject.parseObject(one);
            for(Map.Entry<String, Object> entry:jsonMachine.entrySet()){    //放入map
                machine.put(entry.getKey(),entry.getValue());
            }

            //预约仪器
            String machineCode = String.valueOf(machine.get("machineCode"));
            Machine machineAndCount = bookLogDao.getMachineAndCount(machine);
            if(machineAndCount!=null){
                if(machineAndCount.getValidStatus()==0){
                    Map<String,String> error = new HashMap<>();
                    error.put("编号:"+machineCode,",该仪器属于无效状态，不能预约");
                    machineListError.add(error);
                }
                //剩余的仪器数=可借用的-借用的数量
                String last_number = String.valueOf(machineAndCount.getBookableCount()-Integer.valueOf(String.valueOf(machine.get("machineNumber"))));

                //生成预约仪器记录
                map.put("machineCode",machine.get("machineCode"));
                map.put("machineNumber",machine.get("machineNumber"));

                int affectAdd = bookLogDao.addBookMachine(map);
                if(affectAdd>0){
                    map.put("last_number",last_number);
                    int affectUpdate  = bookLogDao.updateMachine(map);
                    if(affectUpdate>0){
                        count++;
                        // machineListError.add(machineAndCount);
                        //return 607;//预约仪器成功
                    }
                }else{
                    Map<String,String> error = new HashMap<>();
                    error.put("编号:"+machineCode,",该仪器库存不足");
                    machineListError.add(error);

                }
            }else{
                Map<String,String> error = new HashMap<>();
                error.put("编号:"+machineCode,",该仪器为空或可借用数量为0，请联系管理员添加仪器");
                machineListError.add(error);

            }

        }
        //处理仪器---end
        if(count==shouldSuccess){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"实验室和仪器预约成功");
        }else{
            Map<String, Object>mapResult = new HashMap<>();
            if(machineListError!=null){
                mapResult.put("实验室预约成功,以下是仪器预约失败的信息：",machineListError);
                mapResult.put("code",Constant.RESCODE_SUCCESS_MSG);
            }
            return AjaxUtil.bookError(mapResult,Constant.RESCODE_BOOK_ERROR);
        }
    }

    /**
     * 撤销申请
     * @param openId
     * @param lab_id
     * @return
     */
    @Override
    //撤销申请，同时改微信预约表、预约审核表、预约日志表
    public ReturnResult drawApply(String openId,String lab_id){
        Map<String,Object>map = new HashMap<>();
        map.put("openId",openId);
        map.put("labId",lab_id);
        map.put("checkStatus","0");

        //获得对应的预约信息
        OrderCheck wxOrderCheck = bookLogDao.getWxOrderCheck(map);
        if(wxOrderCheck==null){
            return AjaxUtil.error(601,"获取预约记录失败，请确保你输入的信息无误");
        }

        //不等于待审核的状态不能撤销
        if (wxOrderCheck.getCheckStatus()!=0){
            return AjaxUtil.error(612,"未处于待审核状态，不能撤回");
        }
        //审核表审核状态设置为已失效
        map.put("check_status","3");
        int wxStatus = bookLogDao.setWxOrderCheckStatus(map);
        if (wxStatus!=0){
            //实验室状态回到可借用状态
            map.put("labStatus","0");
            int labStatus = bookLogDao.updateLabSetStatus(map);
            if(labStatus<=0){
                return AjaxUtil.error(613,"实验室回退状态失败");
            }

            //预约日志表无效
            map.put("bookingCode",wxOrderCheck.getBookingCode());
            map.put("validStatus","0");
            bookLogDao.setBookingLogStatus(map);

            //更改仪器表数量信息
            map.put("validStatus","1");
            List<BookMachine> machineById =  bookLogDao.getMachineById(map);
            int shouldSucc = machineById.size();
            int count = 0;
            for(int i=0;i<machineById.size();i++){
                //更改微信预约表信息
                map.put("validStatus","3");
                map.put("bookingCode",machineById.get(i).getBookingCode());
                //还原仪器表信息
                bookLogDao.setWxBookingMachineStatus(map);

                map.put("machineNumber",machineById.get(i).getMachineNumber());
                map.put("machine_id",machineById.get(i).getMachineCode());
                //回退数量
                Machine machine = bookLogDao.getMachine(map);
                String last_number = String.valueOf(machine.getBookableCount()+machineById.get(i).getMachineNumber());//仪器表
                map.put("last_number",last_number);
                bookLogDao.setMachineNumber(map);
                count++;
            }
            if(count==shouldSucc){
                return AjaxUtil.sucessUpdate(614,"撤销成功");
            }
        }else{
            return AjaxUtil.sucessUpdate(614,"撤销失败");
        }
        return AjaxUtil.error(Constant.RESCODE_BOOK_ERROR,"撤销失败");

    }

}
