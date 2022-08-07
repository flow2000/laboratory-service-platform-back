package com.miku.lab.controller;

import com.miku.lab.entity.Operation;
import com.miku.lab.entity.OperationSort;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.OperationService;
import com.miku.lab.service.OperationSortService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: panghai
 * @Date: 2022/07/03/16:14
 * @Description: 实验操作分类接口
 */
@RestController
@RequestMapping("/operationSort")
@Api(value = "OperationSortController", tags = "实验操作分类接口")
public class OperationSortController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private OperationSortService operationSortService;

    /**
     * 获取实验操作分类
     *
     * @return 实验操作分类列表
     */
    @ApiOperation(value = "获取实验操作分类")
    @GetMapping("/getOperationSort")
    public ReturnResult getOperationSort(OperationSort operationSort) {
        List<OperationSort> sortList = operationSortService.listOperationSort(operationSort);
        int count = operationSortService.listOperationSortNumber(operationSort);
        if (sortList != null) {
            return AjaxUtil.success(sortList, Constant.RESCODE_SUCCESS_MSG, count);
        } else {
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    @ApiOperation(value = "添加实验操作分类")
    @PostMapping("/addOperationSort")
    public ReturnResult addOperationSort(@RequestBody OperationSort operationSort) {
        int row = operationSortService.addOperationSort(operationSort);
        if (row > 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS, "添加成功");
        } else {
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加失败");
        }
    }

    @ApiOperation(value = "删除实验操作分类")
    @PostMapping("/deleteOperationSort/{sortIds}")
    public ReturnResult deleteOperations(@PathVariable Long[] sortIds) {
        for (Long sortId : sortIds) {
            Operation operation = new Operation();
            operation.setSortId(sortId);
            int count = operationService.listOperationNumber(operation);
            if (count > 0) {
                return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "该分类下还有实验操作");
            }
        }
        int row = operationSortService.deleteOperationSorts(sortIds);
        if (row > 0) {
            return AjaxUtil.success("删除成功", Constant.RESCODE_SUCCESS, row);
        } else {
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
    }

    @ApiOperation(value = "修改实验操作分类")
    @PostMapping("/updateOperationSort")
    public ReturnResult updateOperationSort(@RequestBody OperationSort operationSort) {
        int row = operationSortService.updateOperationSort(operationSort);
        if (row > 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS, "修改成功");
        } else {
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
    }
}
