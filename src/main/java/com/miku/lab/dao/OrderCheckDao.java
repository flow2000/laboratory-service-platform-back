package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/12 21:24
 *@version:1.1
 */

import com.miku.lab.entity.OrderCheck;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderCheckDao {

    List<OrderCheck> getAllOrderCheck();
}
