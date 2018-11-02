package com.kbss.admin.cms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:/mybatis/spring-mybatis.xml"})
public class MybatisPlusConfig {

}
