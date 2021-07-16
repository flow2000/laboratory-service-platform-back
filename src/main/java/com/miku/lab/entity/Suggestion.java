package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/9 14:53
 *@version:1.1
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel(description= "添加反馈记录模板")
public class Suggestion {

    @ApiModelProperty(hidden=true)
    private Integer id;                         //id

    @ApiModelProperty(value = "微信授权id")
    private BigInteger openid;                  //用户账号

    @ApiModelProperty(value = "反馈内容")
    private String suggestContent;           //反馈内容

    @ApiModelProperty(value = "联系电话")
    private String suggestPhone;

    @ApiModelProperty(value = "创建者，可填昵称等")
    private String creater;                     //创建者

    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                   //创建时间

    @ApiModelProperty(hidden=true)
    private String updater;                     //更新者

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(hidden=true)
    private Date updateTime;                   //更新时间


}
