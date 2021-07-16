package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/12 20:53
 *@version:1.1
 */

import com.miku.lab.entity.WxUser;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.WxUserService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "更新微信用户")
    @PostMapping("/updateWxUser")
    public ReturnResult updateWxUser(@RequestBody WxUser wxUser){
        int updateAffect = wxUserService.updateWxUser(wxUser);
        if(updateAffect!=0){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"保存成功");
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "保存失败");
        }
    }

}
