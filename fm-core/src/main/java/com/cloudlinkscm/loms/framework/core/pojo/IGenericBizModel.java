package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * 定义业务相关的通用model应具有的getter和setter及其它通用方法
 *
 * @author : tac
 * @date : 2017/6/29
 */
public interface IGenericBizModel<PK> extends IGenericModel<PK> {
    String getTenantId();

    void setTenantId(String tenantId);
}
