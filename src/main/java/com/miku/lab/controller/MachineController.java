package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/8 16:41
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.AjaxUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @ApiOperation(value = "获取仪器接口")
    @ApiImplicitParam()
    @RequestMapping("/getMachineList")
    public ReturnResult getAllUser(){
        Object map = machineService.getAllMachine();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
