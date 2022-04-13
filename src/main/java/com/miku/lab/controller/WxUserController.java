package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/12 20:53
 *@version:1.1
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.WxUser;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.WxUserService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import com.miku.lab.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx")
@Api(value="WxUserController",tags="微信用户接口")
public class WxUserController {

    @Autowired
    private WxUserService  wxUserService;

    RedisUtil redisUtil = new RedisUtil();


    @ApiOperation(value = "获取所有微信用户信息")
    @ApiImplicitParam()
    @GetMapping("/getAllWxUser")
    public ReturnResult getAllWxUser(){
        Object map = wxUserService.getAllWxUser();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取微信信息失败");
        }
    }
    @ApiOperation(value = "更新微信用户个人信息")
    @PostMapping("/updateWxUser")
    public ReturnResult updateWxUser(@RequestBody WxUser wxUser){
        int updateAffect = wxUserService.updateWxUser(wxUser);
        if(updateAffect!=0){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"保存成功");
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "保存失败，请确保你已经登录");
        }
    }

    @ApiOperation(value = "更新微信是否推送信息")
    @PostMapping("/updateWxPush")
    public ReturnResult updateWxPush(@RequestParam String openId,@RequestParam String isReceptMsg,@RequestParam String isReceptPush){
        int updateAffect = wxUserService.updateWxPush(openId, isReceptMsg, isReceptPush);
        if(updateAffect!=0){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"保存成功");
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "保存失败，请确保你已经登录");
        }
    }


    @ApiOperation(value="添加微信用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId",value="微信授权id",required=true),
            @ApiImplicitParam(name = "username", value = "昵称", required = true)
    })
    @PostMapping("/addWxUser")
    public ReturnResult addWxUser(@RequestParam String openId,@RequestParam String username){
        int flag = wxUserService.addWxUser(openId,username);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，请勿重复添加信息");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }

    @ApiOperation(value="查询单一微信用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId",value="微信授权id",required=true),
    })
    @PostMapping("/searchWxUser")
    public ReturnResult searchWxUser(@RequestParam String openId){
        WxUser wxUser = wxUserService.searchWxUserById(openId);
        if (wxUser != null) {
            return AjaxUtil.success(wxUser, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取微信信息失败");
        }
    }


    @ApiOperation(value="获取微信openid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "js_code",value="登录凭证 js_code",required=true),
    })
    @PostMapping("/wxGetUnionId")
    public ReturnResult getMiniAppOpenId(@RequestParam String js_code) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", "wx53295620b3e8adf1");
        data.put("secret", "f2d8491867cc5926deee9df2dddd7955");
        data.put("js_code", js_code);
        data.put("grant_type", "authorization_code");
        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        String openid = obj.getString("openid");
        String sessionKey = obj.getString("session_key");
        redisUtil.setString("openid", openid);
        redisUtil.setString("sessionKey", sessionKey);
        Map<String, String> result = new HashMap<String, String>();
        result.put("openid",openid);
        result.put("sessionKey",sessionKey);
        return AjaxUtil.success(result, Constant.RESCODE_SUCCESS, 1);
    }
}
