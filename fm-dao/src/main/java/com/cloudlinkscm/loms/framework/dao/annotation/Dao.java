package com.cloudlinkscm.loms.framework.dao.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author : tac
 * @date : 2017/5/18
 */

@Target(ElementType.TYPE)
@Component
public @interface Dao {
}
