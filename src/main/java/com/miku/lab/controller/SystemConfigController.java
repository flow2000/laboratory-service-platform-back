package com.miku.lab.controller;

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.SystemConfigService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
}
