package com.cloudlinkscm.loms.framework.service;

import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import com.cloudlinkscm.loms.framework.dao.GenericMapper;
import com.cloudlinkscm.loms.framework.dao.exception.DeleteException;
import com.cloudlinkscm.loms.framework.dao.exception.QueryException;
import com.cloudlinkscm.loms.framework.dao.exception.InsertException;
import com.cloudlinkscm.loms.framework.dao.exception.UpdateException;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * todo:: impl
 * service基类
 * @author : tac
 * @date : 2017/5/13
 */
public abstract class GenericService<E extends GenericModel> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected GenericMapper<E> mapper;

    public GenericService(GenericMapper<E> mapper) {
        this.mapper = mapper;
    }

    public int deleteByPrimaryKey(String pk) {
        int result = 0;
        try {
            result = mapper.deleteByPrimaryKey(pk);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public int delete(E t) {
        int result = 0;
        try {
            result = mapper.delete(t);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public int insert(E entity) {
        int result = 0;
        try {
            entity.init();
            entity.setInsertUser(currentUserId());
            result = mapper.insert(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public int insertSelective(E entity) {
        int result = 0;
        try {
            entity.init();
            result = mapper.insertSelective(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public List<E> selectAll() {
        return null;
    }

    public E selectByPrimaryKey(Object pk) {
        try {
            E result = mapper.selectByPrimaryKey(pk);
            return result;
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
    }

    public int selectCount(E t) {
        return 0;
    }

    public List<E> select(E t) {
        return null;
    }

    public E selectOne(E t) {
        return null;
    }

    public int updateByPrimaryKey(E entity) {
        int result = 0;
        try {
            setUpdateDefault(entity);
            result = mapper.updateByPrimaryKey(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public int updateByPrimaryKeySelective(E entity) {
        int result = 0;
        try {
            setUpdateDefault(entity);
            result = mapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(bizEx.getErrorCode().getMsg(), e);
            throw bizEx;
        }
        return result;
    }

    public int deleteByExample(Object o) {
        return 0;
    }

    public List<E> selectByExample(Object o) {
        return null;
    }

    public int selectCountByExample(Object o) {
        return 0;
    }

    public int updateByExample(E t, Object o) {
        return 0;
    }

    public int updateByExampleSelective(E t, Object o) {
        return 0;
    }

    public List<E> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return null;
    }

    public List<E> selectByRowBounds(E t, RowBounds rowBounds) {
        return null;
    }

    private void setUpdateDefault(E entity) {
        //todo:: set update user & tenant
        entity.setUpdateTime(new Date());
    }

    protected String currentUserId(){
        //todo::
        return "";
    }
}
