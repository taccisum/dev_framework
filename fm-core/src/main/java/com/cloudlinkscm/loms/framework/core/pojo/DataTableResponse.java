package com.cloudlinkscm.loms.framework.core.pojo;

/**
 * @author : tac
 * @date : 2017/5/22
 */
public class DataTableResponse<T> {
    private Long returnTotalItems;
    private T data;

    public Long getReturnTotalItems() {
        return returnTotalItems;
    }

    public void setReturnTotalItems(Integer returnTotalItems) {
        this.returnTotalItems = returnTotalItems.longValue();
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
}
