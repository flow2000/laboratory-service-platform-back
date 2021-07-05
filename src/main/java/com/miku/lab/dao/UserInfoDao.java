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

@Mapper
@Repository
public interface UserInfoDao  {
    UserInfo selectUsernameAndPassword(UserInfo userInfo);

   /* @Select("select *from sys_info_user where user_id = #{userId}")*/
    UserInfo getUserById(String userId);
}
