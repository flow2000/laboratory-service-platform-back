package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 14:28
 *@version:1.1
 */

import com.miku.lab.dao.BookLogDao;
import com.miku.lab.entity.*;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.IdUtil;
import com.miku.lab.util.StringUtil;
import com.miku.lab.util.TimeUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookLogServiceImp implements BookLogService{

    @Autowired
    private BookLogDao bookLogDao;

    //生成编号
    String flag = new StringUtil().generateBookId();

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
     * 获取所有的预约仪器的的数量
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
     * 首次预约仪器
     * @param openId
     * @param machine_id
     * @param book_number
     * @return
     */
    @Override
    public String addBookMachineLog(String openId,String machine_id,String book_number){
        String a = Constant.bookId;
        Map<String,Object>map = new HashMap<>();
        WxUser wxUser = bookLogDao.getWxUserByOpenId(openId);
        //确保用户已登录
        if (wxUser==null){
            return  "用户未授权";
        }
        map.put("machine_id",machine_id);
        map.put("book_number",book_number);
        Machine machine = bookLogDao.getMachineAndCount(map);
        if(machine!=null){
            if(machine.getValidStatus()==0){
                return "该仪器属于无效状态，不能预约";
            }
            map.put("openId",openId);
            map.put("createTime",new Date());
            map.put("booking_code", Constant.bookId);
            String last_number = String.valueOf(machine.getBookableCount()-Integer.valueOf(book_number));
            int affectAdd = bookLogDao.addBookMachine(map);
            if(affectAdd>0){
                map.put("last_number",last_number);
                int affectUpdate  = bookLogDao.updateMachine(map);
                if(affectUpdate>0){
                    return "预约仪器成功";
                }
            }else{
                return "库存不足";
            }
        }else{
            return "仪器为空，请联系管理员添加仪器";
        }
        return null;
    }

    /**
     * 增加一个预约仪器数量
     * @param openId
     * @param machine_id
     * @return
     */
    @Override
    public String addBookingNumber(String openId,String machine_id){
        Map<String,Object>map = new HashMap<>();
        map.put("machine_id",machine_id);
        Machine machine = bookLogDao.getMachine(map);
        int ableBookNumber =machine.getBookableCount();
        if(ableBookNumber==0){
            return  "可预约库存不足";
        }
        map.put("openId",openId);
        BookMachine bookOne = bookLogDao.getBookLogByOpenIdAndMachineId(map);


        if(ableBookNumber>=1){
            String last_number = String.valueOf(machine.getBookableCount()-1);//仪器表-1
            map.put("last_number",last_number);                                 //仪器表-1
            int machineUpdate  = bookLogDao.updateMachine(map);                  //仪器表-1

            map.put("machine_number",bookOne.getMachineNumber()+1);             //预约仪器表+1
            int numberUpdate = bookLogDao.updateNumberByOpenIdAndMachineId(map);//预约仪器表+1
            if(numberUpdate>0&&machineUpdate>0){
                return "增加成功";
            }
        }
            return  "库存不足";
    }

    /**
     * 减少一个预约仪器数量
     * @param openId
     * @param machine_id
     * @return
     */
    @Override
    public String subBookingNumber(String openId,String machine_id){
        Map<String,Object>map = new HashMap<>();
        map.put("machine_id",machine_id);
        Machine machine = bookLogDao.getMachine(map);       //获取仪器信息

        map.put("openId",openId);
        BookMachine bookOne = bookLogDao.getBookLogByOpenIdAndMachineId(map);   //获取已添加预约仪器信息
        if(bookOne==null){
            return "不可再减少，请重新预约！";
        }

        String last_number = String.valueOf(machine.getBookableCount()+1);//仪器表+1
        map.put("last_number",last_number);                                 //仪器表+1
        int machineUpdate  = bookLogDao.updateMachine(map);                  //仪器表+1
        //如果还剩1个，就删除整条记录
        if((bookOne.getMachineNumber()-1)==0){
            bookLogDao.delWxBookMachine(map);
            return "减少成功";
        }
        map.put("machine_number",bookOne.getMachineNumber()-1);             //预约仪器表-1

        int numberUpdate = bookLogDao.updateNumberByOpenIdAndMachineId(map);//预约仪器表-1
        if(numberUpdate>0&&machineUpdate>0){
            return "减少成功";
        }
        return "扣除失败";

    }


    /**
     *添加实验室
     * @param orderCheck
     * @return
     */
    @Override
    //String openId,String order_number,String lab_name,String remark,String start_time,String end_time
    public String addLabLog(OrderCheck orderCheck){
        Map<String,Object>map = new HashMap<>();
        WxUser wxUser = bookLogDao.getWxUserByOpenId(orderCheck.getOpenId());
        if (wxUser==null){
            return  "用户未授权";
        }
        LabInfo labInfo = bookLogDao.getLabById(orderCheck.getLabId());
        if(labInfo!=null){
            if(labInfo.getLabStatus()==1){
                return "该实验室已借用，请更换实验室";
            }else if(labInfo.getLabStatus()==-1){
                return "该实验室已被锁定，请更换实验室";
            }
            map.put("labId",orderCheck.getLabId());
            map.put("labStatus","1");
            int affectupdate = bookLogDao.updateLabSetStatus(map);   //更改状态
            if(affectupdate>0){
                orderCheck.setBookingCode(Constant.bookId);
                orderCheck.setOrderApplyer(wxUser.getRealName());
                orderCheck.setOrderPhone(wxUser.getUserPhone());
                orderCheck.setUsername(wxUser.getUsername());
                int affectAdd = bookLogDao.addBookLab(orderCheck);             //插入借用记录
                if (affectAdd>0){
                    map.put("booking_code",Constant.bookId);
                    map.put("creater",orderCheck.getOrderApplyer());
                    bookLogDao.addBookingLog(map);
                    return "预约成功";
                }
            }
        }else{
            return "实验室为空";
        }
        return "预约失败，详细原因请联系管理员";
    }

    @Override
    //撤销申请，同时改微信预约表、预约审核表、预约日志表
    public int drawApply(String openId,String lab_id){
        Map<String,Object>map = new HashMap<>();
        map.put("openId",openId);
        map.put("labId",lab_id);
        map.put("checkStatus","0");
        //获得对应的预约信息
        OrderCheck wxOrderCheck = bookLogDao.getWxOrderCheck(map);
        if(wxOrderCheck==null){
            return 0;
        }

        //不等于待审核的状态不急于撤销
        if (wxOrderCheck.getCheckStatus()!=0){
            return -1;
        }
        //审核表审核状态设置为已失效
        map.put("check_status","3");
        int wxStatus = bookLogDao.setWxOrderCheckStatus(map);
        if (wxStatus!=0){
            //实验室状态回到可借用状态
            map.put("labStatus","0");
            int labStatus = bookLogDao.updateLabSetStatus(map);

            //预约日志表无效
            map.put("bookingCode",wxOrderCheck.getBookingCode());
            map.put("validStatus","0");
            bookLogDao.setBookingLogStatus(map);

            //更改仪器表数量信息
            map.put("validStatus","1");
            List<BookMachine> machineById =  bookLogDao.getMachineById(map);
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
            }
            return 1;
        }else{
            return 0;
        }

    }

}
