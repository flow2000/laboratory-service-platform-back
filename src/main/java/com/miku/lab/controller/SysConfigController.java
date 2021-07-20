package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/20 14:40
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Config;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.ConfigService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
@Api(value="SysConfigController",tags="系统设置接口")
public class SysConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 获取配置文件接口
     * @return
     */
    @ApiOperation(value = "获取配置文件接口")
    @ApiImplicitParam()
    @GetMapping("/getAllConfig")
    public ReturnResult getAllConfig(){
        Object map = configService.getAllConfig();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分页仪器分类接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageConfig")
    public ReturnResult getPageConfig(@RequestParam String page, @RequestParam String limit){
        Object map = configService.getPageConfig(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取单个配置详细信息")
    @ApiImplicitParam(name = "id",value="配置编号",required=true)
    @GetMapping("/getConfigDetail")
    public ReturnResult getConfigDetail(@RequestParam String id){
        Object map = configService.getConfigDetail(id);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="更新配置信息")
    @PostMapping("/updateConfig")
    public ReturnResult updateConfig(@RequestBody Config config){
        String map = (String) configService.updateConfig(config);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }
}
