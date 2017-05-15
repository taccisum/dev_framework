package com.cloudlinkscm.loms.framework.dao.exception;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.dao.DaoErrorCode;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public class DeleteException extends BizException {
    public DeleteException() {
        super(DaoErrorCode.DELETE_EXCEPTION);
    }
}
