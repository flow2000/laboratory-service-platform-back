package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/9 13:42
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
public class LabInfo {

    private Integer id;                         //id

    private String labAddress;                  //实验室地址

    private Integer LabStatus;                  //实验室状态

    private String LabAdmin;                    //实验室管理员

    private String LabAdminPhone;              //实验室

    private Integer validStatus;

    private String creater;                     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间




}

