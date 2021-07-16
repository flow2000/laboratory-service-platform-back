package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 15:38
 *@version:1.1
 */

import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.ArticleService;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Api(value="ArticleController",tags="文章接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取所有文章")
//    @ApiImplicitParam()
//    @RequestMapping("/getArticle")
    @GetMapping("/getArticle")
    public ReturnResult getArticle(){
        Object map = articleService.getAllArticle();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value = "获取所有文章分类")
//    @ApiImplicitParam()
//    @RequestMapping("/getArticle")
    @GetMapping("/getArticleSort")
    public ReturnResult getArticleSort(){
        Object map = articleService.getAllArticleSort();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
