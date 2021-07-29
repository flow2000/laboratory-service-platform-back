package com.miku.lab.config;/*
 *@author 邓涛
 *@data 2021/7/29 15:13
 *@version:1.1
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//表示配置类

public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //对视图控制器的快速配置  ViewControllerRegistry是视图控制器
        registry.addViewController("http://127.0.0.1:8848/laboratory-service-platform/login.html").setViewName("login");
        //registry.addViewController("/index.html").setViewName("index");
    }
}
