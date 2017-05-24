package com.cloudlinkscm.loms.framework.core.pojo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public class RestfulApiResponseTest {

    @Test
    public void testSuccess() {
        RestfulApiResponse successResponse = RestfulApiResponse.success();
        Assert.assertEquals(1, successResponse.getReturnCode().intValue());
        Assert.assertEquals("1", successResponse.getErrorCode());
    }

    @Test
    public void testFailure() {
        RestfulApiResponse failureResponse = RestfulApiResponse.failure("123", "", "");
        Assert.assertEquals(0, failureResponse.getReturnCode().intValue());
    }

    @Test
    public void testError() {
        RestfulApiResponse errorResponse = RestfulApiResponse.error("", "");
        Assert.assertEquals(-1, errorResponse.getReturnCode().intValue());
    }

}
