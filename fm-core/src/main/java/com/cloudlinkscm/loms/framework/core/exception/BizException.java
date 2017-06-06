package com.cloudlinkscm.loms.framework.core.exception;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;

/**
 * 业务异常基类
 *
 * <p>
 *     每个具体的派生类应对应一个不同的错误码，错误码通过继承{@link ErrorCode}的枚举类来定义。
 *     可以参考{@link GenericErrorCode}的实现
 * </p>
 *
 * <p>
 *     也可以直接抛出此异常{@link BizException}，此时应显式指定错误码
 * </p>
 *
 * <p>
 *     若要使用错误信息模板，并根据传参来实现不同的错误信息展现可以使用{@link BizExceptionWithArguments}
 * </p>
 *
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
