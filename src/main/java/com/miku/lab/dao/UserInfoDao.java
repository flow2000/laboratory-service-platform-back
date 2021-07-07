package com.miku.lab.dao;

/*
 *@author miku
 *@data 2021/6/1 20:07
 *@version:1.1
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.lab.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoDao  {
    UserInfo loginVerify(UserInfo userInfo);
    List<UserInfo> getAllUserInfo();
}
