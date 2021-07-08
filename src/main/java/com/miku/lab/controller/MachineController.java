package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/8 16:41
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import com.miku.lab.entity.UserInfo;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/machine")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @RequestMapping("/getMachineList")
    public ReturnResult getAllUser(){
        List<Machine> machines = machineService.getAllMachine();
        if(machines!=null){
            return ResultUtil.success(machines, Constant.RESCODE_SUCCESS,machines.size());
        }else{
            return ResultUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
