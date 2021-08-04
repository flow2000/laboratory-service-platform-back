package com.miku.lab.controller;/*
 *@author 邓涛
 *@data 2021/8/4 20:16
 *@version:1.1
 */

import com.miku.lab.entity.SysMenu;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MenuService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(value="MenuController",tags="菜单接口")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取所有菜单接口")
    @ApiImplicitParam()
    @GetMapping("/getAllMenu")
    public ReturnResult getAllMenu(){
        List<SysMenu> allMenu = menuService.getAllMenu();
        if(allMenu!=null){
            return AjaxUtil.success(allMenu, 0,allMenu.size());
        }else {
            return AjaxUtil.error(Constant.RESCODE_MENU_ERROR,"获取失败");
        }
    }
}
