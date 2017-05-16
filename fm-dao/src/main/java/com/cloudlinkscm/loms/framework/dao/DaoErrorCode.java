package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.Language;

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
        return DaoErrorMsg.getInternationalErrorMsg(this, Language.ZH);
    }

    @Override
    public String getInternationalMessage(Language language) {
        return DaoErrorMsg.getInternationalErrorMsg(this, language);
    }

}
