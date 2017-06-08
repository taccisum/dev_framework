package com.cloudlinkscm.loms.framework.dao;

import com.cloudlinkscm.loms.framework.core.pojo.Language;
import com.cloudlinkscm.loms.framework.util.SpringUtils;
import org.apache.commons.codec.language.bm.Lang;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : tac
 * @date : 2017/6/8
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BizDataIntefactTest.class)
@ComponentScan("com.cloudlinkscm")
public class BizDataIntefactTest {

    @Test
    public void testSimply() {
        Assert.assertEquals("tac", BizDataInterface.getBean().currentUserId());
        Assert.assertEquals("taccisum", BizDataInterface.getBean().currentUserTenantId());
        Assert.assertEquals(Language.ZH, BizDataInterface.getBean().currentUserLanguage());
    }


    @Bean
    public BizDataInterface bizDataInterface(){
        return new BizDataInterface() {
            @Override
            public String currentUserId() {
                return "tac";
            }

            @Override
            public String currentUserTenantId() {
                return "taccisum";
            }

            @Override
            public Language currentUserLanguage() {
                return Language.ZH;
            }
        };
    }
}
