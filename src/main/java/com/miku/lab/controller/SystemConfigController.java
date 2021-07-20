package com.miku.lab.controller;

import com.miku.lab.dao.SystemConfigDao;
import com.miku.lab.entity.Config;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.SystemConfigService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/config")
@Api(value="SystemConfigController",tags="系统配置接口")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    /**
     * 获取所有系统配置
     * @return
     */
    @GetMapping("/getAllSystemConfig")
    @ApiOperation(value="获取所有系统配置接口")
    @ApiImplicitParam
    public ReturnResult getAllSystemConfig(){
        List<Map> list = systemConfigService.getAllSystemConfig();
        if(list==null){
            return AjaxUtil.error(Constant.RESCODE_EXCEPTION,"查询失败");
        }
        if(list.size()==0){
            return AjaxUtil.error(Constant.RESCODE_NOEXIST,"查询结果为空");
        }
        return AjaxUtil.success(list,Constant.RESCODE_SUCCESS_MSG,list.size());
    }


    /**
     * 获取配置文件接口
     * @return
     */
    @ApiOperation(value = "获取配置文件接口")
    @ApiImplicitParam()
    @GetMapping("/getAllConfig")
    public ReturnResult getAllConfig(){
        Object map = systemConfigService.getAllConfig();
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
        Object map = systemConfigService.getPageConfig(page,limit);
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
        Object map = systemConfigService.getConfigDetail(id);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="更新配置信息")
    @PostMapping("/updateConfig")
    public ReturnResult updateConfig(@RequestBody Config config){
        String map = (String) systemConfigService.updateConfig(config);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }

    @ApiOperation(value="查询仪器分类接口")
    @GetMapping("/searchConfig")
    public ReturnResult searchConfig(@RequestParam String searchKey,@RequestParam String searchValue,
                                   @RequestParam String page, @RequestParam String limit ){
        Object map = systemConfigService.searchConfig(searchKey,searchValue,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
