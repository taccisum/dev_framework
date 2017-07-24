package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * 定义装配器（负责dto与bo之间的转换工作）接口
 *
 * @author : tac
 * @date : 2017/6/15
 */


/**
 * @deprecated 请使用单向装配器
 * @see SingleAssembler
 */
public interface Assembler<DTO, BO> {
    DTO toDto(BO bo);
    BO toBo(DTO dto);
}
