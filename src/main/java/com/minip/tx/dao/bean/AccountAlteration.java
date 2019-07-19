package com.minip.tx.dao.bean;

import com.minip.tx.utils.TimeUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountAlteration {
    private String alterMoney;
    private int alterType;
    private String alterTime;

    public String getAlterMoney() {
        return alterMoney;
    }

    public void setAlterMoney(BigDecimal alterMoney) {
        this.alterMoney = alterMoney.toString();
    }

    public int getAlterType() {
        return alterType;
    }

    public void setAlterType(int alterType) {
        this.alterType = alterType;
    }

    public String getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Timestamp alterTime) {
        this.alterTime = TimeUtils.transDayTime(alterTime);
    }
}
