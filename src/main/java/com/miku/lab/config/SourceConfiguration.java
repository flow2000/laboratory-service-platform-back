package com.miku.lab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class SourceConfiguration extends WebMvcConfigurationSupport {

    @Value("${file.common.uploadPictureByWindow}")
    private String uploadPictureByWindow;

    @Value("${file.common.uploadPictureByLinux}")
    private String uploadPictureByLinux;

    @Value("${file.common.uploadAttachmentByLinux}")
    private String uploadAttachmentByLinux;

    @Value("${file.common.uploadAttachmentByWindow}")
    private String uploadAttachmentByWindow;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + uploadPictureByWindow);
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + uploadAttachmentByWindow);
        } else {  //linux 和mac
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + uploadPictureByLinux) ;
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:" + uploadAttachmentByLinux) ;
        }

        super.addResourceHandlers(registry);
    }
}
