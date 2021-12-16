package com.money.more.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证手机号是否合法
 */
public class MobileUtil {

    /**
     * 验证手机号是否合法
     * @param number
     * @return
     */
    public static boolean checkMobile(String number) {
        if (number.length() != 11) {
            return false;
        } else {
            String exp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
            Pattern pattern4 = Pattern.compile(exp);
            Matcher match4 = pattern4.matcher(number);
            return match4.matches();
        }
    }
}
