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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value="UserController",tags="用户接口")
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
            return AjaxUtil.success(userInfos,Constant.RESCODE_SUCCESS_MSG,userInfos.size());
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 分页获取用户信息
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="分页获取用户信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page",value="页数",required=true),
        @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageUser")
    public ReturnResult getPageUser(@RequestParam String page, @RequestParam String limit){
        Object map = userInfoService.getPageUser(page,limit);
        if(map!=null){
            return AjaxUtil.success(map,Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }


    /**
     * 获取一个用户信息
     * @param user_id
     * @return
     */
    @ApiOperation(value = "获取一个用户信息")
    @ApiImplicitParam
    @GetMapping("/getOneUser")
    public ReturnResult getOneUser(@RequestParam String user_id){
        Object object = userInfoService.getOneUser(user_id);
        if(object!=null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 验证用户密码
     * @param user_id
     * @param password
     * @return
     */
    @ApiOperation(value="验证用户密码")
    @ApiImplicitParam
    @PostMapping("/verifyUserPassword")
    public ReturnResult verifyUserPassword(@RequestParam String user_id,@RequestParam String password){
        int res = userInfoService.verifyUserPassword(user_id,password);
        if(res == 1){
            return AjaxUtil.success("验证成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_SUCCESS_MSG, "密码错误");
        }
        return AjaxUtil.error(Constant.RESCODE_NOEXIST, "查询失败");
    }

    /**
     * 修改用户密码
     * @param user_id
     * @param password
     * @return
     */
    @ApiOperation(value="修改用户密码")
    @ApiImplicitParam
    @PostMapping("/updatePersonPassword")
    public ReturnResult updatePersonPassword(@RequestParam String user_id,@RequestParam String password,@RequestParam String updater){
        int res = userInfoService.updatePersonPassword(user_id,password,updater);
        if(res == 1){
            return AjaxUtil.success("修改成功",Constant.RESCODE_SUCCESS,res);
        }
        return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
    }

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    @ApiOperation(value="修改个人信息")
    @ApiImplicitParam
    @PostMapping("/updatePersonInfo")
    public ReturnResult updateUserInfo(@RequestBody Map<String,Object>param){
        int res = userInfoService.updateUserInfo(param);
        if(res == 1){
            return AjaxUtil.success("修改成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 添加用户
     * @param param
     * @return
     */
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam
    @PostMapping("/addUser")
    public ReturnResult addUser(@RequestBody Map<String,Object>param){
        int res = userInfoService.addUser(param);
        if(res == 1){
            return AjaxUtil.success("添加成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 删除用户(逻辑删除)
     * @param param
     * @return
     */
    @ApiOperation(value = "删除用户(逻辑删除)")
    @ApiImplicitParam
    @PostMapping("/deleteUser")
    public ReturnResult deleteUser(@RequestBody Map<String,Object>param){
        int res = userInfoService.deleteUser(param);
        if(res >= 1){
            return AjaxUtil.success("删除成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 搜索用户
     * @param param
     * @return
     */
    @ApiOperation(value = "搜索用户")
    @ApiImplicitParam
    @GetMapping("/searchUser")
    public ReturnResult searchUser(@RequestParam int page,@RequestParam int limit,
                                   @RequestParam String searchKey,@RequestParam String searchValue){
        Object object = userInfoService.searchUser(page,limit,searchKey,searchValue);
        if(object != null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS,1);
        }
        return AjaxUtil.error(Constant.RESCODE_NOEXIST, "搜索失败");
    }
}
