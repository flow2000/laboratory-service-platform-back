package com.miku.lab.dao;

/*
 *@author miku
 *@data 2021/6/1 20:07
 *@version:1.1
 */

import com.miku.lab.entity.Role;
import com.miku.lab.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserInfoDao  {
    UserInfo loginVerify(UserInfo userInfo);
    List<UserInfo> getAllUserInfo();

    Map<String,Object> getOneUser(String user_id);

    UserInfo verifyUserPassword(String user_id, String password);

    int updateUserInfo(Map<String, Object> param);

    List<UserInfo> getPageUser(int p, int m);

    int addUser(Map<String, Object> param);

    int deleteUser(String str);

    int updatePersonPassword(String user_id, String password,String updater);
}
