package com.cloudlinkscm.loms.framework.test;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * todo:: test
 * @author : tac
 * @date : 2017/5/27
 */
public abstract class GenericCtrlTest<T> extends GenericTest {
    protected MockMvc mvc;
    protected ObjectMapper mapper = new ObjectMapper();
    {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Before
    public void __init__(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    protected T perform(String path, Object arg, RequestMethod method) throws Exception {
        return perform(path, arg, method, null);
    }

    protected T perform(String path, Object arg, RequestMethod method, Class cls) throws Exception {
        String json = mapper.writeValueAsString(arg);
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
        if (cls == null) {
            return (T) mapper.readValue(responseStr, getGenericClass(0));
        }else{
            JavaType type = mapper.getTypeFactory().constructParametricType(getGenericClass(0), cls);
            return mapper.readValue(responseStr, type);
        }
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

    private Class getGenericClass(int index) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class)params[index];
    }
}
