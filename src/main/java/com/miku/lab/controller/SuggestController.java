package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 14:59
 *@version:1.1
 */

import com.miku.lab.entity.Suggestion;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.service.SuggestService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggest")
@Api(value="SuggestController",tags="反馈接口")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    @ApiOperation(value = "获取反馈记录接口")
    @ApiImplicitParam()
    @GetMapping("/getSuggestion")
    public ReturnResult getSuggestion(){
        Object map = suggestService.getAllSuggestion();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "提交反馈信息接口")
    @ApiImplicitParam()
    @GetMapping("/addSuggest")
    public ReturnResult addSuggest(@RequestBody Suggestion suggestion){
        int flag = suggestService.addSuggest(suggestion);
        if(flag!=0){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }else{
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加信息失败");
        }
    }
}
