package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/16 15:38
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
public class LinkInfo {

    //id
    private int id;
    //公司名称
    private String officeName;
    //联系人电话
    private String linkPeopleName;
    //联系微信号
    private String wechatNumber;
    //联系电话1
    private String linkPhone1;
    //联系电话2
    private String linkPhone2;
    //联系地址
    private String linkAddress;
    //联系邮箱
    private String linkEmail;
    //单位网址
    private String officeUrl;
    //邮编
    private String postCode;
    //二维码图片地址
    private String imgUrl;
    //简介
    private String remark;

    private String creater;                     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间

}
