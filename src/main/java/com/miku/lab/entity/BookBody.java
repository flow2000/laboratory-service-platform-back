package com.miku.lab.entity;/*
 *@author 邓涛
 *@data 2021/7/27 0:01
 *@version:1.1
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class BookBody extends OrderCheck{

    @ApiModelProperty(hidden=true)
    private Integer id; //id


    @ApiModelProperty(value = "微信授权id")
    private String openId;  //微信授权id

    @ApiModelProperty(hidden=true)
    private String bookingCode;     //预约申请编号

    //@ApiModelProperty(value = "可不填")
    @ApiModelProperty(hidden=true)
    private String username;        //昵称

    @ApiModelProperty(value = "实验室编号")
    private String labId;           //实验室编号

    @ApiModelProperty(hidden=true)
    private String orderAddress;   //预约实验室地址

    @ApiModelProperty(hidden=true)
    private String orderApplyer;    //预约申请人姓名

    @ApiModelProperty(hidden=true)
    private String orderPhone;      //预约申请人手机号

    @ApiModelProperty(value = "实验名称")
    private String  labName;        //实验名称

    @ApiModelProperty(value = "预约目的")
    private String remark;         //备注

    @ApiModelProperty(value = "格式必须如：2021-11-20 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;         //开始时间

    @ApiModelProperty(value = "格式必须如：2021-11-20 10:20:10")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;           //结束时间


    @ApiModelProperty(hidden=true)
    private String checker;         //审核人

    @ApiModelProperty(hidden=true)
    private String lastUpdater;     //最后修改人

    @ApiModelProperty(hidden=true)
    private Integer checkStatus;    //审核状态

    @ApiModelProperty(value = "人数")
    private Integer orderNumber;         //人数

    @ApiModelProperty(hidden=true)
    private Integer validStatus;        //有效状态
    @ApiModelProperty(hidden=true)
    private String creater;                     //创建者

    //预约仪器信息
    private List<Map<String, String>> machines;
}
