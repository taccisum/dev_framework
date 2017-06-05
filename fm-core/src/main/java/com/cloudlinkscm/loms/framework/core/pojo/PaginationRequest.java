package com.cloudlinkscm.loms.framework.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author : tac
 * @date : 2017/5/26
 */
public class PaginationRequest<T> {
    private static final int DEF_OFFSET = 0;
    private static final int DEF_PAGE_SIZE = 20;
    private Integer itemFrom;
    private Integer itemTo;

    /**
     * @deprecated 由于该字段命名与实际意义不符，请避免在代码中直接使用该getter，使用 {@link #getOffset()} 方法代替
     */
    public Integer getItemFrom() {
        if (itemFrom == null || itemFrom < 0) {
            return DEF_OFFSET;
        }
        return itemFrom;
    }

    /**
     * @deprecated 由于该字段命名与实际意义不符，请避免在代码中直接使用该setter，使用 {@link #setOffset(Integer)}} 方法代替
     */
    public void setItemFrom(Integer itemFrom) {
        this.itemFrom = itemFrom;
    }

    /**
     * @deprecated 由于该字段命名与实际意义不符，请避免在代码中直接使用该getter，使用 {@link #getLimit()} 方法代替
     */
    public Integer getItemTo() {
        if (itemTo == null || itemTo <= 0) {
            return DEF_PAGE_SIZE;
        }
        return itemTo;
    }

    /**
     * @deprecated 由于该字段命名与实际意义不符，请避免在代码中直接使用该getter，使用 {@link #setLimit(Integer)} 方法代替
     */
    public void setItemTo(Integer itemTo) {
        this.itemTo = itemTo;
    }

    @JsonIgnore
    public Integer getOffset() {
        return getItemFrom();
    }

    @JsonIgnore
    public void setOffset(Integer offset) {
        setItemFrom(offset);
    }

    @JsonIgnore
    public Integer getLimit() {
        return getItemTo() - getItemFrom();
    }

    @JsonIgnore
    public void setLimit(Integer limit){
        setItemTo(getOffset() + limit);
    }

    @JsonIgnore
    public Integer getPageIndex(){
        return getOffset() / getLimit() + 1;
    }

    @JsonIgnore
    public Integer getPageSize(){
        return getLimit();
    }

}
