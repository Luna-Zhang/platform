package com.money.more.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Describe：公共类
 * @Author：luna
 * @Date：2021/11/24:14:25
 */
@Data
public class BaseModel implements Serializable {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by",fill = FieldFill.INSERT )
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "last_update_by",fill = FieldFill.INSERT_UPDATE)
    private String lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "last_update_time",fill = FieldFill.INSERT_UPDATE)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    @ApiModelProperty(value = "拓展字段1")
    @TableField(value = "expand_1")
    private String expand1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField(value = "expand_2")
    private String expand2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField(value = "expand_3")
    private String expand3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField(value = "expand_4")
    private String expand4;

    @ApiModelProperty(value = "分页当前页")
    @TableField(exist = false)
    private Integer current=1;

    @ApiModelProperty(value = "分页每页条数")
    @TableField(exist = false)
    private Integer size=10;
}
