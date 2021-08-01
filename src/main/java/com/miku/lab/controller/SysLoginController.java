package com.miku.lab.controller;

import com.google.gson.Gson;
import com.miku.lab.entity.LoginBody;
import com.miku.lab.entity.SysMenu;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.Ztree;
import com.miku.lab.entity.vo.ReturnResult;

import com.miku.lab.service.LoginService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author 涛
 */
@RestController
@Api(value="SysLoginController",tags="后台系统登录接口")
public class SysLoginController
{


    @Autowired
    private LoginService loginService;

    @ApiOperation(value="登录")
    @PostMapping("/login")
    public ReturnResult login(@RequestBody LoginBody loginBody)
    {
        Object token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode());
        ReturnResult success = AjaxUtil.success(token, Constant.RESCODE_SUCCESS, 1);
        return success;
    }


    /**
     * 加载角色菜单列表树
     */
    @ApiOperation(value="加载左边菜单列表树")
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public Object roleMenuTreeData(@RequestParam String token)
    {
        Object ztrees =  loginService.roleMenuTreeData(token);
        return ztrees;
    }
    /**
 * 登录过滤
 */
@ApiOperation(value="登录过滤")
@GetMapping("/loginFilter")
@ResponseBody
public ReturnResult loginFilter(@RequestHeader String token)
{
    Object map =  loginService.loginFilter(token);
    return AjaxUtil.success(map,Constant.RESCODE_SUCCESS,1);
}


    /**
     * 权限管理菜单树
     */
    @ApiOperation(value="权限管理菜单树")
    @GetMapping("/getRoleMenuTree")
    @ResponseBody
    public ReturnResult getRoleMenuTree(@RequestParam String roleCode)
    {
        List<SysMenu> roleMenuTree = loginService.getRoleMenuTree(roleCode);
        if(roleMenuTree==null){
            return AjaxUtil.error(Constant.RESCODE_LOGIN_ERROR,"验证失败");
        }
        return AjaxUtil.success(roleMenuTree,0,roleMenuTree.size());
    }



//    @GetMapping("/unauth")
//    public String unauth()
//    {
//        return "error/unauth";
//    }
}
