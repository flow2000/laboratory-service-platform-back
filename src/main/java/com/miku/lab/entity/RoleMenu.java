package com.miku.lab.entity;/*
 *@author 邓涛
 *@data 2021/7/31 16:43
 *@version:1.1
 */

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class RoleMenu {

    @TableField(value = "role_id")
    private String roleId;

    @TableField(value = "menu_id")
    private String menuId;

}
