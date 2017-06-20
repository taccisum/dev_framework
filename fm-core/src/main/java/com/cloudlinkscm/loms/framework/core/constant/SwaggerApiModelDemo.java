package com.cloudlinkscm.loms.framework.core.constant;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * @author : tac
 * @date : 2017/6/20
 */
@Deprecated
public final class SwaggerApiModelDemo {
    /**
     * 使用model接收http body中的参数时，使用{@link ApiModelProperty}注解
     */
    @ApiModelProperty(value = "字段1", position = 0, required = false, example = "example value1")
    private String field1;
    @ApiModelProperty(value = "字段2", position = 10, required = false, example = "example value2")
    private String field2;
    @ApiModelProperty(value = "字段3", position = 20, required = true, example = "example value3")
    private String field3;
    /**
     * 如果model中的字段用作绑定url中的请求参数，应使用{@link ApiParam}注解
     */
    @ApiParam(value = "字段4", required = true, defaultValue = "default value1")
    private String field4;
    /**
     * 可使用{@link SwaggerExample}中定义的常量来作为某些特殊类型的参数的example值
     */
    @ApiModelProperty(value = "需要传入uuid的字段", position = 40, required = true, example = SwaggerExample.UUID)
    private String uuidField;
    @ApiModelProperty(value = "需要传入字典key的字段", position = 50, required = true, example = SwaggerExample.DICTIONARY)
    private String dictionaryField;
    @ApiModelProperty(value = "需要传入指定格式日期字符串的字段", position = 60, required = true, example = SwaggerExample.DATE)
    private String dateField;
}
