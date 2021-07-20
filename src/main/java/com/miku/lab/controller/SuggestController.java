package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/9 14:59
 *@version:1.1
 */

import com.miku.lab.entity.Suggestion;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.service.SuggestService;
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
@RequestMapping("/suggest")
@Api(value="SuggestController",tags="反馈接口")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;

    /**
     * 获取所有前段时间(由系统配置表的search_feedback_month字段决定)反馈记录接口
     * @return
     */
    @ApiOperation(value = "获取所有前段时间(由系统配置表的search_feedback_month字段决定)反馈记录接口")
    @ApiImplicitParam()
    @GetMapping("/getAllSuggestion")
    public ReturnResult getAllSuggestion(){
        Object map = suggestService.getAllSuggestion();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    /**
     * 提交反馈信息接口
     * @param suggestion
     * @return
     */
    @ApiOperation(value = "提交反馈信息接口")
    @PostMapping("/addSuggest")
    public ReturnResult addSuggest(@RequestBody Suggestion suggestion){
        int flag = suggestService.addSuggest(suggestion);
        if(flag!=0){
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }else{
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加信息失败");
        }
    }

    /**
     * 分页获取用户反馈信息
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="分页获取用户反馈信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageSuggest")
    public ReturnResult getPageSuggest(@RequestParam String page, @RequestParam String limit){
        Object map = suggestService.getPageSuggest(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    /**
     * 获取一个用户反馈信息
     * @param openid
     * @return
     */
    @ApiOperation(value = "获取一个用户反馈信息")
    @ApiImplicitParam
    @GetMapping("/getOneSuggest")
    public ReturnResult getOneSuggest(@RequestParam String openid){
        Object object = suggestService.getOneSuggest(openid);
        if(object!=null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }


    /**
     * 删除用户反馈(逻辑删除)
     * @param param
     * @return
     */
    @ApiOperation(value = "删除用户反馈(逻辑删除)")
    @ApiImplicitParam
    @PostMapping("/deleteSuggest")
    public ReturnResult deleteSuggest(@RequestBody Map<String,Object> param){
        int res = suggestService.deleteSuggest(param);
        if(res >= 1){
            return AjaxUtil.success("删除成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    /**
     * 搜索用户反馈
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "搜索用户反馈")
    @ApiImplicitParam
    @GetMapping("/searchSuggest")
    public ReturnResult searchSuggest(@RequestParam int page,@RequestParam int limit,
                                   @RequestParam String searchKey,@RequestParam String searchValue){
        Object object = suggestService.searchSuggest(page,limit,searchKey,searchValue);
        if(object != null){
            return AjaxUtil.success(object,Constant.RESCODE_SUCCESS,1);
        }
        return AjaxUtil.error(Constant.RESCODE_NOEXIST, "搜索失败");
    }

}
