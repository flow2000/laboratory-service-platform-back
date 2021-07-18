package com.miku.lab.dao;/*
 *@author miku
 *@data 2021/7/12 20:49
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface WxUserDao {

    List<WxUser> getAllWxUser();
    int updateWxUser(WxUser wxUser);

    int addWxUser(Map<String,Object> map);

    WxUser getWxUserById(String openId);
}
