package com.miku.lab.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	 public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
    public static String getTime(Date date){
        return dateFormat.format(date);// new Date()为获取当前系统时间
    }
    
    /**
     * 将时间转换为时间戳
     * @param time
     * @return
     */
    public static String dateToStamp(String time) {
        String res=null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
		try {
			date = simpleDateFormat.parse(time);
			long ts = date.getTime();
	        res = String.valueOf(ts);
	        return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
        
    }

    /**
     * 将时间戳转换为时间
     * @param time
     * @return
     */
    public static Long stampToDate(String time){
    	Long timestamp = null;
    	try {
    		timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime();
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	return timestamp;
    }
}
