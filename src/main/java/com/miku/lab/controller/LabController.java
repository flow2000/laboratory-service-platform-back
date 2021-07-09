package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 13:41
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.LabService;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab")
public class LabController {

    @Autowired
    private LabService labService;

    @ApiOperation(value = "获取实验室接口")
    @ApiImplicitParam()
    @RequestMapping("/getLabList")
    public ReturnResult getLabList(){
        Object map = labService.getAllLabInfo();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
