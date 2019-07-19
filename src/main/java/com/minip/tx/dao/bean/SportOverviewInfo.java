package com.minip.tx.dao.bean;

import java.sql.Timestamp;

public class SportOverviewInfo {
    private String openId;
    private Integer joinDays;
    private Integer victoryDays;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getJoinDays() {
        return joinDays;
    }

    public void setJoinDays(Integer joinDays) {
        this.joinDays = joinDays;
    }

    public Integer getVictoryDays() {
        return victoryDays;
    }

    public void setVictoryDays(Integer victoryDays) {
        this.victoryDays = victoryDays;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
