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
 * 任务表
 * </p>
 *
 * @author luna
 * @since 2021-12-15
 */
@Data
@Accessors(chain = true)
@TableName("task_info")
@ApiModel(value = "TaskInfo对象", description = "任务表")
public class TaskInfo extends BaseModel {

    @ApiModelProperty(value = "逻辑删除：0-否 1-是")
    @TableField(value = "disable")
    private Byte disable;

    @ApiModelProperty(value = "任务id")
    @TableField(value = "task_id")
    private String taskId;

    @ApiModelProperty(value = "商户id")
    @TableField(value = "merchant_id")
    private String merchantId;

    @ApiModelProperty(value = "任务名称")
    @TableField(value = "task_name")
    private String taskName;

    @ApiModelProperty(value = "平台类型：1-小红书 2-抖音")
    @TableField(value = "platform_type")
    private Integer platformType;

    @ApiModelProperty(value = "平台名称")
    @TableField(value = "platform_name")
    private String platformName;

    @ApiModelProperty(value = "业务类型：1-点赞 2-收藏 3-评论")
    @TableField(value = "business_type")
    private Integer businessType;

    @ApiModelProperty(value = "业务名称：1-点赞 2-收藏 3-评论")
    @TableField(value = "business_name")
    private String businessName;

    @ApiModelProperty(value = "任务链接")
    @TableField(value = "task_url")
    private String taskUrl;

    @ApiModelProperty(value = "下单数量")
    @TableField(value = "task_count")
    private Integer taskCount;

    @ApiModelProperty(value = "支付金额")
    @TableField(value = "payment_amount")
    private BigDecimal paymentAmount;

    @ApiModelProperty(value = "单价")
    @TableField(value = "unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "多客单价")
    @TableField(value = "customer_price")
    private BigDecimal customerPrice;

    @ApiModelProperty(value = "任务状态：W-待审核 F-审核不通过 I-进行中 P-暂停 S-已完成 R-退款")
    @TableField(value = "task_status")
    private String taskStatus;

    @ApiModelProperty(value = "审核人")
    @TableField(value = "audit_by")
    private String auditBy;

    @ApiModelProperty(value = "审核时间")
    @TableField(value = "audit_time")
    private Date auditTime;

    @ApiModelProperty(value = "处理结果")
    @TableField(value = "audit_result")
    private String auditResult;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    @TableField(value = "remarks")
    private String remarks;

}
