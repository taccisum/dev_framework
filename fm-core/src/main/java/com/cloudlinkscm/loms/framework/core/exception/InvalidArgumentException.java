package com.cloudlinkscm.loms.framework.core.exception;

/**
 * @author : tac
 * @date : 2017/7/24
 */
public class InvalidArgumentException extends BizExceptionWithArguments {
    public InvalidArgumentException(String argName) {
        super(GenericErrorCode.INVALID_ARGUMENT_EXCEPTION, argName);
    }
}
