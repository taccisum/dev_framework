package com.cloudlinkscm.loms.framework.cloud.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

/**
 * 通用的FeignClient自定义配置
 *
 * @author : tac
 * @date : 2017/6/13
 */

@Configuration
public class GenericFeignClientConfiguration {
    @Value("${hercules.dateFormatPattern}")
    private String dataFormatPattern;

    private ObjectFactory<HttpMessageConverters> objectFactory;

    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(customHttpMessageConvertersFactory()));
    }

    @Bean
    public Encoder feignEncoder(){
        return new SpringEncoder(customHttpMessageConvertersFactory());
    }

    private ObjectFactory<HttpMessageConverters> customHttpMessageConvertersFactory(){
        if(objectFactory == null){
            HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
            HttpMessageConverters converters = new HttpMessageConverters(jacksonConverter);
            objectFactory = () -> converters;
        }
        return objectFactory;
    }

    private ObjectMapper customObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(dataFormatPattern));
        return objectMapper;
    }
}
