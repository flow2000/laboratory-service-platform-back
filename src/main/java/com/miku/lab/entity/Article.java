package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/9 15:28
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
public class Article {

    private Integer id;
    private String  articleCode;
    private String  articleClass;
    private String  articleTitle;
    private String  articleActor;
    private String  articleContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date  articleDate;
    private String  articleAbstract;
    private Integer isPush;

    private Integer validStatus;

    private String creater;                     //创建者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间
    private String updater;                     //更新者
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;                   //更新时间



}
