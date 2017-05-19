package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.ErrorMessage;
import com.cloudlinkscm.loms.framework.core.pojo.Language;

import java.util.HashMap;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public enum  DaoErrorCode implements ErrorCode {
    INSERT_EXCEPTION("501"),
    UPDATE_EXCEPTION("502"),
    DELETE_EXCEPTION("503"),
    SELECT_EXCEPTION("504");

    private String code;
    DaoErrorCode(String code) {
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

    private static HashMap<ErrorCode, String> zh = new HashMap<>();
    private static HashMap<ErrorCode, String> en = new HashMap<>();
    static {
        zh.put(DaoErrorCode.INSERT_EXCEPTION, "插入数据失败");
        zh.put(DaoErrorCode.DELETE_EXCEPTION, "删除数据失败");
        zh.put(DaoErrorCode.SELECT_EXCEPTION, "获取数据失败");
        zh.put(DaoErrorCode.UPDATE_EXCEPTION, "更新数据失败");
    }
    static {
        en.put(DaoErrorCode.INSERT_EXCEPTION, "insert data failure");
        en.put(DaoErrorCode.DELETE_EXCEPTION, "delete data failure");
        en.put(DaoErrorCode.SELECT_EXCEPTION, "select data failure");
        en.put(DaoErrorCode.UPDATE_EXCEPTION, "update data failure");
    }
    private static ErrorMessage errorMessage = new ErrorMessage(zh, en);

}
