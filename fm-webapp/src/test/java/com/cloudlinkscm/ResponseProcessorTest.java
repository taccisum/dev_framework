package com.cloudlinkscm;

import com.cloudlinkscm.loms.framework.core.pojo.RestfulApiResponse;
import com.cloudlinkscm.loms.framework.webapp.response.processer.adapter.ResponseAdapter;
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
public class ResponseProcessorTest extends BaseTest {

    @Test
    public void testNotAdapt() throws Exception {
        //todo:: assert
        String responseStr =  mvc.perform(post("/demo/index")
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
    public void testAdapt() throws Exception {
        //todo:: assert
        ResponseAdapterFactory.put(233, new Response4DemoAdapter());

        String responseStr =  mvc.perform(post("/demo/index?adaptTo=233")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    static class Demo{
        private String resultCode;
        private Object resultValue;
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public Object getResultValue() {
            return resultValue;
        }

        public void setResultValue(Object resultValue) {
            this.resultValue = resultValue;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    static class Response4DemoAdapter implements ResponseAdapter<Demo> {

        @Override
        public Demo doAdapt(RestfulApiResponse response) {
            Demo demo = new Demo();
            demo.setResultCode(response.getErrorCode());
            demo.setResultMsg(response.getReturnMsg());
            demo.setResultValue(response.getResult());
            return demo;
        }
    }

}
