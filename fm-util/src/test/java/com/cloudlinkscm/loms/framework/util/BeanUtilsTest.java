package com.cloudlinkscm.loms.framework.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @author : tac
 * @date : 2017/7/4
 */
public class BeanUtilsTest {
    @Test
    public void copyPropertiesSelective() throws Exception {
        A a = new A("1", "2", null);
        B b = new B("a", "b", "c");
        BeanUtils.copyPropertiesSelective(a, b);
        Assert.assertEquals("1", b.getField1());
        Assert.assertEquals("2", b.getField2());
        Assert.assertEquals("c", b.getField3());
    }

    @Test
    public void extract() throws Exception {
        A a = new A("a", "b", "c");
        Map<String, Object> map = BeanUtils.extract(a);
        Assert.assertEquals(map.get("field1"), "a");
        Assert.assertEquals(map.get("field2"), "b");
        Assert.assertEquals(map.get("field3"), "c");
    }

    public class A {
        public A(String field1, String field2, String field3) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        private String field1;
        private String field2;
        private String field3;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getField3() {
            return field3;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }
    }

    public class B {
        public B(String field1, String field2, String field3) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        private String field1;
        private String field2;
        private String field3;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getField3() {
            return field3;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }
    }
}

