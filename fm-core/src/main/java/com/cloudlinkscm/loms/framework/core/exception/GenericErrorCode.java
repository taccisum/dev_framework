package com.cloudlinkscm.loms.framework.core.exception;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.ErrorMessage;
import com.cloudlinkscm.loms.framework.core.pojo.Language;

import java.util.HashMap;

/**
 * todo::
 * 全局通用异常
 * @author : tac
 * @date : 2017/5/18
 */
public enum GenericErrorCode implements ErrorCode {
    INVALID_REQUEST_ARGUMENT_EXCEPTION("101");

    private static HashMap<ErrorCode, String> zh = new HashMap<>();
    private static HashMap<ErrorCode, String> en = new HashMap<>();
    static {
        zh.put(GenericErrorCode.INVALID_REQUEST_ARGUMENT_EXCEPTION, "无效请求参数：%s");
    }
    static {
        en.put(GenericErrorCode.INVALID_REQUEST_ARGUMENT_EXCEPTION, "invalid request argument: %s");
    }
    private static ErrorMessage errorMessage = new ErrorMessage(zh, en);


    private String code;
    GenericErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return errorMessage.getErrorMessage(this);
    }

    @Override
    public String getInternationalMessage(Language language) {
        return errorMessage.getInternationalErrorMessage(this, language);
    }

    @Override
    public String getInternationalMessage(Language language, Object... args){
        return errorMessage.getInternationalErrorMessage(this, language, args);
    }
}
