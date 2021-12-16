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
 * 基础字典表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("base_dict")
@ApiModel(value = "BaseDict对象", description = "基础字典表")
public class BaseDict extends BaseModel{

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "标签")
    @TableField(value = "label")
    private String label;

    @ApiModelProperty(value = "字典编码")
    @TableField(value = "dict_code")
    private String dictCode;

    @ApiModelProperty(value = "字典名称")
    @TableField(value = "dict_value")
    private String dictValue;

    @ApiModelProperty(value = "父字典编码")
    @TableField(value = "parent_code")
    private String parentCode;

    @ApiModelProperty(value = "状态：0-禁用1-启用")
    @TableField(value = "status")
    private Byte status;

    @ApiModelProperty(value = "备注")
    @TableField(value = "remark")
    private String remark;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;
}
