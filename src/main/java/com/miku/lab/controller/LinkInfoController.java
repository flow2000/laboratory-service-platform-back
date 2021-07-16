package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/16 15:46
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.LabService;
import com.miku.lab.service.LinkInfoService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkInfoController {

    @Autowired
    private LinkInfoService linkInfoService;

    @ApiOperation(value = "获取联系方式接口")
    @ApiImplicitParam()
    @GetMapping("/getLinkInfo")
    public ReturnResult getLinkInfo(){
        Object map = linkInfoService.getLinkInfoList();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
