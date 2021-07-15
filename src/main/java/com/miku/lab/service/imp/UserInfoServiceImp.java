package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/6/1 20:12
 *@version:1.1
 */

import com.miku.lab.dao.UserInfoDao;
import com.miku.lab.entity.Role;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.service.UserInfoService;
import com.miku.lab.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Object getOneUser(String user_id) {
        if (user_id==null||"".equals(user_id)){
            return null;
        }
        Map<String,Object> map = userInfoDao.getOneUser(user_id);

        return map;
    }

    @Override
    public int verifyUserPassword(String user_id, String password) {
        UserInfo userInfo = null;
        try{
            userInfo = userInfoDao.verifyUserPassword(user_id,password);
        }catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if(userInfo==null){
            return 0;
        }
        return 1;
    }

    @Override
    public int updateUserInfo(Map<String, Object> param) {
        return userInfoDao.updateUserInfo(param);
    }

    @Override
    public Map<String, Object> getPageUser(String page, String limit) {
        Map<String,Object> map = new HashMap<>();
        int p = 0;
        int m = 10;
        try{
            p = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
            m = Integer.parseInt(limit);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","参数错误");
            return map;
        }
        List<UserInfo> pageUserList = userInfoDao.getPageUser(p,m);
        List<UserInfo> allUserList = userInfoDao.getAllUserInfo();
        map.put("users",pageUserList);
        map.put("count",allUserList.size());
        return map;
    }

    @Override
    public int addUser(Map<String, Object> param) {
        String user_id = (String) param.get("user_id");
        Map<String,Object> map = null;
        if(user_id!=null){
            map = userInfoDao.getOneUser(user_id);
            if (map==null){
                return userInfoDao.addUser(param);
            }
        }
        return 0;
    }

    @Override
    public int deleteUser(Map<String, Object> param) {
        String str = (String) param.get("user_id");
        str = str.substring(0,str.length());
        return userInfoDao.deleteUser(str);
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
