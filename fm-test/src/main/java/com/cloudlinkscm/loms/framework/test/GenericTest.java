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

import java.util.ArrayList;
import java.util.List;

/**
 * spring环境下通用的junit单元测试基类
 *
 * @author : tac
 * @date : 2017/5/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class GenericTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Fairy fairy = Fairy.create();

    protected List<Integer> range(int start, int end) {
        List<Integer> range = new ArrayList<>();
        for (int a = start; a < end; a++) {
            range.add(a);
        }
        return range;
    }

    protected List<Integer> range(int count) {
        return range(0, count);
    }
}
