package com.miku.lab.service.imp;/*
 *@author 邓涛
 *@data 2021/7/24 13:36
 *@version:1.1
 */

import com.miku.lab.dao.LoginDao;
import com.miku.lab.dao.RoleDao;
import com.miku.lab.entity.Role;
import com.miku.lab.entity.SysMenu;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.Ztree;
import com.miku.lab.service.LoginService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.JwtUtil;
import com.miku.lab.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private LoginDao loginDao;

    RedisUtil redisUtil = new RedisUtil();


    /**
     * 生成token
     * @param user_id
     * @param password
     * @param code
     * @return
     */
    @Override
    public String login(String user_id,String password,String code) {
        if(!code.equalsIgnoreCase(Constant.CODE)){
            return "验证码错误";
        }
        UserInfo user = new UserInfo();
        user.setUserId(user_id);
        user.setPassword(password);
        UserInfo userInfo = loginDao.getUserInfo(user);
        if (userInfo == null) {
            return "账号或密码错误，请重新检查";
        }
        if (userInfo.getIsDisable() == 1) {
            return "你的账号已经被禁用，请联系管理员";
        }


        //生成token
        String token = JwtUtil.geneToken(userInfo);
        if(token!=null){
            //放入redis
            redisUtil.setString(userInfo.getUserId(), token);
            return token;
        }else{
            return "token生成失败";
        }
    }

    /**
     * 获得登录token信息
     * @param token
     * @return
     */
    @Override
    public UserInfo getLoginUser(String token) {
        String username = JwtUtil.getUsername(token);
        String isValid = redisUtil.getString(username);
        if(isValid!=null){
            UserInfo userInfoByUserName = loginDao.getUserInfoByUserName(username);
            if(userInfoByUserName!=null){
                return userInfoByUserName;
            }
        }
        return null;
    }

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(UserInfo user){
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if ("admin".equals(user.getUserId()))
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(getRolePermission(user.getUserId()));
        }
        return roles;
    }

    /**
     * 根据角色ID查询菜单
     *

     */
    @Override
    public List<Ztree> roleMenuTreeData(String token)
    {
        String roleId = JwtUtil.getRole(token);
        String userId = JwtUtil.getUsername(token);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysMenu> menuList = selectMenuAll(userId);
        if (roleId!=null)
        {
            List<String> roleMenuList = loginDao.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }


    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysMenu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = false;
        if(roleMenuList!=null){
             isCheck = true;
        }else{
            isCheck = false;
        }
        for (SysMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getMenuId());
            ztree.setPId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getTitle());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SysMenu menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getTitle());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<SysMenu> selectMenuAll(String userId)
    {
        List<SysMenu> menuList = null;
        if ("admin".equals(userId))
        {
            menuList = loginDao.selectMenuAll();
        }
        else
        {
            menuList = loginDao.selectMenuAllByUserId(userId);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> getRolePermission(String userId)
    {
        List<Role> perms = loginDao.selectRolePermissionByUserId(userId);

        Set<String> permsSet = new HashSet<>();

        for (Role perm : perms)
        {
            if (perm!=null)
            {
                permsSet.addAll(Arrays.asList(perm.getRoleCode().trim().split(",")));
            }
        }
        return permsSet;
    }


}
