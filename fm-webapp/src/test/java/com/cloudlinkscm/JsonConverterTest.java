package com.cloudlinkscm;

import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : tac
 * @date : 2017/5/19
 */
public class JsonConverterTest extends BaseTest {

    @Test
    public void testDateFormatter() throws Exception {
        String responseStr =  mvc.perform(post("/demo/date_formatter")
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
