package com.money.more.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Describe：任务vo
 * @Author：luna
 * @Date：2021/12/8:10:50
 */
@Data
public class TaskBean extends BasicsRequest{

    @ApiModelProperty("平台类型：平台类型：1-小红书 2-抖音")
    private String platformType ;

    @ApiModelProperty("业务类型：1-点赞 2-收藏 3-评论")
    private String businessType;

    @ApiModelProperty("任务链接")
    private String taskUrl;

    @ApiModelProperty("任务数量")
    private String taskCount;

    @ApiModelProperty("备注")
    private String remarks;



}
