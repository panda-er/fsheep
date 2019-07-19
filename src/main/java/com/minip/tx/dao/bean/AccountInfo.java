package com.minip.tx.dao.bean;


import java.math.BigDecimal;

public class AccountInfo {
    private String accountBalance;
    private String withdrawCash;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance.toString();
    }

    public String getWithdrawCash() {
        return withdrawCash;
    }

    public void setWithdrawCash(BigDecimal withdrawCash) {
        this.withdrawCash = withdrawCash.toString();
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "accountBalance='" + accountBalance + '\'' +
                ", withdrawCash='" + withdrawCash + '\'' +
                '}';
    }
}
