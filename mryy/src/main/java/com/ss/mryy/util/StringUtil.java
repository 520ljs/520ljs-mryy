package com.ss.mryy.util;

/**
 * @Author:ljy.s
 * @Date:2023/4/24 - 04 - 24 - 9:26
 */
public class StringUtil {

    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        String trim = str.trim();// 去除空格
        if ("".equals(trim)) {
            return true;
        }
        return false;
    }

}
