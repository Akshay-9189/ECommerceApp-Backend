package com.csi.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration(proxyBeanMethods = false)
public class ImageConfig {
    @Bean
    public HttpMessageConverters httpMessageConverters() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());

        List<MediaType> mediaTypeList = new ArrayList<>(converter.getSupportedMediaTypes());
        mediaTypeList.add(MediaType.APPLICATION_OCTET_STREAM);

        converter.setSupportedMediaTypes(mediaTypeList);
        converter.setPrettyPrint(true);

        return new HttpMessageConverters(converter);
    }
}
