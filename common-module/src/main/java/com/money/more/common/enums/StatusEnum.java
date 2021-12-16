package com.money.more.common.enums;

/**
 * @Describe：状态
 * @Author：luna
 * @Date：2021/12/15:12:00
 */
public enum StatusEnum {
    DISABLE(0),
    ENABLE(1);

    private Integer status;

    StatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
