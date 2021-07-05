package com.miku.lab.util;/*
 *@author miku
 *@data 2021/6/1 12:37
 *@version:1.1
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public String get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set( String key,String value){
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value,Constant.TOKEN_EXPIRATION*1000);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存数据
     * @param key
     * @param value
     * @return
     */
    public boolean getAndSet(final String key,String value){
        boolean result = false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除键值对
     * @param key
     * @return
     */
    public boolean delete(final String key){
        boolean result = false;
        try{
            redisTemplate.delete(key);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
