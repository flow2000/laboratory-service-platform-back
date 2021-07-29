package com.miku.lab.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 * 
 * @author ruoyi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SysMenu
{

    /** 菜单ID */
    private Long menuId;

    /** 菜单名称 */
    private String title;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private String orderNum;

    /** 菜单URL */
    private String href;

    /** 打开方式（menuItem页签 menuBlank新窗口） */
    private String target;

    /**菜单状态*/
    private boolean status;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    //是否展开
    private String open;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();

}
