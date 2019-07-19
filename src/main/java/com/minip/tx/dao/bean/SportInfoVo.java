package com.minip.tx.dao.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SportInfoVo {
    private String day;
    private String openId;
    private Integer stepsSubmit;
    private BigDecimal output;
    private BigDecimal redPacket;
    private Timestamp timeSubmit;
    private Integer status;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getStepsSubmit() {
        return stepsSubmit;
    }

    public void setStepsSubmit(Integer stepsSubmit) {
        this.stepsSubmit = stepsSubmit;
    }

    public BigDecimal getOutput() {
        return output;
    }

    public void setOutput(BigDecimal output) {
        this.output = output;
    }

    public BigDecimal getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(BigDecimal redPacket) {
        this.redPacket = redPacket;
    }

    public Timestamp getTimeSubmit() {
        return timeSubmit;
    }

    public void setTimeSubmit(Timestamp timeSubmit) {
        this.timeSubmit = timeSubmit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SportInfoVo{" +
                "day='" + day + '\'' +
                ", openId='" + openId + '\'' +
                ", stepsSubmit=" + stepsSubmit +
                ", output=" + output +
                ", redPacket=" + redPacket +
                ", timeSubmit=" + timeSubmit +
                ", status=" + status +
                '}';
    }
}
