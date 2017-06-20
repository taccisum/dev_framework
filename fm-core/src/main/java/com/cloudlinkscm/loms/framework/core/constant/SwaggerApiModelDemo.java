package com.cloudlinkscm.loms.framework.core.constant;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author : tac
 * @date : 2017/6/20
 */
@Deprecated
public final class SwaggerApiModelDemo {
    @ApiModelProperty(value = "字段1", position = 0, required = false, example = "example value1")
    private String field1;
    @ApiModelProperty(value = "字段2", position = 10, required = false, example = "example value2")
    private String field2;
    @ApiModelProperty(value = "字段3", position = 20, required = true, example = "example value3")
    private String field3;
    @ApiModelProperty(value = "需要传入uuid的字段", position = 30, required = true, example = SwaggerExample.UUID)
    private String uuidField;
    @ApiModelProperty(value = "需要传入字典key的字段", position = 40, required = true, example = SwaggerExample.DICTIONARY)
    private String dictionaryField;
    @ApiModelProperty(value = "需要传入指定格式日期字符串的字段", position = 50, required = true, example = SwaggerExample.DATE)
    private String dateField;
}
