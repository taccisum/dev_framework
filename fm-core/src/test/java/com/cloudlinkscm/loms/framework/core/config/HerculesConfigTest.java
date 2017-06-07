package com.cloudlinkscm.loms.framework.core.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author : tac
 * @date : 2017/6/6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HerculesConfigTest.class)
@ComponentScan("com.cloudlinkscm.loms.framework.core.config")
public class HerculesConfigTest {

    @Value("${hercules.dateFormat}")
    private String dateFormatPattern;

    @Resource
    private HerculesConfig herculesConfig;

    @Test
    public void testSimply() {
        System.out.println(dateFormatPattern);
        Assert.assertTrue(dateFormatPattern.length() > 0);
    }

    @Test
    public void testSimply1() {
        System.out.println(herculesConfig.getDateFormatPattern());
        Assert.assertNotNull(herculesConfig);
        Assert.assertFalse(herculesConfig.getDebug());
        Assert.assertTrue(herculesConfig.getDateFormatPattern().length() > 0);
    }
}
