package com.cloudlinkscm.loms.framework.util;

/**
 * BeanUtils facade
 * 对bean utils的调用做封装，若后续对性能有要求，可以很方便替换成更高效的bean utils
 *
 * @see org.springframework.beans.BeanUtils
 *
 * @author : tac
 * @date : 2017/5/24
 */

public abstract class BeanUtils {
    public static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
