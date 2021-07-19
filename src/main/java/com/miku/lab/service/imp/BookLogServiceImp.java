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
    public Object getAllBookMachine() {
        List<BookMachine>bookMachines = bookLogDao.getAllBookMachine();
        if(bookMachines!=null){
            return bookMachines;
        }else {
            return null;
        }
    }

    /**
     * 预约仪器
     * @param openId
     * @param machine_id
     * @param book_number
     * @return
     */
    @Override
    public String addBookMachineLog(String openId,String machine_id,String book_number){
        Map<String,Object>map = new HashMap<>();
        WxUser wxUser = bookLogDao.getWxUserByOpenId(openId);
        if (wxUser==null){
            return  "用户未授权";
        }
        map.put("machine_id",machine_id);
        map.put("book_number",book_number);
        Machine machine = bookLogDao.getMachineAndCount(map);
        if(machine!=null){
            map.put("openId",openId);
            map.put("createTime",new Date());
            map.put("booking_code", IdUtil.geneId(Constant.BUSINESS_Book));
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
     *
     * @param orderCheck
     * @return
     */
    @Override
    //String openId,String order_number,String lab_name,String remark,String start_time,String end_time
    public String addLabLog(OrderCheck orderCheck){
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
            int affectupdate = bookLogDao.updateLabSetStatus(orderCheck.getLabId());   //更改状态
            if(affectupdate>0){
                orderCheck.setBookingCode(IdUtil.geneId(Constant.BUSINESS_Book));
                orderCheck.setOrderApplyer(wxUser.getRealName());
                orderCheck.setOrderPhone(wxUser.getUserPhone());
                orderCheck.setChecker("root");
                orderCheck.setUsername(wxUser.getUsername());
                orderCheck.setCreateTime(new Date());
                int affectAdd = bookLogDao.addBookLab(orderCheck);             //插入借用记录
                if (affectAdd>0){
                    return "预约成功";
                }
            }
        }else{
            return "实验室为空";
        }
        return "预约失败，详细原因请联系管理员";
    }
}
