package com.miku.lab;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
@EnableScheduling
public class LabApplication extends SpringBootServletInitializer {
    protected static final Logger logger = LoggerFactory.getLogger(LabApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
        logger.info("创建定时任务");
    }

    //重写configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LabApplication.class);
    }
}
