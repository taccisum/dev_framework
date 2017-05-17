package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * 与业务相关的表通用model
 * @author : tac
 * @date : 2017/5/16
 */
public abstract class GenericBizModel extends GenericModel {

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
