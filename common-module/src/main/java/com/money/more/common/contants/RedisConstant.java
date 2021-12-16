package com.money.more.common.contants;

/**
 * @Describe：redis常量
 * @Author：luna
 * @Date：2021/11/29:16:36
 */
public class RedisConstant {

    public static final int REDIS_EXPIRES_DAYS_FIVE = 3600 * 24 * 7;

    public static final int EXPIRES_A_DAY = 60 * 60 * 24;

    public static final int EXPIRES_A_HOUR = 60 * 60;

    public static final int EXPIRES_SECOND = 30 * 30;

    public static final int EXPIRES_SYSLOGIN_SECOND = 60 * 10;

    public static final int EXPIRES_COUNT_SECOND = 30 * 60 * 24;

    public static final int EXPIRES_THREE_MINUTE = 60 * 3;


    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "login:token:";

    /**
     * 验证码前缀
     */
    public static final String VERIFY_CODE_PREFIX = "verify:code:";

    /**
     * 短信验证码次数
     */
    public static final String SMS_CODE_COUNT = "sms:code:count:";


}
