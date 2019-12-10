package com.util;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * describe:
 *
 * @author 王立朝
 * @date 2019/09/02
 */
public class Md5Util {

    //盐，用于混交md5
    private static final String slat = "&%1A2Asc*&%$$#@";

    public static void main(String[] args) {
        /**
         * 需要加密的密码尽可能的要复杂，包含大小写字母和数字，
         * 长度不小于8为，加密必须加盐，如果需要更高要求，可以多次加密，多次加盐
         */
       // System.out.println(getMD5("12ASD23klk935"));
        //a37fba0e7896e73e6b851c619a4ae599
        System.out.println(getMD5("1"));
        //532fdddb64943cae16a11b7a6af4fe9b

        System.out.println(getMD5("2"));
        //5f20020229bfc73d5b269c715552a8b2
    }

    /**
     * spring框架里面的加密方法
     *
     * @param value 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getMD5(String value) {
        String base = value + "/s/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 原生的加密方法
     *
     * @param value 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String value) {
        try {
            value = value + slat;
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(value.getBytes("UTF8"));
            byte message[] = messageDigest.digest();
            String result = "";
            for (int i = 0; i < message.length; i++) {
                result += Integer.toHexString((0x000000FF & message[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
