package com.miku.lab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SourceConfiguration extends WebMvcConfigurationSupport {

    @Value("${file.common.uploadWindow}")
    private String filePathWindow;

    @Value("${file.common.uploadLinux}")
    private String filePathLinux;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");

        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + filePathWindow);
        } else {  //linux 和mac
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + filePathLinux) ;
        }

        super.addResourceHandlers(registry);
    }
}
