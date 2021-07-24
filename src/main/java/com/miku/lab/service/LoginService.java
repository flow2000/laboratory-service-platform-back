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

    String login(String user_id,String password,String code);

    UserInfo getLoginUser(String token);

    Set<String> getRolePermission(UserInfo user);

    public Object roleMenuTreeData(String token);

    public List<SysMenu> selectMenuAll(String userId);
}
