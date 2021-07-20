package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 15:38
 *@version:1.1
 */

import com.miku.lab.entity.ArticleSort;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.ArticleService;
import com.miku.lab.service.BookLogService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getArticleSort")
    public ReturnResult getArticleSort(){
        Object map = articleService.getAllArticleSort();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分页文章分类接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageArticleSort")
    public ReturnResult getPageArticleSort(@RequestParam String page, @RequestParam String limit){
        Object map = articleService.getPageMachineSort(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分类详细信息接口")
    @ApiImplicitParam(name = "sortId",value="分类编号",required=true)
    @GetMapping("/getSortDetail")
    public ReturnResult getSortDetail(@RequestParam String sortId){
        Object map = articleService.getSortDetail(sortId);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
    @ApiOperation(value="更新文章分类接口")
    @PostMapping("/updateSort")
    public ReturnResult updateSort(@RequestBody ArticleSort articleSort){
        String map = (String) articleService.updateSort(articleSort);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }

    @ApiOperation(value="添加文章分类接口")
    @PostMapping("/addSort")
    public ReturnResult addSort(@RequestBody ArticleSort articleSort){
        int flag = articleService.addSort(articleSort);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，请勿重复添加分类");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }

    @ApiOperation(value="删除文章分类接口")
    @PostMapping("/delSort")
    public ReturnResult delSort(@RequestParam String sortId){
        int flag = articleService.delSort(sortId);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"删除失败");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"删除成功");
        }
    }

    @ApiOperation(value="批量删除文章分类接口")
    @PostMapping("/combineDelSort")
    public ReturnResult combineDelSort(@RequestParam String ids){
        String[] sortIds = ids.split(",");
        int count=sortIds.length;
        int delCount=0;
        for(String sortId:sortIds){
            int flag = articleService.delSort(sortId);
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
    @PostMapping("/searchSort")
    public ReturnResult searchSort(@RequestParam String searchKey,@RequestParam String searchValue,
                                   @RequestParam String page, @RequestParam String limit ){
        Object map = articleService.searchSort(searchKey,searchValue,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
