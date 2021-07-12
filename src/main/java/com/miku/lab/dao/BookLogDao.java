package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 14:26
 *@version:1.1
 */

import com.miku.lab.entity.BookLog;
import com.miku.lab.entity.BookMachine;
import com.miku.lab.entity.Machine;
import com.miku.lab.entity.WxUser;
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

    Machine getMachineAndCount(Map<String,String> map);

    int addBookMachine(Map<String,String>map);

    int updateMachine(Map<String,String>map);
}
