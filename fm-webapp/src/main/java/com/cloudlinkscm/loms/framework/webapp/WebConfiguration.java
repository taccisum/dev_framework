package com.cloudlinkscm.loms.framework.webapp;

import com.cloudlinkscm.loms.framework.core.config.HerculesConfig;
import com.cloudlinkscm.loms.framework.webapp.response.processer.RestfulApiResponseSupportFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * web app通用配置
 *
 * <ul>
 *     <li>配置全局的异常处理器{@link #extendHandlerExceptionResolvers(List)}</li>
 *     <li>配置响应适配器的handler到spring mvc的handlers list并调整优先级{@link #restfulApiResponseSupportFactoryBean()}</li>
 *     <li>集成swagger并配置{@link #createRestApi()} {@link #addResourceHandlers(ResourceHandlerRegistry)}</li>
 *     <li>配置消息转换器{@link #configureMessageConverters(List)}</li>
 * </ul>
 *
 * @author : tac
 * @date : 2017/5/16
 */
@Configuration
@EnableSwagger2
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Resource
    private HerculesConfig herculesConfig;

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(new ExceptionHandler());
    }

    @Bean
    public RestfulApiResponseSupportFactoryBean restfulApiResponseSupportFactoryBean() {
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
     * 注册静态资源文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    /**
     * 配置消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        ObjectMapper myMapper = new ObjectMapper();
        myMapper.setDateFormat(new SimpleDateFormat(herculesConfig.getDateFormatPattern()));    //日期格式化器
//        myMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));    //日期格式化器
        converters.add(new MappingJackson2HttpMessageConverter(myMapper));
    }
}
