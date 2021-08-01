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
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {


    @Autowired
    private LoginDao loginDao;

    RedisUtil redisUtil = new RedisUtil();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        UserInfo user = getLoginUser(username);

        //判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<String> roleMenuList = loginDao.selectMenuTree(user.getRoleCode());
        for(String roleName:roleMenuList){
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        String password = user.getPassword();
        return new User(user.getUsername(),password,authorities);
    }


    /**
     * 生成token
     * @param user_id
     * @param password
     * @param code
     * @return
     */
    @Override
    public Object login(String user_id,String password,String code) {

        Map<String, Object>map = new HashMap<>();
        if(!code.equalsIgnoreCase(Constant.CODE)){
            map.put("code",String.valueOf(Constant.RESCODE_CAPTCHA_ERROR));
            map.put("msg","验证码错误");
            return map;
        }
        UserInfo user = new UserInfo();
        user.setUserId(user_id);
        user.setPassword(password);
        UserInfo userInfo = loginDao.getUserInfo(user);
        if (userInfo == null) {
            map.put("code",String.valueOf(Constant.USERNAME_PASSWORD_ERROR));
            map.put("msg","账号或密码错误，请重新检查");
            return map;
        }
        if (userInfo.getIsDisable() == 1) {
            map.put("code",String.valueOf(Constant.USERNAME_DISABLE));
            map.put("msg","你的用户属于禁用状态!");
            return map;
        }

        //生成token
        String token = JwtUtil.geneToken(userInfo);
        if(token!=null){
            //放入redis
            redisUtil.setString(userInfo.getUserId(), token);
            map.put("code",String.valueOf(Constant.RESCODE_SUCCESS));
            map.put("token",token);
            UserInfo userInfoToken =getLoginUser(token);
            userInfoToken.setPassword("*******");
            map.put("user",userInfoToken);
            return map;
        }else{
            map.put("code",String.valueOf(Constant.RESCODE_LOGIN_ERROR));
            map.put("msg","生成token失败!");
            return map;
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
     * 登录过滤
     * @param token
     * @return
     */

    @Override
    public Object loginFilter(String token){
        Map<String, Object>map = new HashMap<>();
        //是否是有效token字符串
        Claims claims = JwtUtil.checkJWT(token);
        if(claims==null){
            map.put("code","201");
            map.put("msg","无效token，请重新登录");
            return map;
        }

        String userId = JwtUtil.getUsername(token);
        if(userId!=""){
            String userByRedis = redisUtil.getString(userId);
            if(userByRedis==null){
                map.put("code","206");
                map.put("msg","token已失效，请重新登录");
                return map;
            }
        }

        map.put("code","200");
        map.put("msg","操作成功");
        return  map;

    }

    /**
     * 获取权限树
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> getRoleMenuTree(String roleId) {
        Map<String, Object>map = new HashMap<>();



        List<SysMenu> menuList = selectMenuAll("admin");
        if(roleId!=null){
            List<String> roleMenuList = loginDao.selectMenuTree(roleId);
            if(roleMenuList==null){
                return null;
            }
            for(int i=0;i<menuList.size();i++) {
                String menuPerm = menuList.get(i).getPerms();
                for (int j = 0; j < roleMenuList.size(); j++) {
                    String rolePerm = roleMenuList.get(j);
                    if(menuPerm.equals(rolePerm)){
                        menuList.get(i).setStatus(true);

                    }

                }
            }
            if(menuList!=null){
                return menuList;
            }
        }
        return null;
    }

    /**
     * 根据角色ID查询菜单
     *

     */
    @Override
    public Object roleMenuTreeData(String token) {

        Map<String, Object>map = new HashMap<>();

        //获取角色信息
        String roleId = JwtUtil.getRole(token);
        String userId = JwtUtil.getUsername(token);
        if(roleId==""){
            map.put("code","202");
            map.put("msg","无效token，获取角色信息失败");
            return map;
        }

        //获取数据
        List<Ztree> ztrees = new ArrayList<Ztree>();
        //获得对应用户的所有菜单
        List<SysMenu> menuList = selectMenuAll(userId);
        if (roleId!=null)
        {
            List<String> roleMenuList = loginDao.selectMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
            Ztree ztreeFirst = new Ztree();
            ztreeFirst.setTitle("首页");
            ztreeFirst.setHref("welcome/welcome.html");
            ztreeFirst.setTarget("_self");
            map.put("homeInfo",ztreeFirst);

            Ztree ztreeSecond = new Ztree();
            Map<String, Object>mapLogo = new HashMap<>();
            mapLogo.put("title","实验室管理系统");
            mapLogo.put("image","../static/img/logo.png");
            mapLogo.put("href","");
            map.put("logoInfo",mapLogo);

            map.put("menuInfo",ztrees);

        }
        else
        {
            ztrees = initZtree(menuList, null, true);
            map.put("roleNull",ztrees);
        }
        return map;
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
        Ztree ztreeThird = new Ztree();
        ztreeThird.setTitle("");
        ztreeThird.setIcon("fa fa-address-book");
        ztreeThird.setHref("");
        ztreeThird.setTarget("_self");
        ztreeThird.setChild(addZtreeNode(menuList));
        ztrees.add(ztreeThird);
        return ztrees;
    }

    //添加树节点
    public List<Ztree> addZtreeNode(List<SysMenu> menuList){
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (SysMenu menu : menuList)
        {
            //父Id！=0不直接添加到模块
            if(menu.getParentId()!=0){
                continue;
            }
            Ztree ztree = new Ztree();
            ztree.setTitle(menu.getTitle());
            ztree.setIcon(menu.getIcon());
            ztree.setHref(menu.getHref());
            ztree.setTarget(menu.getTarget());
            //查询当前节点是否有孩子节点
            for(SysMenu menuTmp : menuList){
                if(menu.getMenuId()==menuTmp.getParentId()){
                    List<SysMenu> childPerms = getChildPerms(menuList, menuTmp.getParentId());
                    if(childPerms!=null){
                        ztree.setChild(transMenuChild(childPerms));
                    }
                }
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    //子节点放入child中
    public List<Ztree> transMenuChild(List<SysMenu> menuList)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();

        for (SysMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
                ztree.setTitle(menu.getTitle());
                ztree.setIcon(menu.getIcon());
                ztree.setHref(menu.getHref());
                ztree.setTarget(menu.getTarget());
            ztrees.add(ztree);
        }
        return ztrees;

    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, Long parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
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
