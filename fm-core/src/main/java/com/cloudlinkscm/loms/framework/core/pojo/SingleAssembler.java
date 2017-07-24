package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * 定义单向装配器
 *
 * @author : tac
 * @date : 2017/7/24
 */
public interface SingleAssembler<FROM, TO> {
    TO assemble(FROM from);
}
