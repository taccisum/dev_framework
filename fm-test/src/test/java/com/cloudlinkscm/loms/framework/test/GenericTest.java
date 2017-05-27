package com.cloudlinkscm.loms.framework.test;

import io.codearte.jfairy.Fairy;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : tac
 * @date : 2017/5/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class GenericTest {

    @Autowired
    WebApplicationContext webApplicationContext;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Fairy fairy = Fairy.create();

}
