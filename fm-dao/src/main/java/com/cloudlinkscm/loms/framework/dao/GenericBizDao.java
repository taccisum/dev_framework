package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.GenericBizModel;
import tk.mybatis.mapper.entity.Example;

/**
 * 通用的业务dao基类，用于对mapper的操作做一些封装，以简化编码工作
 *
 * 在{@link GenericDao}的行为基础上增加了租户边界
 *
 * @author : tac
 * @date : 2017/6/20
 */
public abstract class GenericBizDao<E extends GenericBizModel, PK> extends GenericDao<E, PK> {
    protected static final String TENANT_ID_FIELD_NAME = "tenantId";

    protected GenericMapper<E> mapper;

    public GenericBizDao(GenericMapper<E> mapper) {
        super(mapper);
    }

    protected String currentUserTenantId(){
        return BizDataInterface.getBean().currentUserTenantId();
    }

    @Override
    protected void setInsertDefault(E entity) {
        super.setInsertDefault(entity);
        entity.setTenantId(currentUserTenantId());
    }

    @Override
    protected void setBoundary(E criteria) {
        criteria.setTenantId(currentUserTenantId());
    }

    protected void setBoundary(Example example) {
//      以租户为边界过滤
        Example.Criteria criteria;
        if (example.getOredCriteria().size() == 0) {
            criteria = example.createCriteria();
        } else {
            criteria = example.getOredCriteria().get(0);
        }

        criteria.andEqualTo(TENANT_ID_FIELD_NAME, currentUserTenantId());
    }
}
