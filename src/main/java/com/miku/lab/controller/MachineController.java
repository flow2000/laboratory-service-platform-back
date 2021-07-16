package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/8 16:41
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.AjaxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/machine")
@Api(value="MachineController",tags="仪器接口")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @ApiOperation(value = "获取仪器接口")
    @ApiImplicitParam()
    @GetMapping("/getMachineList")
    public ReturnResult getAllUser(){
        Object map = machineService.getAllMachine();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="分页获取仪器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageMachine")
    public ReturnResult getPageMachine(@RequestParam String page, @RequestParam String limit){
        Object map = machineService.getPageMachine(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "获取所有仪器分类接口")
    @ApiImplicitParam()
    @GetMapping("/getMachineType")
    public ReturnResult getMachineType(){
        List<Machine_sort> list = machineService.getMachineType();
        if(list!=null){
            return AjaxUtil.success(list, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取所有仪器分类接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageMachineSort")
    public ReturnResult getPageMachineSort(@RequestParam String page, @RequestParam String limit){
        Object map = machineService.getPageMachineSort(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分类详细信息接口")
    @ApiImplicitParam(name = "sortId",value="分类编号",required=true)
    @GetMapping("/getMachineSortDetail")
    public ReturnResult getMachineSortDetail(@RequestParam String sortId){
        Object map = machineService.getSortDetail(sortId);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="更新仪器分类接口")
    @PostMapping("/updateSort")
    public ReturnResult updateSort(@RequestBody Machine_sort machine_sort){
        String map = (String) machineService.updateMachineSort(machine_sort);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }

    @ApiOperation(value="添加仪器分类接口")
    @PostMapping("/addSort")
    public ReturnResult addSort(@RequestBody Machine_sort machine_sort){
        int flag = machineService.addMachineSort(machine_sort);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，请勿重复添加分类");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }

    @ApiOperation(value="删除仪器分类接口")
    @PostMapping("/delSort")
    public ReturnResult delSort(@RequestParam String sortId){
        int flag = machineService.delMachineSort(sortId);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"删除失败");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"删除成功");
        }
    }

    @ApiOperation(value="删除仪器分类接口")
    @PostMapping("/combineDelSort")
    public ReturnResult combineDelSort(@RequestParam String ids){
        String[] sortIds = ids.split(",");
        int count=sortIds.length;
        int delCount=0;
        for(String sortId:sortIds){
            int flag = machineService.delMachineSort(sortId);
            if (flag == 1) {
                delCount++;
            }
        }
        if(delCount==count){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"批量删除成功");
        }else{
            return AjaxUtil.success("已删除",Constant.RESCODE_DELETEERROR,delCount);
        }
    }

    @ApiOperation(value="查询仪器分类接口")
    @GetMapping("/searchSort")
    public ReturnResult searchSort(@RequestParam String searchKey,@RequestParam String searchValue){
        Object map = machineService.searchSort(searchKey,searchValue);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
