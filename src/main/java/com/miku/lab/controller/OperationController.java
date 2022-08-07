package com.miku.lab.controller;

import com.miku.lab.entity.Operation;
import com.miku.lab.entity.vo.ReturnResult;
import com.miku.lab.service.OperationService;
import com.miku.lab.util.AjaxUtil;
import com.miku.lab.util.Constant;
import com.miku.lab.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: panghai
 * @Date: 2022/07/02/22:56
 * @Description: 实验操作接口
 */
@RestController
@RequestMapping("/operation")
@Api(value = "OperationController", tags = "实验操作接口")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Value("${file.common.uploadAttachmentByLinux}")
    private String uploadAttachmentByLinux;

    @Value("${file.common.uploadAttachmentByWindow}")
    private String uploadAttachmentByWindow;

    @Value("${file.common.uploadPictureByLinux}")
    private String uploadPictureByLinux;

    @Value("${file.common.uploadPictureByWindow}")
    private String uploadPictureByWindow;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    private static final String url = "https://shiyanshi.top";

    /**
     * 获取实验操作
     *
     * @return 实验操作列表
     */
    @ApiOperation(value = "获取实验操作")
    @GetMapping("/getOperation")
    public ReturnResult getOperation(Operation operation) {
        List<Operation> operationList = operationService.listOperation(operation);
        int count = operationService.listOperationNumber(operation);
        if (operationList != null) {
            return AjaxUtil.success(operationList, Constant.RESCODE_SUCCESS_MSG, count);
        } else {
            return AjaxUtil.error(Constant.RESCODE_NOEXIST, "获取信息失败");
        }
    }

    @ApiOperation(value = "添加实验操作")
    @PostMapping("/addOperation")
    public ReturnResult addOperation(@RequestBody Operation operation) {
        int row = operationService.addOperation(operation);
        if (row > 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS, "添加成功");
        } else {
            return AjaxUtil.error(Constant.RESCODE_INSERTERROR, "添加失败");
        }
    }

    @ApiOperation(value = "删除实验操作")
    @PostMapping("/deleteOperation/{sortIds}")
    public ReturnResult deleteOperations(@PathVariable Long[] sortIds) {
        int row = operationService.deleteOperations(sortIds);
        if (row > 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS, "删除成功");
        } else {
            return AjaxUtil.error(Constant.RESCODE_DELETEERROR, "删除失败");
        }
    }

    @ApiOperation(value = "修改实验操作")
    @PostMapping("/updateOperation")
    public ReturnResult updateOperation(@RequestBody Operation operation) {
        int row = operationService.updateOperation(operation);
        if (row > 0) {
            return AjaxUtil.sucessUpdate(Constant.RESCODE_SUCCESS, "修改成功");
        } else {
            return AjaxUtil.error(Constant.RESCODE_MODIFYERROR, "修改失败");
        }
    }

    /**
     * 上传附件
     *
     * @param attachments 多附件
     * @return 附件下载地址
     */
    @ApiOperation(value = "上传附件")
    @ApiImplicitParam(name = "attachment", value = "swagger-ui2不支持数组文件上传,请使用postman上传")
    @PostMapping(value = "/uploadAttachments", headers = "content-type=multipart/form-data")
    @ResponseBody
    public ReturnResult projectPictureUpload(
            @RequestParam(value = "file") MultipartFile[] attachments, HttpServletRequest request) {
        if (attachments.length > 0) {
            List<String> pathList = new ArrayList<>();
            for (int i = 0; i < attachments.length; i++) {
                // 原始文件名
                String originalFilename = attachments[i].getOriginalFilename();
                // 文件后缀
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                // 添加yyyy/mm/dd作为文件夹
                String format = sdf.format(new Date());
                // 文件保存路径
                String filePath = "";
                if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                    //windows下的路径
                    filePath = uploadAttachmentByWindow + format;
                } else {
                    //linux下的路径
                    filePath = uploadAttachmentByLinux + format;
                }
                File folder = new File(filePath);
                if (!folder.isDirectory()) {
                    folder.mkdirs();
                }
                File file = new File(filePath + originalFilename);
                while (!file.exists()) {
                    if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                        //windows下的路径
                        filePath = uploadAttachmentByWindow + format;
                    } else {
                        //linux下的路径
                        filePath = uploadAttachmentByLinux + format;
                    }
                }
                try {
                    //写入附件
                    Boolean writeFlag = FileUtils.uploadFile(attachments[i].getBytes(), filePath, originalFilename);
                    if (!writeFlag) {
                        //上传附件失败
                        return AjaxUtil.error(Constant.UPLOAD_FAIL, "上传附件失败");
                    }
                    //上传成功后，将可以访问的完整路径返回
                    pathList.add(url + "/attachmentUpload/project/" + format + "/" + originalFilename);
                } catch (Exception e) {
                    e.printStackTrace();
                    //上传附件失败
                    return AjaxUtil.error(Constant.UPLOAD_FAIL, "上传附件失败");
                }
            }
            return AjaxUtil.success(pathList, Constant.RESCODE_SUCCESS_MSG, pathList.size());
        }
        return AjaxUtil.error(Constant.UPLOAD_FAIL, "没附件不能为空");
    }

    /**
     * 上传富文本图片
     *
     * @param picture 图片
     * @return 图片下载地址
     */
    @ApiOperation(value = "上传富文本图片")
    @ApiImplicitParam(name = "uploadPicture", value = "上传富文本图片")
    @PostMapping(value = "/uploadPicture", headers = "content-type=multipart/form-data")
    @ResponseBody
    public ReturnResult projectPictureUpload(
            @RequestParam(value = "file") MultipartFile picture, HttpServletRequest request) {
        if (picture != null) {
            // 原始文件名
            String originalFilename = picture.getOriginalFilename();
            // 文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // 添加yyyy/mm/dd作为文件夹
            String format = sdf.format(new Date());
            // 文件名称为uuid+图片后缀防止冲突
            String fileName = UUID.randomUUID().toString() + "." + suffix;
            // 文件保存路径
            String filePath = "";
            if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
                //windows下的路径
                filePath = uploadPictureByWindow + format;
            } else {
                //linux下的路径
                filePath = uploadPictureByLinux + format;
            }
            File folder = new File(filePath);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String picPath = "";
            try {
                //写入附件
                Boolean writeFlag = FileUtils.uploadFile(picture.getBytes(), filePath, fileName);
                if (!writeFlag) {
                    //上传附件失败
                    return AjaxUtil.error(Constant.UPLOAD_FAIL, "上传图片失败");
                }
                //上传成功后，将可以访问的完整路径返回
                picPath = url + "/pictureUpload/project/" + format + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                //上传附件失败
                return AjaxUtil.error(Constant.UPLOAD_FAIL, "上传图片失败");
            }
            Map<String, String> data = new HashMap<>();
            data.put("src", picPath);
            data.put("title", fileName);
            return AjaxUtil.success(data, 0, 1);
        }
        return AjaxUtil.error(Constant.UPLOAD_FAIL, "图片不能为空");
    }


}
