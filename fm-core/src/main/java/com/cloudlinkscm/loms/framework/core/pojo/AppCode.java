package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * Application Code
 * @author : tac
 * @date : 2017/5/13
 */
public interface AppCode {
    /**
     * 获取提示信息（使用默认语言）
     */
    String getMessage();

    /**
     * 获取国际化提示信息（多语言）
     */
    String getInternationalMessage(Language language);

    /**
     * 获取错误码
     */
    String getCode();
}
