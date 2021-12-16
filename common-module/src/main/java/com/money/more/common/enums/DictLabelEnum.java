package com.money.more.common.enums;

/**
 * @Describe：字典表label类型
 * @Author：luna
 * @Date：2021/12/15:11:15
 */
public enum DictLabelEnum {
    PLATFORM_TYPE ("platformType"),
    BUSINESS_TYPE("businessType"),
    MERCHANT_PRICE_TYPE("merchantPriceType"),
    USER_PRICE_TYPE("userPriceType");




    private String label;

    DictLabelEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
