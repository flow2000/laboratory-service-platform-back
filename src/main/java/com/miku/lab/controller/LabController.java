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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/lab")
@Api(value="LabController",tags="实验室接口")
public class LabController {

    @Autowired
    private LabService labService;

    @ApiOperation(value = "获取实验室接口")
    @ApiImplicitParam()
    @GetMapping("/getLabList")
    public ReturnResult getLabList(){
        Object map = labService.getAllLabInfo();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="分页获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageLab")
    public ReturnResult getPageLab(@RequestParam String page, @RequestParam String limit){
        Object map = labService.getPageLab(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    /**
     * 获取一个实验室信息
     * @param lab_id
     * @return
     */
    @ApiOperation(value = "获取一个实验室信息")
    @ApiImplicitParam
    @GetMapping("/getOneLab")
    public ReturnResult getOneLab(@RequestParam String lab_id){
        Object object = labService.getOneLab(lab_id);
        if(object!=null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 修改实验室基本信息
     * @param param
     * @return
     */
    @ApiOperation(value="修改实验室基本信息")
    @ApiImplicitParam
    @PostMapping("/updateLabInfo")
    public ReturnResult updateLabInfo(@RequestBody Map<String,Object>param){
        int res = labService.updateLabInfo(param);
        if(res == 1){
            return AjaxUtil.success("修改成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 修改实验室有效状态
     * @param param
     * @return
     */
    @ApiOperation(value="修改实验室有效状态")
    @ApiImplicitParam
    @PostMapping("/updateLabValid")
    public ReturnResult updateLabDisable(@RequestBody Map<String,Object>param){
        int res = labService.updateLabValid(param);
        if(res == 1){
            return AjaxUtil.success("修改成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 修改实验室使用状态
     * @param param
     * @return
     */
    @ApiOperation(value="修改实验室使用状态")
    @ApiImplicitParam
    @PostMapping("/updateLabUsing")
    public ReturnResult updateLabUsing(@RequestBody Map<String,Object>param){
        int res = labService.updateLabUsing(param);
        if(res == 1){
            return AjaxUtil.success("修改成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 添加实验室
     * @param param
     * @return
     */
    @ApiOperation(value = "添加实验室")
    @ApiImplicitParam
    @PostMapping("/addLab")
    public ReturnResult addUser(@RequestBody Map<String,Object>param){
        int res = labService.addLab(param);
        if(res == 1){
            return AjaxUtil.success("添加成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 删除实验室(逻辑删除)
     * @param param
     * @return
     */
    @ApiOperation(value = "删除实验室(逻辑删除)")
    @ApiImplicitParam
    @PostMapping("/deleteLab")
    public ReturnResult deleteUser(@RequestBody Map<String,Object>param){
        int res = labService.deleteLab(param);
        if(res >= 1){
            return AjaxUtil.success("删除成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 搜索实验室
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "搜索实验室")
    @ApiImplicitParam
    @GetMapping("/searchLab")
    public ReturnResult searchUser(@RequestParam int page,@RequestParam int limit,
                                   @RequestParam String searchKey,@RequestParam String searchValue){
        Object object = labService.searchLab(page,limit,searchKey,searchValue);
        if(object != null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS,1);
        }
        return AjaxUtil.error(Constant.RESCODE_NOEXIST, "搜索失败");
    }
}
