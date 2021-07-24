package com.miku.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Ztree树结构实体类
 * 
 * @author 涛
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Ztree implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
   // private Long id;

    /** 节点父ID */
   // private Long pId;

    /** 节点名称 */
   // private String name;

    /** 节点标题 */
    private String title;

    //图标
    private String icon;
    //地址
    private String href;
    //打开方式
    private String target;
    //孩子
    private List<Ztree> child;

    /** 是否勾选 */
   // private boolean checked = false;

    /** 是否展开 */
   // private boolean open = false;

    /** 是否能勾选 */
   // private boolean nocheck = false;


}
