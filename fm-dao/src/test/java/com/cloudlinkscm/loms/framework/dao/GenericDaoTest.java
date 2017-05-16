package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author : tac
 * @date : 2017/5/16
 */

public class GenericDaoTest {

    private GenericMapper mapper;
    private TestDao dao;
    private TestPojo entity;

    @Before
    public void before() {
        mapper = mock(GenericMapper.class);
        dao = new TestDao(mapper);
        entity = mock(TestPojo.class);
        when(mapper.insert(entity)).thenReturn(1);
    }


    @Test
    public void testInsert() {
        Assert.assertEquals(1, dao.insert(entity));
        verify(mapper).insert(entity);
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
