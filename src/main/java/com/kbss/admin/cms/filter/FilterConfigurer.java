package com.kbss.admin.cms.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>http拦截</p>
 * <p>Created by qrf on 2017/8/16.</p>
 */
@Configuration
public class FilterConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private CommonFilter commonFilter;

    @Value("${common.white.list}")
    private String whiteLists;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] whiteList = whiteLists.split(",");
        registry.addInterceptor(commonFilter)
                .addPathPatterns("/**")
                .excludePathPatterns(whiteList);
        super.addInterceptors(registry);
    }
}
