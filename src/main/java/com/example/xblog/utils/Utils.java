package com.example.xblog.utils;

import java.util.Random;

public class Utils {
    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 自定义简单生成盐，是一个随机生成的长度为16的字符串，每一个字符是随机的十六进制字符
     */
    public static String salt() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < sb.capacity(); i++) {
            sb.append(hex[random.nextInt(16)]);
        }
        return sb.toString();
    }

    private final static String[] adminUrlList = {
            "/article/delBatch",
            "/article/save",

            "/category/delBatch",
            "/category/save",

            "/tag/delBatch",
            "/tag/save",

            "/article-tag/save",

            "/user/delBatch",
            "/user/save",

    };

    public static Boolean isAdmin(String url){
        boolean isFlag = false;
        for (String s:adminUrlList) {
            if (url.contains(s)) {
                isFlag = true;
                break;
            }
        }
        return  isFlag;
    }
}
