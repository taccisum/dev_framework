package com.cloudlinkscm.loms.framework.core.exception;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public class BizExceptionTest {

    @Test(expected = BizException.class)
    public void testSimply() {
        throw new BizException(new TestCode());
    }


    @Test
    public void testSubException() {
        try {
            throw new SomeException();
        }
        catch (BizException bizEx){
            Assert.assertEquals("123", bizEx.getErrorCode().getCode());
            Assert.assertEquals("测试异常", bizEx.getErrorCode().getMessage());
        }
    }


    private class SomeException extends BizException{


        SomeException() {
            super(new TestCode());
        }
    }

    class TestCode implements  ErrorCode{
        private final static String CODE = "123";
        private final static String MSG = "测试异常";
        private final static String ENG_MSG = "exception";

        @Override
        public String getMessage() {
            return MSG;
        }

        @Override
        public String getInternationalMessage(Language language) {
            return ENG_MSG;
        }

        @Override
        public String getCode() {
            return CODE;
        }
    }

}
