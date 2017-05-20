package com.cloudlinkscm.loms.framework.webapp.response.processer;

import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;
import com.cloudlinkscm.loms.framework.webapp.response.processer.adapter.ResponseAdapterFactory;
import com.monitorjbl.json.DefaultView;
import com.monitorjbl.json.JsonViewReturnValueHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public class RestfulApiResponseProcessor extends JsonViewReturnValueHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RestfulApiResponseProcessor(List<HttpMessageConverter<?>> converters, DefaultView defaultView) {
        super(converters, defaultView);
    }


    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(RestfulApiResponse.class);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Integer adapterType = 0;
        try {
            adapterType = Integer.parseInt(nativeWebRequest.getParameter("adaptTo"));
        }catch (NumberFormatException ignored){}

        Object o1 = null;
        try {
            o1 = ResponseAdapterFactory.create(adapterType).doAdapt((RestfulApiResponse) o);
        }catch (Exception e){
            logger.error("it's a error occurred in doAdapt()", e);
            super.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
            return;
        }

        super.handleReturnValue(o1, methodParameter, modelAndViewContainer, nativeWebRequest);
    }
}
