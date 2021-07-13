package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/7 12:58
 *@version:1.1
 */

import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.vo.LoginResultVo;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.UserInfoService;
import com.miku.lab.util.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 获取验证码
     * @param request
     * @param response
     */
    @ApiOperation(value = "验证码获取")
    @ApiImplicitParam()
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *登录验证
     * @param token
     * @param userId
     * @param password
     * @param captcha
     * @return
     */
    @ApiOperation(value = "用户登录接口")
    @ApiImplicitParam()
    @GetMapping("/login")
    public ReturnResult login(@RequestParam String token, @RequestParam String userId, @RequestParam String password, @RequestParam String captcha) {
        RedisUtil redisUtil = new RedisUtil();
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setPassword(password);
        if(!captcha.equalsIgnoreCase(Constant.CODE)){
            return AjaxUtil.error(Constant.RESCODE_CAPTCHA_ERROR,"验证码错误");
        }
        UserInfo userInfo = userInfoService.login(token,user,captcha);
        if(userInfo!=null){
            String newToken = JwtUtil.geneToken(userInfo);
            redisUtil.setString(userInfo.getUserId(),newToken);
            userInfo.setPassword("*******");
            return AjaxUtil.success(new LoginResultVo().setToken(newToken).setUserInfo(userInfo),Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_PASSWORD_ERROR,"密码错误");
        }
    }


    /**
     * 获取所有用户信息
     * @return
     */
    @ApiOperation(value = "获取所有的用户信息")
    @ApiImplicitParam()
    @GetMapping("/getAllUser")
    public ReturnResult getAllUser(){
        List<UserInfo> userInfos = userInfoService.getAllUser();
        if(userInfos!=null){
            return AjaxUtil.success(userInfos,Constant.RESCODE_SUCCESS,userInfos.size());
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "获取一个用户信息")
    @ApiImplicitParam
    @GetMapping("/getOneUser")
    public ReturnResult getOneUser(@RequestParam String user_id){
        Map<String,Object> map = userInfoService.getOneUser(user_id);
        if(map!=null){
            return AjaxUtil.success(map,Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
