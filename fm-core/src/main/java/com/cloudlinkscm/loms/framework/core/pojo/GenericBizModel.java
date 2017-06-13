package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * 与业务相关的实体模型基类
 *
 * @author : tac
 * @date : 2017/5/16
 */
public abstract class GenericBizModel<PK> extends GenericModel<PK> {

    /**
     * 租户id
     */
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
