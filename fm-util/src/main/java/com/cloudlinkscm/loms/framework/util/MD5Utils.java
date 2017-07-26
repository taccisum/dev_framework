package com.cloudlinkscm.loms.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具
 *
 * @author : tac
 * @date : 2017/7/25
 */
public abstract class MD5Utils {
    private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);
    public static String encrypt(String str){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("no such algorithm", e);
            throw new RuntimeException(e);
        }
        md.update(str.getBytes());
        byte[] digest = md.digest();
        return convertByteToHex(digest);
    }

    private static String convertByteToHex(byte[] byteData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
