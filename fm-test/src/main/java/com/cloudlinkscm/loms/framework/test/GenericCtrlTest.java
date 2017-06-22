package com.cloudlinkscm.loms.framework.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * todo:: test
 * spring环境下通用的junit controller层单元测试基类
 *
 * @author : tac
 * @date : 2017/5/27
 */
public abstract class GenericCtrlTest<T> extends GenericTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected MockMvc mvc;
    protected ObjectMapper mapper = new ObjectMapper();
    {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Before
    public void __init__(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected T perform(String path, Object data, RequestMethod method, TypeReference<T> typeReference) throws Exception {
        String json = mapper.writeValueAsString(data);
        logger.info(path + "请求参数" + json);
        String responseStr =  mvc.perform(doRequest(path, method)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return mapper.readValue(responseStr, typeReference);
    }

    protected T perform(String path, Object data, RequestMethod method, Class<T> cls) throws Exception {
        String json = mapper.writeValueAsString(data);
        logger.info(path + "请求参数" + json);
        String responseStr =  mvc.perform(doRequest(path, method)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return mapper.readValue(responseStr, cls);
    }

    private MockHttpServletRequestBuilder doRequest(String path, RequestMethod method) {
        MockHttpServletRequestBuilder m;
        switch (method){
            case GET:
                m = get(path);
                break;
            case POST:
                m = post(path);
                break;
            case PUT:
                m = put(path);
                break;
            case DELETE:
                m = delete(path);
                break;
            default:
                //todo:: impl 其它请求方式
                throw new NotImplementedException();
        }
        return m;
    }
}
