package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * @author : tac
 * @date : 2017/5/13
 */
public interface AppCode {
    /**
     * 获取提示信息（中文）
     */
    String getMessage();

    /**
     * 获取国际化提示信息（多语言）
     */
    String getInternationalMessage(Language language);

    String getCode();
}
