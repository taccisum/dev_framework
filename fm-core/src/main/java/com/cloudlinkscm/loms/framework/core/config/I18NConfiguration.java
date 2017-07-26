package com.cloudlinkscm.loms.framework.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


/**
 * 国际化相关配置
 *
 * @author : tac
 * @date : 2017/7/25
 */
@Configuration
public class I18NConfiguration {
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //todo:: 暂时写死
        messageSource.setBasenames("i18n/message");
        return messageSource;
    }
}
