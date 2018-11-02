package com.kbss.admin;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kbss.admin.cms.config.LongObjectSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class KbssadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KbssadminApplication.class, args);
    }

    /**
     * json 格式化选项,修复Long类型返回js前端精度丢失问题
     * @return
     */

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        SerializeConfig config = new SerializeConfig();
        config.put(Long.class, new LongObjectSerializer());
        fastJsonConfig.setSerializeConfig(config);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }
}
