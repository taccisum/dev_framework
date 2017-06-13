package com.cloudlinkscm;

import com.cloudlinkscm.loms.framework.webapp.response.processer.adapter.ResponseAdapterFactory;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : tac
 * @date : 2017/5/17
 */
public class ExceptionHandlerTest extends BaseTest {

    @Test
    public void testSimply() {

    }


    @Test
    public void testSysException() throws Exception {
        String responseStr =  mvc.perform(post("/demo/sys_exception?adaptTo=233")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void testBizException() throws Exception {
        String responseStr =  mvc.perform(post("/demo/biz_exception")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
