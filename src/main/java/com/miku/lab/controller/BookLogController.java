package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 14:35
 *@version:1.1
 */

import com.miku.lab.entity.LabInfo;
import com.miku.lab.entity.OrderCheck;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import com.miku.lab.util.TimeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/booking")
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

    @ApiOperation(value = "查看所有预约仪器信息")
    @ApiImplicitParam()
    @GetMapping("/getBookMachine")
    public ReturnResult getLabList(){
        Object map = bookLogService.getAllBookMachine();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "预约仪器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="openId",value="微信openid",required=true),
            @ApiImplicitParam(name="machine_id",value="仪器编号",required=true),
            @ApiImplicitParam(name="book_number",value="预约数量",required=true)
    })
    @GetMapping("/addMachineCheck")
    public ReturnResult addMachineCheck(@RequestParam String openId,@RequestParam String machine_id,@RequestParam String book_number){
        String msg = bookLogService.addBookMachineLog(openId,machine_id,book_number);
        return AjaxUtil.error(Constant.RESCODE_SUCCESS, msg);
    }

    @ApiOperation(value = "预约实验室接口")
    @PostMapping("/addLabCheck")
    public ReturnResult addLabInfo(@RequestBody OrderCheck orderCheck){
        String msg = bookLogService.addLabLog(orderCheck);
        return AjaxUtil.error(Constant.RESCODE_SUCCESS, msg);
    }
}
