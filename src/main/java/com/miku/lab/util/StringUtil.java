package com.miku.lab.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isEmail(String string) {
        if (string == null || string=="")
            return false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

     public String generateBookId(){
        try {
           return IdUtil.geneId(Constant.BUSINESS_Book);
        }catch (Exception e){
            System.out.println("生成编号失败");
        }
        return "生成失败";
    }



}
