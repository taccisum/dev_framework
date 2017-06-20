package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.exception.BizException;
import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.dao.exception.DeleteException;
import com.cloudlinkscm.loms.framework.dao.exception.InsertException;
import com.cloudlinkscm.loms.framework.dao.exception.QueryException;
import com.cloudlinkscm.loms.framework.dao.exception.UpdateException;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 通用的dao基类，用于对mapper的操作做一些封装，以简化编码工作
 *
 * <p>在派生类中需要通过构造函数指定从{@link GenericMapper}派生的一个具体的mapper</p>
 *
 * <p>
 *    在某些方法的封装实现中（如{@link #insert(GenericModel)}），会依赖于一些业务数据，
 *    为了将框架与实际业务解耦，这些业务数据的获取是通过在具体应用中实现的{@link BizDataInterface}
 *    的bean来获取的
 * </p>
 *
 * todo:: test select by boundary
 * @author : tac
 * @date : 2017/5/16
 */
public abstract class GenericDao<E extends GenericModel, PK> {
    private static final boolean BOUNDARY_DEFAULT = true;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected GenericMapper<E> mapper;

    public GenericDao(GenericMapper<E> mapper) {
        this.mapper = mapper;
    }

    public int deleteByPrimaryKey(PK pk) {
        try {
            return mapper.deleteByPrimaryKey(pk);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int delete(E entity) {
        try {
            return mapper.delete(entity);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int insert(E entity) {
        try {
            setInsertDefault(entity);
            return mapper.insert(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int insertSelective(E entity) {
        try {
            setInsertDefault(entity);
            return mapper.insertSelective(entity);
        } catch (Exception e) {
            InsertException bizEx = new InsertException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
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
        return selectCount(criteria, BOUNDARY_DEFAULT);
    }

    public int selectCount(E criteria, boolean boundary) {
        if(boundary){
            setBoundary(criteria);
        }
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
        return select(criteria, BOUNDARY_DEFAULT);
    }

    public List<E> select(E criteria, boolean boundary) {
        if(boundary){
            setBoundary(criteria);
        }
        try {
            return mapper.select(criteria);
        } catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }


    public E selectOne(E criteria) {
        return selectOne(criteria, BOUNDARY_DEFAULT);
    }

    public E selectOne(E criteria, boolean boundary) {
        if(boundary){
            setBoundary(criteria);
        }
        try {
            return mapper.selectOne(criteria);
        } catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }
    public int updateByPrimaryKey(E entity) {
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            }
            setUpdateDefault(entity);
            return mapper.updateByPrimaryKey(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int updateByPrimaryKeySelective(E entity) {
        try {
            if (entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            }
            setUpdateDefault(entity);
            return mapper.updateByPrimaryKeySelective(entity);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int deleteByExample(Example example) {
        return deleteByExample(example, BOUNDARY_DEFAULT);
    }

    public int deleteByExample(Example example, boolean boundary) {
        if(boundary){
            setBoundary(example);
        }
        try {
            return mapper.deleteByExample(example);
        } catch (Exception e) {
            DeleteException bizEx = new DeleteException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public List<E> selectByExample(Example example) {
        return selectByExample(example, BOUNDARY_DEFAULT);
    }
    public List<E> selectByExample(Example example, boolean boundary) {
        if(boundary){
            setBoundary(example);
        }
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
        return selectCountByExample(example, BOUNDARY_DEFAULT);
    }

    public int selectCountByExample(Example example, boolean boundary) {
        if(boundary){
            setBoundary(example);
        }
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
        return updateByExample(entity, example, BOUNDARY_DEFAULT);
    }

    public int updateByExample(E entity, Example example, boolean boundary) {
        if(boundary){
            setBoundary(example);
        }
        try {
            setUpdateDefault(entity);
            return mapper.updateByExample(entity, example);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public int updateByExampleSelective(E entity, Example example) {
        return updateByExampleSelective(entity, example, BOUNDARY_DEFAULT);
    }

    public int updateByExampleSelective(E entity, Example example, boolean boundary) {
        if(boundary){
            setBoundary(example);
        }
        try {
            setUpdateDefault(entity);
            return mapper.updateByExampleSelective(entity, example);
        } catch (Exception e) {
            UpdateException bizEx = new UpdateException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public List<E> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return selectByExampleAndRowBounds(example, rowBounds, BOUNDARY_DEFAULT);
    }

    public List<E> selectByExampleAndRowBounds(Example example, RowBounds rowBounds, boolean boundary) {
        if (boundary) {
            setBoundary(example);
        }
        try {
            return mapper.selectByExampleAndRowBounds(example, rowBounds);
        } catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }

    public List<E> selectByRowBounds(E criteria, RowBounds rowBounds) {
        return selectByRowBounds(criteria, rowBounds, BOUNDARY_DEFAULT);
    }

    public List<E> selectByRowBounds(E criteria, RowBounds rowBounds, boolean boundary) {
        if(boundary){
            setBoundary(criteria);
        }
        try {
            return mapper.selectByRowBounds(criteria, rowBounds);
        } catch (Exception e) {
            QueryException bizEx = new QueryException();
            logger.error(getErrorMsg(bizEx), e);
            throw bizEx;
        }
    }


    protected String currentUserId(){
        return BizDataInterface.getBean().currentUserId();
    }

    protected void setInsertDefault(E entity) {
        entity.init();
        entity.setInsertUser(currentUserId());
    }
    protected void setUpdateDefault(E entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(currentUserId());
    }
    protected void setBoundary(E criteria) {
    }
    protected void setBoundary(Example example) {
    }

    private String getErrorMsg(BizException bizEx) {
        return bizEx.getErrorCode().getInternationalMessage(Language.ZH);
    }

}
