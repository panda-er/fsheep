package com.minip.tx.dao.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountAlterationVo {
    private String openId;
    private BigDecimal alterMoney;
    private Timestamp alterTime;
    private Integer alterType;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public BigDecimal getAlterMoney() {
        return alterMoney;
    }

    public void setAlterMoney(BigDecimal alterMoney) {
        this.alterMoney = alterMoney;
    }

    public Timestamp getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Timestamp alterTime) {
        this.alterTime = alterTime;
    }

    public Integer getAlterType() {
        return alterType;
    }

    public void setAlterType(Integer alterType) {
        this.alterType = alterType;
    }

    @Override
    public String toString() {
        return "AccountAlterationVo{" +
                "openId='" + openId + '\'' +
                ", alterMoney=" + alterMoney +
                ", alterTime=" + alterTime +
                ", alterType=" + alterType +
                '}';
    }
}
