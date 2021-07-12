package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/12 21:10
 *@version:1.1
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class OrderCheck {

    private Integer id; //id

    private String openId;  //微信授权id

    private String bookingCode;     //预约申请编号

    private String username;        //昵称

    private String orderAddress;   //预约实验室地址

    private String orderApplyer;    //预约申请人姓名

    private String orderPhone;      //预约申请人手机号

    private String  labName;        //实验名称

    private String remark;         //备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;         //开始时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;           //结束时间

    private String checker;         //审核人

    private String lastUpdater;     //最后修改人

    private Integer checkStatus;    //审核状态

    private Integer orderNumber;         //人数

    private Integer validStatus;        //有效状态
    private String creater;                     //创建者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间

}
