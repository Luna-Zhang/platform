package com.money.more.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Created By zhangshanshan
 * Date: 2018/11/16
 * Time: 16:28
 */
public class UUIDGenerateUtil {

    public static void main(String[] args) {
        System.out.println(UUIDGenerateUtil.randomNumber4());
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 随机生成15位数
     * @return
     */
    public static String randomNumber () {
        String date = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        int random = (int)(Math.random()*10);
        return date + random;
    }

    /**
     * 随机生成18位数
     * @return
     */
    public static String randomNumber18 () {
        String date = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        for(int i=0;i<4;i++) {
            int random = (int)(Math.random()*10);
            date += random;
        }
        return date;
    }

    /**
     * 生成3位随机数
     * @return
     */
    public static Integer randomNumber4 () {
        return (int)(Math.random()*900)+1000;
    }

    /**
     * [0-9a-zA-Z] 随机生成
     * @param length
     * @return
     */
    public static String getLowerLetterNumber(int length) {
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[length];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < rands.length; i++)
        {
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
        }
        for(int i=0;i<rands.length;i++){
            buffer.append(rands[i]);
        }
        return buffer.toString();
    }

}
