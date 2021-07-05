package com.miku.lab.service.imp;/*
 *@author miku
 *@data 2021/6/1 20:12
 *@version:1.1
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miku.lab.dao.UserInfoDao;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.service.UserInfoService;
import com.miku.lab.util.JwtTokenUtils;
import com.miku.lab.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserInfoServiceImp implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Resource
    @Autowired
    private RedisUtils redisUtils;

    /**
     *
     * @param userInfo
     * @param rememberme
     * @return
     */
    public String login(UserInfo userInfo, int rememberme){
       /* QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userInfo.getUserId());
        queryWrapper.eq("password",userInfo.getPassword());*/
        /*userInfo = userInfoDao.selectOne(queryWrapper);*/
        userInfo = userInfoDao.selectUsernameAndPassword(userInfo);
        //生成token
        if(userInfo!=null){
            String token = JwtTokenUtils.createToken(userInfo,rememberme);
            System.out.println(token);
            redisUtils.set(userInfo.getUserId(),token);
            System.out.println(redisUtils.get(userInfo.getUserId()));
            return token;
        }
        return null;
    }
    public UserInfo findUserById(String userId){
        /*QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);*/
        return userInfoDao.getUserById(userId);
    }
}
