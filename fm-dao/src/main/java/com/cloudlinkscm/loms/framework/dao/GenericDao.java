package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.pojo.GenericBizModel;
import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.dao.exception.DeleteException;
import com.cloudlinkscm.loms.framework.dao.exception.InsertException;
import com.cloudlinkscm.loms.framework.dao.exception.QueryException;
import com.cloudlinkscm.loms.framework.dao.exception.UpdateException;
import com.cloudlinkscm.loms.framework.util.SpringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author : tac
 * @date : 2017/5/16
 */
public abstract class GenericDao<E extends GenericModel, PK> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected GenericMapper<E> mapper;

    public GenericDao(GenericMapper<E> mapper) {
        this.mapper = mapper;
    }

    public int deleteByPrimaryKey(PK pk) {
        int result = 0;
        try {
            result = mapper.deleteByPrimaryKey(pk);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int delete(E entity) {
        int result = 0;
        try {
            result = mapper.delete(entity);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int insert(E entity) {
        int result = 0;
        try {
            setInsertDefault(entity);
            result = mapper.insert(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int insertSelective(E entity) {
        int result = 0;
        try {
            setInsertDefault(entity);
            result = mapper.insertSelective(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public List<E> selectAll() {
        try {
            return mapper.selectAll();
        } catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public E selectByPrimaryKey(PK pk) {
        try {
            return mapper.selectByPrimaryKey(pk);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int selectCount(E criteria) {
        try {
            return mapper.selectCount(criteria);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public List<E> select(E criteria) {
        try {
            return mapper.select(criteria);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public E selectOne(E criteria) {
        try {
            return mapper.selectOne(criteria);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int updateByPrimaryKey(E entity) {
        int result = 0;
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            }
            setUpdateDefault(entity);
            result = mapper.updateByPrimaryKey(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int updateByPrimaryKeySelective(E entity) {
        int result = 0;
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            }
            setUpdateDefault(entity);
            result = mapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int deleteByExample(Example example) {
        int result = 0;
        try {
            result = mapper.deleteByExample(example);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public List<E> selectByExample(Example example) {
        try {
            return mapper.selectByExample(example);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int selectCountByExample(Example example) {
        try {
            return mapper.selectCountByExample(example);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int updateByExample(E entity, Example example) {
        int result = 0;
        try {
            setUpdateDefault(entity);
            result = mapper.updateByExample(entity, example);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public int updateByExampleSelective(E entity, Example example) {
        int result = 0;
        try {
            setUpdateDefault(entity);
            result = mapper.updateByExampleSelective(entity, example);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
        return result;
    }

    public List<E> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        try {
            return mapper.selectByExampleAndRowBounds(example, rowBounds);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public List<E> selectByRowBounds(E entity, RowBounds rowBounds) {
        try {
            return mapper.selectByRowBounds(entity, rowBounds);
        }
        catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }


    protected String currentUserId(){
        return getBizDataInterface().currentUserId();
    }
    protected String currentUserTenantId(){
        return getBizDataInterface().currentUserTenantId();
    }

    private BizDataInterface getBizDataInterface(){
        BizDataInterface data = SpringUtils.getBean(BizDataInterface.class);
        if (data == null) {
            logger.warn("can't find available bean of BizDataInterface");
            return DummyBizDataInterface.instance();
        }
        return data;
    }

    private void setInsertDefault(E entity) {
        entity.init();
        entity.setInsertUser(currentUserId());

        if(entity instanceof GenericBizModel){
            GenericBizModel temp = (GenericBizModel)entity;
            if(temp.getTenantId() == null){
                temp.setTenantId(currentUserTenantId());
            }
        }
    }

    private void setUpdateDefault(E entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUserId());
    }

    private String getErrorMsg(BizException bizEx) {
        return bizEx.getErrorCode().getInternationalMessage(Language.ZH);
    }

}
