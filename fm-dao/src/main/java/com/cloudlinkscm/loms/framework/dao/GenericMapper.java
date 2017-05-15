package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import tk.mybatis.mapper.common.Mapper;

/**
 * mapper基类
 * @author : tac
 * @date : 2017/5/13
 */

public interface GenericMapper<E extends GenericModel> extends Mapper<E> {
    //todo:: 定义mapper通用方法
//    int deleteByPrimaryKey(String pk);
//
//    int insert(E entity);
//
//    int insertSelective(E entity);
//
//    E selectByPrimaryKey(String pk);
//
//    int updateByPrimaryKeySelective(E entity);
//
//    int updateByPrimaryKey(E entity);
}
