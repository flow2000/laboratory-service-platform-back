package com.miku.lab.entity;/*
 *@author miku
 *@data 2021/7/12 20:44
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
public class WxUser {
    private Integer id; //id

    private String openId;  //微信授权id

    private String roleCode; //角色编号

    private String realName;    //真实姓名

    private String userCompany;     //单位

    private String userPhone;       //手机号

    private String userPosition;    //职位

    private String email;       //邮箱

    private String sex;         //性别

    private String username;    //用户名

    private Integer valid_status;   //有效状态

    private String creater;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //修改人
    private String updater;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}
