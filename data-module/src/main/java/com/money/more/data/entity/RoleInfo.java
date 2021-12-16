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
 * 角色表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("role_info")
@ApiModel(value = "RoleInfo对象", description = "角色表")
public class RoleInfo extends BaseModel {

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "role_id")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    @TableField(value = "role_name")
    private String roleName;

    @ApiModelProperty(value = "状态：0-禁用1-启用")
    @TableField(value = "status")
    private Byte status;
}
