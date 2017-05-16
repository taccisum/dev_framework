package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import com.cloudlinkscm.loms.framework.dao.exception.UpdateException;
import org.apache.ibatis.session.RowBounds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author : tac
 * @date : 2017/5/16
 */
public class GenericDaoTest {
    private GenericMapper mapper;
    private TestDao dao;
    private String pk = "id";
    private TestPojo entity;
    private List<TestPojo> entities;
    private Example example;
    private RowBounds rowBounds;

    @Before
    public void before() {
        mapper = mock(GenericMapper.class);
        dao = new TestDao(mapper);
        entity = mock(TestPojo.class);
        entities = mock(List.class);
        example = mock(Example.class);
        rowBounds = mock(RowBounds.class);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        when(mapper.deleteByPrimaryKey(pk)).thenReturn(1);
        Assert.assertEquals(1, dao.deleteByPrimaryKey(pk));
        verify(mapper).deleteByPrimaryKey(pk);
    }

    @Test
    public void testDelete() {
        when(mapper.delete(entity)).thenReturn(1);
        Assert.assertEquals(1, dao.delete(entity));
        verify(mapper).delete(entity);
    }

    @Test
    public void testInsert() {
        when(mapper.insert(entity)).thenReturn(1);
        Assert.assertEquals(1, dao.insert(entity));
        verify(mapper).insert(entity);
    }

    @Test
    public void testInsertSelective() {
        when(mapper.insertSelective(entity)).thenReturn(1);
        Assert.assertEquals(1, dao.insertSelective(entity));
        verify(mapper).insertSelective(entity);
    }

    @Test
    public void testSelectAll() {
        when(mapper.selectAll()).thenReturn(entities);
        when(entities.size()).thenReturn(18);
        Assert.assertEquals(18, dao.selectAll().size());
        verify(mapper).selectAll();
    }

    @Test
    public void testSelectByPrimaryKey() {
        when(mapper.selectByPrimaryKey(pk)).thenReturn(entity);
        Assert.assertEquals(entity, dao.selectByPrimaryKey(pk));
        verify(mapper).selectByPrimaryKey(pk);
    }

    @Test
    public void testSelectCount() {
        when(mapper.selectCount(entity)).thenReturn(15);
        Assert.assertEquals(15, dao.selectCount(entity));
        verify(mapper).selectCount(entity);
    }

    @Test
    public void testSelect() {
        when(mapper.select(entity)).thenReturn(entities);
        when(entities.size()).thenReturn(18);
        Assert.assertEquals(18, dao.select(entity).size());
        verify(mapper).select(entity);
    }

    @Test
    public void testSelectOne() {
        when(mapper.selectOne(entity)).thenReturn(entity);
        Assert.assertEquals(entity, dao.selectOne(entity));
        verify(mapper).selectOne(entity);
    }

    @Test(expected = UpdateException.class)
    public void testUpdateByPrimaryKey() {
        when(mapper.updateByPrimaryKey(entity)).thenReturn(1);
        when(entity.getId()).thenReturn(pk);
        Assert.assertEquals(1, dao.updateByPrimaryKey(entity));
        verify(mapper).updateByPrimaryKey(entity);
        when(entity.getId()).thenReturn(null);
        dao.updateByPrimaryKey(entity);
    }

    @Test(expected = UpdateException.class)
    public void testUpdateByPrimaryKeySelective() {
        when(mapper.updateByPrimaryKeySelective(entity)).thenReturn(1);
        when(entity.getId()).thenReturn(pk);
        Assert.assertEquals(1, dao.updateByPrimaryKeySelective(entity));
        verify(mapper).updateByPrimaryKeySelective(entity);
        when(entity.getId()).thenReturn(null);
        dao.updateByPrimaryKey(entity);
    }

    @Test
    public void testDeleteByExample() {
        when(mapper.deleteByExample(example)).thenReturn(4);
        Assert.assertEquals(4, dao.deleteByExample(example));
        verify(mapper).deleteByExample(example);
    }

    @Test
    public void testSelectByExample() {
        when(mapper.selectByExample(example)).thenReturn(entities);
        when(entities.size()).thenReturn(16);
        Assert.assertEquals(16, dao.selectByExample(example).size());
        verify(mapper).selectByExample(example);
    }

    @Test
    public void testSelectCountByExample() {
        when(mapper.selectCountByExample(example)).thenReturn(16);
        Assert.assertEquals(16, dao.selectCountByExample(example));
        verify(mapper).selectCountByExample(example);
    }

    @Test
    public void testUpdateByExample() {
        when(mapper.updateByExample(entity, example)).thenReturn(3);
        Assert.assertEquals(3, dao.updateByExample(entity, example));
        verify(mapper).updateByExample(entity, example);
    }

    @Test
    public void testUpdateByExampleSelective() {
        when(mapper.updateByExampleSelective(entity, example)).thenReturn(3);
        Assert.assertEquals(3, dao.updateByExampleSelective(entity, example));
        verify(mapper).updateByExampleSelective(entity, example);
    }

    @Test
    public void testSelectByExampleAndRowBounds() {
        when(mapper.selectByExampleAndRowBounds(example, rowBounds)).thenReturn(entities);
        when(entities.size()).thenReturn(17);
        Assert.assertEquals(17, dao.selectByExampleAndRowBounds(example, rowBounds).size());
        verify(mapper).selectByExampleAndRowBounds(example, rowBounds);
    }

    @Test
    public void testSelectByRowBounds() {
        when(mapper.selectByRowBounds(entity, rowBounds)).thenReturn(entities);
        when(entities.size()).thenReturn(17);
        Assert.assertEquals(17, dao.selectByRowBounds(entity, rowBounds).size());
        verify(mapper).selectByRowBounds(entity, rowBounds);
    }

//-----------------------------------------------------------------------//

    class TestDao extends GenericDao<TestPojo, String>{

        public TestDao(GenericMapper<TestPojo> mapper) {
            super(mapper);
        }
    }

    class TestPojo extends GenericModel {

        protected String currentUserId() {
            return "";
        }
    }
}
