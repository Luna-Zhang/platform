package com.money.more.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * md5加密
 */
public class MD5Util {

    public static String encryptMd5(String string) throws UnsupportedEncodingException {
        return encryptMd5(string, "UTF-8");
    }

    public static String encryptMd5(String string, String charSet) throws UnsupportedEncodingException {
        return DigestUtils.md5Hex(string.getBytes(charSet));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encryptMd5("123456"));
    }

}
