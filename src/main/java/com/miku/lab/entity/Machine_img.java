package com.miku.lab.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName(value = "machine_img")
public class Machine_img {
    @TableId(value = "id")
    private Integer id;
    @TableId(value = "url")
    private String url;
    @TableId(value = "creater")
    private String creater;
    @TableId(value = "modifyer")
    private String modifyer;
    @TableId(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Integer createTime;
    @TableId(value = "modify_time")
    private Integer modifyTime;
    @TableId(value = "delete_time")
    private Integer deleteTime;
    @TableId(value = "valid_status")
    private Integer validStatus;
    @TableId(value = "is_deleted")
    private Integer isDeleted;
    @TableId(value = "machine_code")
    private String machineCode;
    @TableId(value = "is_tar")
    private Integer is_tar;

}
