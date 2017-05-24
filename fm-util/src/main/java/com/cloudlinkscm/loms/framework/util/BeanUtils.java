package com.cloudlinkscm.loms.framework.util;

/**
 * BeanUtils facade
 * 对bean utils的调用做封装，若后续对性能有要求，方便替换成更高效的bean utils
 * @author : tac
 * @date : 2017/5/24
 */
public class BeanUtils {
    private static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
