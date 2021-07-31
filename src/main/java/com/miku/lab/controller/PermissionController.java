package com.miku.lab.controller;/*
 *@author 邓涛
 *@data 2021/7/31 16:32
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.PermissionService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/permission")
@Api(value="PermissionController",tags="权限管理接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "修改权限")
    @ApiImplicitParam()
    @PostMapping("/updatePermission")
    public ReturnResult updatePermission(@RequestBody Map<String, Object> resMap){

       int flag =  permissionService.updatePermission(resMap);
       if(flag!=0){
           return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"操作成功");
       }else{
           return AjaxUtil.error(Constant.RESCODE_MODIFYERROR,"更新失败");
       }
    }
}
