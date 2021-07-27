package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 14:35
 *@version:1.1
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miku.lab.entity.*;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import com.miku.lab.util.StringUtil;
import com.miku.lab.util.TimeUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
@Api(value="BookLogController",tags="预约接口")
public class BookLogController {

    @Autowired
    private BookLogService bookLogService;



    @ApiOperation(value = "获取预约记录接口")
    @ApiImplicitParam()
    @GetMapping("/getBookingLog")
    public ReturnResult getBookingLog(){
        Object map = bookLogService.getAllBookLog();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "查看对应openid预约仪器信息")
    @ApiImplicitParam(name = "openId",value="微信授权Id",required=true)
    @GetMapping("/getBookMachine")
    public ReturnResult getLabList(@RequestParam String openId){
        Object map = bookLogService.getAllBookMachineById(openId);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }


    @ApiOperation(value = "撤销申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId",value="微信授权Id",required=true),
            @ApiImplicitParam(name = "lab_id", value = "实验室编号", required = true)
    })
    @PostMapping("/drawApply")
    public ReturnResult drawApply(@RequestParam String openId,@RequestParam String lab_id){
        return bookLogService.drawApply(openId,lab_id);
    }


    @ApiOperation(value = "提交按钮实验室+仪器接口")
    @PostMapping("/orderInfo")
    public ReturnResult orderInfo(@RequestBody Map<String, Object>map){
       return bookLogService.addBookingLog(map);
    }
}
