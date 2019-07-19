package com.minip.tx.bean;

import com.minip.tx.utils.PageReq;

public class QueryKeywords extends PageReq{
    private String openId;

    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString()
    {
        StringBuffer logStr = new StringBuffer();
        logStr.append("openId = [")
                .append(openId)
                .append("],pageNow = [")
                .append(pageNow)
                .append("],pageSize = [")
                .append(pageSize)
                .append("].") ;
        return logStr.toString();
    }
}
