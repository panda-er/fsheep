package com.minip.tx.utils;

public class PageReq {
    protected Integer pageNow;
    protected Integer pageSize;
    public Integer getPageNow()
    {
        return pageNow;
    }
    public void setPageNow(Integer pageNow)
    {
        this.pageNow = pageNow;
    }
    public Integer getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
}
