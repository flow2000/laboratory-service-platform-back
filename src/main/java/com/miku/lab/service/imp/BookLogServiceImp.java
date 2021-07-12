package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/9 14:28
 *@version:1.1
 */

import com.miku.lab.dao.BookLogDao;
import com.miku.lab.entity.BookLog;
import com.miku.lab.entity.BookMachine;
import com.miku.lab.entity.Machine;
import com.miku.lab.entity.WxUser;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.IdUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String addBookMachineLog(String openId,String machine_id,String book_number){
        WxUser wxUser = bookLogDao.getWxUserByOpenId(openId);
        if (wxUser==null){
            return  "用户未授权";
        }

        Map<String,String>map = new HashMap<>();
        map.put("machine_id",machine_id);
        map.put("book_number",book_number);
        Machine machine = bookLogDao.getMachineAndCount(map);
        if(machine!=null){
            map.put("openId",openId);
            map.put("booking_code", IdUtil.geneId(Constant.BUSINESS_Book));
            String last_number = String.valueOf(machine.getBookableCount()-Integer.valueOf(book_number));
            int affectAdd = bookLogDao.addBookMachine(map);
            if(affectAdd>0){
                map.put("last_number",last_number);
                int affectUpdate  = bookLogDao.updateMachine(map);
                if(affectUpdate>0){
                    return "预约成功";
                }
            }else{
                return "库存不足";
            }
        }else{
            return "仪器为空，请联系管理员添加仪器";
        }
        return null;
    }

}
