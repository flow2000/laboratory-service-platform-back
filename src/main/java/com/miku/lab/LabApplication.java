package com.miku.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LabApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

    //重写configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LabApplication.class);
    }
}
