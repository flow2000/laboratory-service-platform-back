package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/12 20:53
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.WxUserService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxUserController {

    @Autowired
    private WxUserService  wxUserService;

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

}
