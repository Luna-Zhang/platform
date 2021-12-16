package com.money.more.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("permission_info")
@ApiModel(value = "PermissionInfo对象", description = "权限表")
public class PermissionInfo extends BaseModel{

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "权限id")
    @TableField(value = "permission_id")
    private String permissionId;

    @ApiModelProperty(value = "权限名称")
    @TableField(value = "permission_name")
    private String permissionName;

    @ApiModelProperty(value = "状态：0-禁用1-启用")
    @TableField(value = "status")
    private Integer status;

}
