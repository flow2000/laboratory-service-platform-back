package com.miku.lab.util;


import com.miku.lab.entity.vo.ReturnResult;

public class AjaxUtil {

    public static ReturnResult success(Object object, Integer code, Integer count){
        ReturnResult result = new ReturnResult();
        result.setCode(code);
        result.setMsg("成功");
        result.setData(object);
        result.setCount(count);
        return result;
    }

    public static ReturnResult error(Integer code, String msg){
        ReturnResult result = new ReturnResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
