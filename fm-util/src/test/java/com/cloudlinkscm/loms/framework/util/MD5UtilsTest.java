package com.cloudlinkscm.loms.framework.util;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

import static org.junit.Assert.*;

/**
 * @author : tac
 * @date : 2017/7/25
 */
public class MD5UtilsTest {
    @Test
    public void encrypt() {
        Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", MD5Utils.encrypt("123456").toLowerCase());
    }
}