package com.miku.lab.controller;

import com.miku.lab.entity.LoginBody;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.Ztree;
import com.miku.lab.entity.vo.ReturnResult;

import com.miku.lab.service.LoginService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

    @PostMapping("/login")
    public ReturnResult login(@RequestBody LoginBody loginBody)
    {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode());
        ReturnResult success = AjaxUtil.success(token, Constant.RESCODE_SUCCESS, 1);
        return success;
    }


    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(@RequestHeader String token)
    {
        List<Ztree> ztrees = loginService.roleMenuTreeData(token);
        return ztrees;
    }



    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
