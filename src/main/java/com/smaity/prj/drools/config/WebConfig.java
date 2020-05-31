package com.smaity.prj.drools.config;
/**
 * Customer Type Enum Converter File
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import com.smaity.prj.drools.support.CustomerTypeEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerTypeEnumConverter());
    }
}
