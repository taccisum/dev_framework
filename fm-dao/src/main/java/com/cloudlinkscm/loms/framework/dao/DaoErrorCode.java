package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public enum  DaoErrorCode implements ErrorCode {
    INSERT_EXCEPTION("501", "数据新增失败！"),
    UPDATE_EXCEPTION("502", "数据更新失败！"),
    DELETE_EXCEPTION("503", "数据删除失败！"),
    GET_EXCEPTION("504", "数据获取失败！"),
    SELECT_EXCEPTION("505", "数据获取失败！");

    private String code;
    private String msg;
    DaoErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }
}
