package com.cloudlinkscm.loms.framework.core.exception;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;

/**
 * 业务异常基类
 * @author : tac
 * @date : 2017/5/12
 */
public class BizException extends RuntimeException {

    public BizException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
