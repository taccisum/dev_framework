package com.cloudlinkscm.loms.framework.service;

import com.cloudlinkscm.loms.framework.core.pojo.GenericModel;
import com.cloudlinkscm.loms.framework.dao.GenericMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author : tac
 * @date : 2017/5/15
 */

@Transactional
public class GenericServiceTest {

    private GenericMapper mapper;
    private TestService service;
    private TestPojo entity;

    @Before
    public void before() {
        mapper = mock(GenericMapper.class);
        service = new TestService(mapper);
        entity = mock(TestPojo.class);
        when(mapper.insert(entity)).thenReturn(1);
    }


    @Test
    public void testInsert() {
        Assert.assertEquals(1, service.insert(entity));
    }


    class TestService extends GenericService<TestPojo>{

        public TestService(GenericMapper<TestPojo> mapper) {
            super(mapper);
        }
    }

    class TestPojo extends GenericModel{

        protected String currentUserId() {
            return "";
        }

        protected void setDefault() {

        }
    }

}
