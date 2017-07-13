package com.cloudlinkscm;

import com.cloudlinkscm.model.Demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        ObjectMapper mapper =  new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Demo demo = new Demo();
        demo.setDate(new Date());
        demo.setInteger(123);
        demo.setString("asdad");
        String responseStr =  mvc.perform(post("/demo/date_formatter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(demo))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
