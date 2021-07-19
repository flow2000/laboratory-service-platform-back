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

    List<WxUser> getAllWxUser();    //获取所有微信用户

    int updateWxUser(WxUser wxUser);        //更新微信用户

    int addWxUser(Map<String,Object> map);  //添加微信用户

    WxUser getWxUserById(String openId);        //获取微信详细用户信息

    int updateWxPush(Map<String,Object> map);   //更新微信是否推送信息
}
