package com.miku.lab.test;/*
 *@author miku
 *@data 2021/6/1 14:48
 *@version:1.1
 */

import com.miku.lab.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void  set(){
        redisUtils.set("redis_key","redis_value");
    }
    @Test
    public void get(){
        String value = redisUtils.get("redis_key");
        System.out.println("这是value:"+value);
    }
}
