package com.miku.lab.util;/*
 *@author miku
 *@data 2021/7/12 23:17
 *@version:1.1
 */

import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class IdUtil {

    public static String geneId(String prefix){
        return prefix+System.currentTimeMillis() +String.valueOf(new Random().nextInt(900)+100);
    }
}
