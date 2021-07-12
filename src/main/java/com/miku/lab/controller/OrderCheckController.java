package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/12 21:28
 *@version:1.1
 */

import com.miku.lab.entity.OrderCheck;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.OrderCheckService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderCheckController {

    @Autowired
    private OrderCheckService orderCheckService;

    @ApiOperation(value = "获取所有预约审核接口")
    //@ApiImplicitParam(name="mobile",value="手机号",required=true)
    @ApiImplicitParam()
    @GetMapping("/getOrderCheck")
    public ReturnResult getOrderCheck(){
        Object map = orderCheckService.getAllOrderCheck();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

}
