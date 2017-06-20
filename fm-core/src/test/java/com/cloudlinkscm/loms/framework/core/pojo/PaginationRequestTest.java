package com.cloudlinkscm.loms.framework.core.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : tac
 * @date : 2017/5/26
 */
public class PaginationRequestTest {
    @Test
    public void testDeprecated() {
        PaginationRequest request = new InnerTestClass();
        request.setItemFrom(0);
        request.getItemFrom();
    }

    @Test
    public void testGetOffset() {
        PaginationRequest request = new InnerTestClass();
        request.setOffset(12);
        Assert.assertEquals(12, request.getOffset().intValue());
        Assert.assertEquals(12, request.getItemFrom().intValue());
    }

    @Test
    public void testGetLimit() {
        PaginationRequest request = new InnerTestClass();
        request.setOffset(12);
        request.setLimit(3);

        Assert.assertEquals(3, request.getLimit().intValue());
        Assert.assertEquals(15, request.getItemTo().intValue());
    }

    @Test
    public void testGetPageIndexAndGetPageSize() {
        PaginationRequest request = new InnerTestClass();
        request.setOffset(12);
        request.setLimit(3);

        Assert.assertEquals(12 / 3 + 1, request.getPageIndex().intValue());
        Assert.assertEquals(3, request.getPageSize().intValue());
    }

    @Test
    public void testSerializeDeserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PaginationRequest request = new InnerTestClass();
        request.setOffset(12);
        request.setLimit(3);

        String serialize = mapper.writeValueAsString(request);
        PaginationRequest deserialize = mapper.readValue(serialize, InnerTestClass.class);
        Assert.assertEquals(12, deserialize.getItemFrom().intValue());
        Assert.assertEquals(15, deserialize.getItemTo().intValue());
    }
}

class InnerTestClass extends PaginationRequest{
}