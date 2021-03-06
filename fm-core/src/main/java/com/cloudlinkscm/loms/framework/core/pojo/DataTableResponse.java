package com.cloudlinkscm.loms.framework.core.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

/**
 * 定义前端表格控件使用的响应格式
 *
 * @author : tac
 * @date : 2017/5/22
 */
public class DataTableResponse<T> {
    @ApiModelProperty(value = "分页前的总条目数")
    private Long returnTotalItems;
    @ApiModelProperty(value = "分页后的数据")
    private T data;

    /**
     * 对应{@link #getReturnTotalItems()}的冗余字段
     * @return
     */
    public Long getReturnTotalItem(){
        return returnTotalItems;
    }

    public Long getReturnTotalItems() {
        return returnTotalItems;
    }

    public void setReturnTotalItems(Long returnTotalItems) {
        this.returnTotalItems = returnTotalItems;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static DataTableResponse empty(){
        DataTableResponse o = new DataTableResponse();
        o.setReturnTotalItems(0l);
        o.setData(new ArrayList<>());
        return o;
    }
}
