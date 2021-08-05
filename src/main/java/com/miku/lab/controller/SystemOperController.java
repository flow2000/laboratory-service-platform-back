package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 15:19
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.SystemOperation;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.OperService;
import com.miku.lab.service.SuggestService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import com.miku.lab.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/operation")
@Api(value="SystemOperController",tags="操作接口")
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

    @ApiOperation(value="获取所有操作记录类接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageOper")
    public ReturnResult getPageOper(@RequestParam String page, @RequestParam String limit){
        Object map = operService.getPageOper(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="查询记录接口")
    @GetMapping("/searchOper")
    public ReturnResult searchOper(@RequestParam String searchKey,@RequestParam String searchValue,
                                   @RequestParam String page, @RequestParam String limit ){
        Object map = operService.searchOper(searchKey,searchValue,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="删除记录接口")
    @PostMapping("/delOper")
    public ReturnResult delOper(@RequestParam String id){
        int flag = operService.delOper(id);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"删除失败");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"删除成功");
        }
    }

    @ApiOperation(value="批量删除记录接口")
    @PostMapping("/combineDelOper")
    public ReturnResult combineDelOper(@RequestParam String ids){
        String[] sortIds = ids.split(",");
        int count=sortIds.length;
        int delCount=0;
        for(String id:sortIds){
            int flag = operService.delOper(id);
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
    @ApiOperation(value="添加日志接口")
    @PostMapping("/addOper")
    public ReturnResult addOper(@RequestBody SystemOperation operation, HttpServletRequest request){
        operation.setIpAddress(IpUtil.getIpAddr(request));
        int flag = operService.addOper(operation);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，记录重复");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }
}
