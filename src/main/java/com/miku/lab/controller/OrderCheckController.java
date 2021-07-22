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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
@Api(value="OrderCheckController",tags="预约审核接口")
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

    @ApiOperation(value = "查看对应预约仪器信息接口")
    @PostMapping("/getWxMachineLog")
    public ReturnResult getWxMachineLog(@RequestParam String openId){
        Object map = orderCheckService.getWxMachineLog(openId);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "获取所有待审核预约的信息")
    @GetMapping("/getAllBookingInfo")
    public ReturnResult getAllBookingInfo(){
        Object map = orderCheckService.getAllBookingInfo();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "分页获取所有待审核预约的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageBookingInfo")
    public ReturnResult getPageBookingInfo(@RequestParam int page, @RequestParam int limit){
        Object map = orderCheckService.getPageBookingInfo(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "搜索待审核预约的信息")
    @GetMapping("/searchBookingInfo")
    public ReturnResult searchBookingInfo(@RequestParam int page,@RequestParam int limit,
                                          @RequestParam String searchKey,@RequestParam String searchValue){
        Object map = orderCheckService.searchBookingInfo(page,limit,searchKey,searchValue);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "分页获取本次预约的仪器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageOrderMachine")
    public ReturnResult getPageOrderMachine(@RequestParam String openid,@RequestParam String booking_code,
                                            @RequestParam int page,@RequestParam int limit){
        Object map = orderCheckService.getPageOrderMachine(openid,booking_code,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "审核预约信息")
    @PostMapping("/checkBooking")
    public ReturnResult checkBooking(@RequestBody Map<String,Object> param){
        int res = orderCheckService.checkBooking(param);
        if(res!=0){
            return AjaxUtil.success("成功", Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "审核失败");
        }
    }

    @ApiOperation(value = "获取所有预约记录的信息")
    @GetMapping("/getAllBookingLog")
    public ReturnResult getAllBookingLog(){
        Object map = orderCheckService.getAllBookingLog();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "分页获取所有预约记录的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageBookingLog")
    public ReturnResult getPageBookingLog(@RequestParam int page, @RequestParam int limit){
        Object map = orderCheckService.getPageBookingLog(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "搜索预约记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/searchBookingLog")
    public ReturnResult searchBookingLog(@RequestParam int page,@RequestParam int limit,
                                          @RequestParam String searchKey,@RequestParam String searchValue){
        Object map = orderCheckService.searchBookingLog(page,limit,searchKey,searchValue);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
