package com.cloudlinkscm.loms.framework.core.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author : tac
 * @date : 2017/7/14
 */
public class DataTableResponseTest {

    @Test
    public void testReturnTotalItem() throws IOException {
        DataTableResponse response = new DataTableResponse();
        response.setReturnTotalItems(123L);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(response);
        HashMap<String, Object> map = mapper.readValue(str, HashMap.class);

        Assert.assertEquals(123, map.get("returnTotalItem"));

    }

}