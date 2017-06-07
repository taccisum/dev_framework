package com.cloudlinkscm.loms.framework.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局通用配置参数
 *
 * @author : tac
 * @date : 2017/6/6
 */

@Configuration
@EnableConfigurationProperties(HerculesConfig.class)
@ConfigurationProperties(prefix = "hercules")
public class HerculesConfig {
    private Boolean debug;
    private String dateFormatPattern;

    public Boolean getDebug() {
        if(debug == null){
            return false;
        }
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public String getDateFormatPattern() {
        if(dateFormatPattern == null){
            return "yyyy-MM-dd hh:mm:ss";
        }
        return dateFormatPattern;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }
}
