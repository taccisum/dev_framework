package com.cloudlinkscm.loms.framework.webapp;

import com.cloudlinkscm.loms.framework.webapp.response.processer.RestfulApiResponseSupportFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author : tac
 * @date : 2017/5/16
 */
@Configuration
@EnableSwagger2
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Override
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new ExceptionHandler();
    }

    @Bean
    public RestfulApiResponseSupportFactoryBean views() {
        return new RestfulApiResponseSupportFactoryBean();
    }


    /**
     * swagger配置
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloudlinkscm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LOMS api docs")
                .version("1.0")
                .build();
    }

    /**
     * 注册swagger的资源文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置消息转换器
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        ObjectMapper myMapper = new ObjectMapper();
        myMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));    //日期格式化器
        converters.add(new MappingJackson2HttpMessageConverter(myMapper));
    }
}
