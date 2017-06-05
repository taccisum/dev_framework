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
 * 调整handlers的优先级
 *
 * <p>
 *     在spring中，{@link org.springframework.web.bind.annotation.RequestBody}注解对应的处理器处理完后，
 *     定义在其后面的handler是不会得到执行的，因此要将{@link RestfulApiResponseProcessor}的优先级调整到前面
 * </p>
 *
 * <p>
 *     通过{@link InitializingBean}，调整优先级的过程将在{@link RequestMappingHandlerAdapter}装配完成后进行
 * </p>
 *
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
