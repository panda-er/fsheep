package com.minip.tx.dao.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountInfoVo {
    private String openId;
    private BigDecimal accountBalance;
    private BigDecimal withdrawCash;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash;
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
