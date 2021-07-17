package com.miku.lab.controller;

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Role;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.RoleService;
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

    @ApiOperation(value="获取角色详细信息接口")
    @ApiImplicitParam(name = "roleCode",value="角色编号",required=true)
    @GetMapping("/getRoleDetail")
    public ReturnResult getSortDetail(@RequestParam String roleCode){
        Object map = roleService.getRoleDetail(roleCode);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="更新角色信息接口")
    @PostMapping("/updateRole")
    public ReturnResult updateRole(@RequestBody Role role){
        String map = (String) roleService.updateRole(role);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }

    @ApiOperation(value="添加角色信息接口")
    @PostMapping("/addRole")
    public ReturnResult addRole(@RequestBody Role role){
        int flag = roleService.addRole(role);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，请勿重复添加角色");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }

    @ApiOperation(value="删除文章分类接口")
    @PostMapping("/delRole")
    public ReturnResult delRole(@RequestParam String roleCode){
        int flag = roleService.delRole(roleCode);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"删除失败");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"删除成功");
        }
    }

    @ApiOperation(value="批量删除文章分类接口")
    @PostMapping("/combineDelRole")
    public ReturnResult combineDelRole(@RequestParam String ids){
        String[] roleCodes = ids.split(",");
        int count=roleCodes.length;
        int delCount=0;
        for(String roleCode:roleCodes){
            int flag = roleService.delRole(roleCode);
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
    @GetMapping("/searchRole")
    public ReturnResult searchRole(@RequestParam String searchKey,@RequestParam String searchValue,
                                   @RequestParam String page, @RequestParam String limit ){
        Object map = roleService.searchRole(searchKey,searchValue,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
