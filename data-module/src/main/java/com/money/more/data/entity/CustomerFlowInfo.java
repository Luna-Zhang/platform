package com.money.more.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 多客流水表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("customer_flow_info")
@ApiModel(value = "CustomerFlowInfo对象", description = "多客流水表")
public class CustomerFlowInfo extends BaseModel {

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "多客流水id")
    @TableField(value = "customer_flow_id")
    private String customerFlowId;

    @ApiModelProperty(value = "任务id")
    @TableField(value = "task_id")
    private String taskId;

    @ApiModelProperty(value = "金额")
    @TableField(value = "amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "多客流水类型：1-提现 2-任务完成")
    @TableField(value = "customer_flow_type")
    private Integer customerFlowType;

    @ApiModelProperty(value = "多客流水名称")
    @TableField(value = "customer_flow_name")
    private String customerFlowName;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;

}
