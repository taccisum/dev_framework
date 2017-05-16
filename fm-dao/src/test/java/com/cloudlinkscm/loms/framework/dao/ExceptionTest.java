package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.dao.exception.InsertException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @date : 2017/5/16
 */
public class ExceptionTest {
    @Test
    public void testSimply() {
        try {
            throw new InsertException();
        }
        catch (BizException e){
            Assert.assertEquals(DaoErrorCode.INSERT_EXCEPTION, e.getErrorCode());
            Assert.assertTrue(e.getErrorCode().getCode().length() > 0);
            Assert.assertTrue(e.getErrorCode().getMessage().length() > 0);
            Assert.assertTrue(e.getErrorCode().getInternationalMessage(Language.EN).length() > 0);
        }
    }
}
