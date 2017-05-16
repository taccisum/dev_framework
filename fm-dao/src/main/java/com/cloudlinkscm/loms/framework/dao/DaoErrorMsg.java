package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.ErrorCode;
import com.cloudlinkscm.loms.framework.core.pojo.Language;

import java.util.HashMap;

/**
 * @author : tac
 * @date : 2017/5/16
 */
public class DaoErrorMsg {
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

    public static String getInternationalErrorMsg(ErrorCode key, Language language){
        switch (language){
            case ZH:
                return zh.get(key);
            case EN:
                return en.get(key);


            default:
                //use english as generic language
                return en.get(key);
        }
    }
}
