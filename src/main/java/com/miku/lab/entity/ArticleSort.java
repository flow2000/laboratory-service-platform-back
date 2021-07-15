package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/15 8:21
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
public class ArticleSort {
    private Integer id;    //id
    private Integer sortId; //分类(默认0)
    private String sortName;  //分类名称
    private String remark;     //备注
    private String validStatus; //有效状态
    private String creater;                     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间

}
