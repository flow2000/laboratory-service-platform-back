package com.miku.lab.controller;

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.StatisticsService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value="获取用户统计，仪器数量，预约次数，文章存储数")
    @ApiImplicitParam
    @GetMapping("/getTotal")
    public ReturnResult getTotal(){
        Map<String,Object> map = statisticsService.getTotal();
        return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
    }

    @ApiOperation(value="获取仪器分类统计")
    @ApiImplicitParam
    @GetMapping("/getMachineStatistics")
    public ReturnResult getMachineType(){
        return AjaxUtil.success(statisticsService.getMachineStatistics(), Constant.RESCODE_SUCCESS,1);
    }

    @ApiOperation(value="获取实验室分类统计")
    @ApiImplicitParam
    @GetMapping("/getLabStatistics")
    public ReturnResult getLabType(){
        return AjaxUtil.success(statisticsService.getLabType(), Constant.RESCODE_SUCCESS,1);
    }

    @ApiOperation(value="获取六个月前每个月的用户统计，仪器数量，预约次数，文章存储数")
    @ApiImplicitParam
    @GetMapping("/getSixMonthsStatistics")
    public ReturnResult getSixMonthsStatistics(){
        Map<String,Object> map = statisticsService.getSixMonthsStatistics();
        return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
    }
}
