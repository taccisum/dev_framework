package com.cloudlinkscm.loms.framework.webapp;

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
}
