package com.corp.spring.config;

import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addConverter(Jsr310Converters.StringToLocalDateConverter.INSTANCE);
    }
}
