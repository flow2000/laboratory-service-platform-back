package com.miku.lab.dao;/*
 *@author 邓涛
 *@data 2021/7/24 13:36
 *@version:1.1
 */

import com.miku.lab.entity.Role;
import com.miku.lab.entity.SysMenu;
import com.miku.lab.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
@Repository
public interface LoginDao {

    UserInfo getUserInfo(UserInfo user);

    UserInfo getUserInfoByUserName(String userId);

    List<Role> selectRolePermissionByUserId(String userId);

    List<SysMenu> selectMenuAll();

    public List<SysMenu> selectMenuAllByUserId(String userId);

    /**
     * 根据角色ID查询菜单
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(String roleId,String menu_type);
}
