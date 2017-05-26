package com.cloudlinkscm.loms.framework.core.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author : tac
 * @date : 2017/5/26
 */
public class PaginationRequest {
    private static final int DEF_PAGE_INDEX = 1;
    private static final int DEF_PAGE_SIZE = 20;
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getPageIndex() {
        if(pageIndex == null || pageIndex <= 0){
            return DEF_PAGE_INDEX;
        }
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize <= 0){
            return DEF_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonIgnore
    public Integer getOffset() {
        return (getPageIndex() - 1) * getPageSize();
    }

    @JsonIgnore
    public Integer getLimit() {
        return getPageIndex() * getPageSize();
    }
}
