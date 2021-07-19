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
    List<BookLog> getAllBookLog();

    List<BookMachine> getAllBookMachine();

    WxUser getWxUserByOpenId(String openId);

    Machine getMachineAndCount(Map<String,Object> map);

    int addBookMachine(Map<String,Object>map);

    int updateMachine(Map<String,Object>map);

    Machine getMachineById(Map<String,Object>map);

    LabInfo getLabById(String lab_id);

    int updateLabSetStatus(String lab_id);

    int addBookLab(OrderCheck orderCheck);
}
