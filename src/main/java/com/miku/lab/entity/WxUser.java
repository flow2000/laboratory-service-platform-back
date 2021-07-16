package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/12 20:44
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

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel(description= "修改微信用户信息模板")
public class WxUser {
    @ApiModelProperty(hidden=true)
    private Integer id; //id

    @ApiModelProperty(value = "微信授权id")
    private String openId;  //微信授权id

    @ApiModelProperty(hidden=true)
    private String roleCode; //角色编号

    @ApiModelProperty(value = "真实姓名")
    private String realName;    //真实姓名

    @ApiModelProperty(value = "单位")
    private String userCompany;     //单位

    @ApiModelProperty(value = "手机号码")
    private String userPhone;       //手机号

    @ApiModelProperty(value = "职位")
    private String userPosition;    //职位

    @ApiModelProperty(value = "邮箱")
    private String email;       //邮箱

    @ApiModelProperty(value = "性别")
    private String sex;         //性别

    @ApiModelProperty(value = "昵称，可不填")
    private String username;    //昵称

    @ApiModelProperty(hidden=true)
    private Integer validStatus;   //有效状态

    @ApiModelProperty(hidden=true)
    private String creater;
    //创建时间
    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //修改人
    @ApiModelProperty(hidden=true)
    private String updater;
    //修改时间
    @ApiModelProperty(hidden=true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    @ApiModelProperty(hidden=true)
    private String isReceptPush;

    @ApiModelProperty(hidden=true)
    private String isReceptMsg;

}
