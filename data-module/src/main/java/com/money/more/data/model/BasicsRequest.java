package com.money.more.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Describe：公共请求参数
 * @Author：luna
 * @Date：2021/11/24:13:00
 */
@Data
public class BasicsRequest {
    @ApiModelProperty("页数")
    private Integer current = 1;

    @ApiModelProperty("条数")
    private Integer size = 10;
}
