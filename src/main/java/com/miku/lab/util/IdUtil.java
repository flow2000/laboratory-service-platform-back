package com.miku.lab.util;/*
 *@author miku
 *@data 2021/7/12 23:17
 *@version:1.1
 */

import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class IdUtil {

    /**
     * 获得编号
     * @param prefix
     * @return
     */
    public static String geneId(String prefix){
        return prefix+System.currentTimeMillis() +String.valueOf(new Random().nextInt(900)+100);
    }

    /**
     * 获取六位随机字符串类型的数字
     * @return
     */
    public static String getSixNum() {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

}
