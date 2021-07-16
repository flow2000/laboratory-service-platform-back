package com.miku.lab.entity;
/*
 *@author miku
 *@data 2021/6/1 15:06
 *@version:1.1
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class UserInfo {
    //id
    private Integer id;
    //用户账号
    private String userId;
    //密码
    private String password;
    //角色编号
    private String roleCode;
    //姓名
    private String realName;
    //用户名
    private String username;
    //手机号
    private String phone;
    //性别
    private int sex;
    //邮箱
    private String email;
    //删除标志
    private int isDisable;
    //创建者
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
