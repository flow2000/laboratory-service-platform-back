package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 15:19
 *@version:1.1
 */

import com.miku.lab.entity.SystemOperation;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.OperService;
import com.miku.lab.service.SuggestService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class SystemOperController {

    @Autowired
    private OperService operService;

    @ApiOperation(value = "系统操作接口")
    @ApiImplicitParam()
    @GetMapping("/getOper")
    public ReturnResult getOper(){
        Object map = operService.getAllOper();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
