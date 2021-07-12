package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/12 20:49
 *@version:1.1
 */

import com.miku.lab.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WxUserDao {

    List<WxUser> getAllWxUser();
}
