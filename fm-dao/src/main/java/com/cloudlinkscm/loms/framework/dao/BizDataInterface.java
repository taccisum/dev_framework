package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.Language;

/**
 * 一些在具体的微服务运行时才能获取到的数据接口
 * @author : tac
 * @date : 2017/5/15
 */
public interface BizDataInterface {
    /**
     * @return 当前登陆用户id
     */
    String currentUserId();

    /**
     * @return 当前用户所属租户id
     */
    String currentUserTenantId();

    /**
     * @return 当前用户语言环境
     */
    Language currentUserLanguage();
}
