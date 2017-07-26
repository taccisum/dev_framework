package com.cloudlinkscm.loms.framework.util;

import com.cloudlinkscm.loms.framework.util.exception.BeanUtilsException;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * BeanUtils facade
 * 对bean utils的调用做封装，若后续对性能有要求，可以很方便替换成更高效的bean utils
 *
 * @see org.springframework.beans.BeanUtils
 * @see org.apache.commons.beanutils.BeanUtils
 *
 * @author : tac
 * @date : 2017/5/24
 */

public abstract class BeanUtils {

    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    public static void copyPropertiesSelective(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void populate(Object bean, Map<String, ? extends Object> properties) {
        try {
            org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
        } catch (Exception e) {
            logger.error("BeanUtils.plpulate() exception", e);
            throw new BeanUtilsException(e);
        }
    }

    /**
     * 将bean的字段抽取出来作为一个map，暂不支持嵌套对象的抽取（嵌套对象的字段会被忽略）
     */
    public static Map<String, Object> extract(Object bean){
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
                }else{
                    //todo:: 抽取嵌套对象属性
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
