package com.cloudlinkscm.loms.framework.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : tac
 * @date : 2017/5/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LoggerTest.class)
public class LoggerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testSimply() {
        logger.info("hello world");
    }

}
