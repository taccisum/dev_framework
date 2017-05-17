package com.cloudlinkscm.loms.framework.webapp;

import com.cloudlinkscm.loms.framework.webapp.response.processer.RestfulApiResponseSupportFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author : tac
 * @date : 2017/5/16
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Override
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new ExceptionHandler();
    }

    @Bean
    public RestfulApiResponseSupportFactoryBean views() {
        return new RestfulApiResponseSupportFactoryBean();
    }
}
