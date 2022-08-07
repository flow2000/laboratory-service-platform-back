package com.miku.lab.controller;/*
 *@author miku
 *@data 2021/7/8 16:41
 *@version:1.1
 */

import com.miku.lab.entity.Machine;
import com.miku.lab.entity.Machine_sort;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.MachineService;
import com.miku.lab.util.Constant;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/machine")
@Api(value="MachineController",tags="仪器接口")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Value("${file.common.uploadPictureByLinux}")
    private String uploadPictureByLinux;

    @Value("${file.common.uploadPictureByWindow}")
    private String uploadPictureByWindow;

    /**
     * 获取所有仪器
     * @return
     */
    @ApiOperation(value = "获取仪器接口")
    @ApiImplicitParam()
    @GetMapping("/getAllMachine")
    public ReturnResult getAllMachine(){
        Object map = machineService.getAllMachine();
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 分页获取仪器
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="分页获取仪器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageMachine")
    public ReturnResult getPageMachine(@RequestParam String page, @RequestParam String limit){
        Object map = machineService.getPageMachine(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    /**
     * 添加仪器
     * @param param
     * @return
     */
    @ApiOperation(value = "添加仪器接口")
    @ApiImplicitParam()
    @PostMapping("/addMachine")
    public ReturnResult addMachine(@RequestBody Map<String,Object>param){
        int res = machineService.addMachine(param);
        if(res!=0){
            return AjaxUtil.success("添加成功", Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加失败");
        }
    }

    @PostMapping("/projectPictureUpload")
    @ResponseBody
    public ReturnResult projectPictureUpload(@RequestParam(value = "projectImg",required = true) MultipartFile file){
        //将图片上传到服务器
        if(file.isEmpty()){
            return AjaxUtil.error(Constant.RESCODE_NOEXIST,"项目图片不能为空");
        }
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //图片名称为uuid+图片后缀防止冲突
        String fileName = UUID.randomUUID().toString()+"."+suffix;
        String os = System.getProperty("os.name");
        //文件保存路径
        String filePath="";
        if(os.toLowerCase().startsWith("win")){
            //windows下的路径
            filePath = uploadPictureByWindow;
        }else {
            //linux下的路径
            filePath = uploadPictureByLinux;
        }
        try {
            //写入图片
            Boolean writePictureflag = FileUtils.uploadFile(file.getBytes(),filePath,fileName);
            if(!writePictureflag){
                //上传图片失败
                return AjaxUtil.error(Constant.UPLOAD_FAIL,"上传项目图片失败");
            }
            //上传成功后，将可以访问的完整路径返回
            String fullImgpath = "/pictureUpload/project/"+fileName;
            return AjaxUtil.success(fullImgpath,Constant.RESCODE_SUCCESS_MSG,1);
        } catch (Exception e) {
            e.printStackTrace();
            //上传图片失败
            return AjaxUtil.error(Constant.UPLOAD_FAIL,"上传项目图片失败");
        }
    }

    /**
     * 修改仪器基本信息
     * @param param
     * @return
     */
    @ApiOperation(value = "修改仪器基本信息接口")
    @ApiImplicitParam()
    @PostMapping("/updateMachine")
    public ReturnResult updateMachine(@RequestBody Map<String,Object>param){
        int res = machineService.updateMachine(param);
        if(res!=0){
            return AjaxUtil.success("修改成功", Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
    }

    /**
     * 修改仪器是否需要审核状态
     * @param param
     * @return
     */
    @ApiOperation(value = "修改仪器是否需要审核状态")
    @ApiImplicitParam()
    @PostMapping("/updateMachineCheck")
    public ReturnResult updateMachineCheck(@RequestBody Map<String,Object>param){
        int res = machineService.updateMachineCheck(param);
        if(res!=0){
            return AjaxUtil.success("修改成功", Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
    }

    /**
     * 搜索仪器
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @ApiOperation(value = "搜索仪器")
    @ApiImplicitParam()
    @GetMapping("/searchMachine")
    public ReturnResult searchMachine(@RequestParam int page,@RequestParam int limit,
                                      @RequestParam String searchKey,@RequestParam String searchValue){
        Object object = machineService.searchMachine(page,limit,searchKey,searchValue);
        if(object!=null){
            return AjaxUtil.success(object, Constant.RESCODE_SUCCESS_MSG,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "搜索失败");
        }
    }

    /**
     * 删除仪器(逻辑删除)
     * @param param
     * @return
     */
    @ApiOperation(value = "删除仪器(逻辑删除)")
    @ApiImplicitParam
    @PostMapping("/deleteMachine")
    public ReturnResult deleteMachine(@RequestBody Map<String,Object>param){
        int res = machineService.deleteMachine(param);
        if(res >= 1){
            return AjaxUtil.success("删除成功",Constant.RESCODE_SUCCESS,res);
        }else if(res == 0){
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
        return AjaxUtil.error(Constant.RESCODE_EXCEPTION, "失败");
    }

    @ApiOperation(value = "获取所有仪器分类接口")
    @ApiImplicitParam()
    @GetMapping("/getMachineType")
    public ReturnResult getMachineType(){
        List<Machine_sort> list = machineService.getMachineType();
        if(list!=null){
            return AjaxUtil.success(list, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分页仪器分类接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value="页数",required=true),
            @ApiImplicitParam(name = "limit", value = "每页数据量", required = true)
    })
    @GetMapping("/getPageMachineSort")
    public ReturnResult getPageMachineSort(@RequestParam String page, @RequestParam String limit){
        Object map = machineService.getPageMachineSort(page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="获取分类详细信息接口")
    @ApiImplicitParam(name = "sortId",value="分类编号",required=true)
    @GetMapping("/getMachineSortDetail")
    public ReturnResult getMachineSortDetail(@RequestParam String sortId){
        Object map = machineService.getSortDetail(sortId);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }

    @ApiOperation(value="更新仪器分类接口")
    @PostMapping("/updateSort")
    public ReturnResult updateSort(@RequestBody Machine_sort machine_sort){
        String map = (String) machineService.updateMachineSort(machine_sort);
        return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,map);
    }

    @ApiOperation(value="添加仪器分类接口")
    @PostMapping("/addSort")
    public ReturnResult addSort(@RequestBody Machine_sort machine_sort){
        int flag = machineService.addMachineSort(machine_sort);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"添加失败，请勿重复添加分类");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"添加成功");
        }
    }

    @ApiOperation(value="删除仪器分类接口")
    @PostMapping("/delSort")
    public ReturnResult delSort(@RequestParam String sortId){
        int flag = machineService.delMachineSort(sortId);
        if (flag == 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_INSERTERROR,"删除失败");
        }else{
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS,"删除成功");
        }
    }

    @ApiOperation(value="批量删除仪器分类接口")
    @PostMapping("/combineDelSort")
    public ReturnResult combineDelSort(@RequestParam String ids){
        String[] sortIds = ids.split(",");
        int count=sortIds.length;
        int delCount=0;
        for(String sortId:sortIds){
            int flag = machineService.delMachineSort(sortId);
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
    @GetMapping("/searchSort")
    public ReturnResult searchSort(@RequestParam String searchKey,@RequestParam String searchValue,
                                   @RequestParam String page, @RequestParam String limit ){
        Object map = machineService.searchSort(searchKey,searchValue,page,limit);
        if(map!=null){
            return AjaxUtil.success(map, Constant.RESCODE_SUCCESS,1);
        }else{
            return AjaxUtil.error(Constant.RESCODE_SUCCESS, "获取信息失败");
        }
    }
}
