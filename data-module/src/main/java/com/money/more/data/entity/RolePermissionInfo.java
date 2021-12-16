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
 * 角色权限关联表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("role_permission_info")
@ApiModel(value = "RolePermissionInfo对象", description = "角色权限关联表")
public class RolePermissionInfo extends BaseModel {

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "role_id")
    private String roleId;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "permission_id")
    private String permissionId;

}
