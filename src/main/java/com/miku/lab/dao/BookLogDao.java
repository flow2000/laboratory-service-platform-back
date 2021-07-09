package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/9 14:26
 *@version:1.1
 */

import com.miku.lab.entity.BookLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookLogDao {
    List<BookLog> getAllBookLog();
}
