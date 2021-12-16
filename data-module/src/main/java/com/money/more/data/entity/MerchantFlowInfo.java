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
 * 商户流水表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("merchant_flow_info")
@ApiModel(value = "MerchantFlowInfo对象", description = "商户流水表")
public class MerchantFlowInfo extends BaseModel{

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "商户流水id")
    @TableField(value = "merchant_flow_id")
    private String merchantFlowId;

    @ApiModelProperty(value = "任务id")
    @TableField(value = "task_id")
    private String taskId;

    @ApiModelProperty(value = "商户id")
    @TableField(value = "merchant_id")
    private String merchantId;

    @ApiModelProperty(value = "商户流水类型：1-充值 2-下单 3-退款")
    @TableField(value = "merchant_flow_type")
    private Integer merchantFlowType;

    @ApiModelProperty(value = "商户流水名称")
    @TableField(value = "merchant_flow_name")
    private String merchantFlowName;

    @ApiModelProperty(value = "金额")
    @TableField(value = "amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;
}
