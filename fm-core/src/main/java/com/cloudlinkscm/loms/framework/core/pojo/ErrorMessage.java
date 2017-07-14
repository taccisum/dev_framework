package com.cloudlinkscm.loms.framework.core.pojo;

import java.util.HashMap;

/**
 * 国际化的错误信息管理类
 *
 * @author : tac
 * @date : 2017/5/18
 */
public class ErrorMessage {
    public ErrorMessage(HashMap<ErrorCode, String> zh, HashMap<ErrorCode, String> en) {
        this.zh = zh;
        this.en = en;
    }

    private HashMap<ErrorCode, String> zh = new HashMap<>();
    private HashMap<ErrorCode, String> en = new HashMap<>();

    public String getInternationalErrorMessage(ErrorCode key, Language language){
        switch (language){
            case ZH:
                return zh.get(key);
            case EN:
                return en.get(key);

            default:
                //use chinese as generic language
                return zh.get(key);
        }
    }

    public String getInternationalErrorMessage(ErrorCode key, Language language, Object... args){
        String msg = getInternationalErrorMessage(key, language);
        return String.format(msg, args);
    }

    public String getErrorMessage(ErrorCode key){
        return zh.get(key);
    }
}
