package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/6/1 20:51
 *@version:1.1
 */

import com.miku.lab.entity.UserInfo;
import com.miku.lab.service.UserInfoService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.ResultUtil;
import com.miku.lab.vo.LoginResultVo;
import com.miku.lab.vo.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParam()
    @PostMapping("/login")
    public ReturnResult login(@RequestParam String userId,
                              @RequestParam String password,@RequestParam int isRemember){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setPassword(password);
        String token = userInfoService.login(userInfo,isRemember);
        if(token==null){
            return ResultUtil.error(Constant.RESCODE_EXCEPTION,"登录失败");
        }
        userInfo= userInfoService.findUserById(userId);
        LoginResultVo loginResultVo = new LoginResultVo().setUserInfo(userInfo).setToken(token);
        return ResultUtil.success(loginResultVo,Constant.RESCODE_SUCCESS,1);
    }
}
