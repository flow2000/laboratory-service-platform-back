package com.miku.lab.entity;
/*
 *@author miku
 *@data 2021/6/1 15:06
 *@version:1.1
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "sys_info_user")
public class UserInfo {

    //@TableId(value = "id")
    private Integer id;

    //@TableId(value = "user_id")
    private String userId;

    //@TableId(value = "password")
    private String password;

    //@TableId(value = "role_code")
    private String role_code;

   // @TableId(value = "name")
    private String name;

   // @TableId(value = "username")
    private String username;

   // @TableId(value = "phone")
    private String phone;

    //@TableId(value = "sex")
    private int sex;

    //@TableId(value = "email")
    private String email;

    //@TableId(value = "is_disable")
    private int isDisable;

}
