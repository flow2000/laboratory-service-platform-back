package com.miku.lab.service;/*
 *@author 邓涛
 *@data 2021/7/24 13:36
 *@version:1.1
 */

import com.miku.lab.entity.SysMenu;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.Ztree;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface LoginService {


    Object login(String user_id,String password,String code);
    //获得登录用户信息
    UserInfo getLoginUser(String token);
    //获得角色权限
    Set<String> getRolePermission(UserInfo user);
    //生成角色菜单
    public Object roleMenuTreeData(String token);

    //获得所有菜单
    public List<SysMenu> selectMenuAll(String userId);

    //登录过滤
    public Object loginFilter(String token);
}
