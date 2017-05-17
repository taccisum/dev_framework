package com.cloudlinkscm;

import io.codearte.jfairy.Fairy;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : tac
 * @date : 2017/5/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Fairy fairy = Fairy.create();
    protected MockMvc mvc;

    @Before
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
