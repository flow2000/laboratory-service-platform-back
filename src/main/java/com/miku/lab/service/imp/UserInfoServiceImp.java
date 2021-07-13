package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/6/1 20:12
 *@version:1.1
 */

import com.miku.lab.dao.UserInfoDao;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.service.UserInfoService;
import com.miku.lab.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    RedisUtil redisUtil = new RedisUtil();


    @Override
    public UserInfo login(String token,UserInfo user,String captcha){
        RedisUtil redisUtil = new RedisUtil();
        UserInfo userInfo = userInfoDao.loginVerify(user);
        if(userInfo!=null){
            return userInfo;
        }else{
            return null;
        }
    }

    /**
     * 获取全部用户信息
     * @return
     */
    @Override
    public List<UserInfo> getAllUser(){
        List<UserInfo> users = userInfoDao.getAllUserInfo();
        if(users!=null){
            return users;
        }else{
            return null;
        }
    }

    /**
     * 获取单个用户信息
     * @return
     */
    @Override
    public Map<String,Object> getOneUser(String user_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (user_id==null||"".equals(user_id)){
            map.put("msg","参数错误");
        }
        map.put("user",userInfoDao.getOneUser(user_id));
        return map;
    }

    public int isValiToken(String token,String user_id) {

        if("null".equals(redisUtil.getString(user_id))){
            return 0;
        }else if(redisUtil.getString(user_id)!=null) {
            if(token==""||"null".equals(token)) {
                return 0;
            }else if(redisUtil.getString(user_id).equals(token)) {
                return 1;
            }
        }
        return 0;

    }
}
