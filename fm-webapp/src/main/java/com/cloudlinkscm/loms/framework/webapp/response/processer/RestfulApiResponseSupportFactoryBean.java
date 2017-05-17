package com.cloudlinkscm.loms.framework.webapp.response.processer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitorjbl.json.DefaultView;
import com.monitorjbl.json.JsonViewMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public class RestfulApiResponseSupportFactoryBean implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(RestfulApiResponseSupportFactoryBean.class);

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    private final JsonViewMessageConverter converter;
    private final DefaultView defaultView;

    public RestfulApiResponseSupportFactoryBean() {
        this(new ObjectMapper());
    }

    public RestfulApiResponseSupportFactoryBean(ObjectMapper mapper) {
        this(new JsonViewMessageConverter(mapper.copy()), DefaultView.create());
    }

    private RestfulApiResponseSupportFactoryBean(JsonViewMessageConverter converter, DefaultView defaultView) {
        this.converter = converter;
        this.defaultView = defaultView;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(adapter.getReturnValueHandlers());

        List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
        converters.add(converter);
        adapter.setMessageConverters(converters);

        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        List<HttpMessageConverter<?>> converters = new ArrayList<>(adapter.getMessageConverters());
        converters.add(converter);
        for (HandlerMethodReturnValueHandler handler : handlers) {
            int index = handlers.indexOf(handler);
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                handlers.add(index, new RestfulApiResponseProcessor(converters, defaultView));
                break;
            }
        }
    }
}
