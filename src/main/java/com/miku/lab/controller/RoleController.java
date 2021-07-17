package com.miku.lab.controller;

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.RoleService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
@Api(value="RoleController",tags="角色接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色
     * @return
     */
    @ApiOperation(value = "获取所有角色")
    @ApiImplicitParam()
    @GetMapping("/getAllRole")
    public ReturnResult getAllRole(){
        List<Map> list = roleService.getAllRole();
        if(list!=null){
            return AjaxUtil.success(list,Constant.RESCODE_SUCCESS_MSG,list.size());
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 分页获取角色信息
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="分页获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageRole")
    public ReturnResult getPageRole(@RequestParam String page, @RequestParam String limit){
        Object object = roleService.getPageRole(page,limit);
        if(object!=null){
            return AjaxUtil.success(object, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }
}
