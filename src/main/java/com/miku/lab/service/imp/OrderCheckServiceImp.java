package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/7/12 21:26
 *@version:1.1
 */

import com.miku.lab.dao.OrderCheckDao;
import com.miku.lab.entity.OrderCheck;
import com.miku.lab.service.OrderCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCheckServiceImp implements OrderCheckService {

    @Autowired
    private OrderCheckDao orderCheckDao;

    @Override
    public Object getAllOrderCheck() {
        List<OrderCheck> orderChecks = orderCheckDao.getAllOrderCheck();
        if(orderChecks!=null){
            return orderChecks;
        }else{
            return null;
        }
    }
}
