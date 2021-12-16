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
 * 用户盐值信息表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("user_salt_info")
@ApiModel(value = "UserSaltInfo对象", description = "用户盐值信息表")
public class UserSaltInfo extends BaseModel {
    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "盐")
    @TableField(value = "salt")
    private String salt;
}
