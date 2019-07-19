package com.minip.tx.dao.bean;

import com.minip.tx.utils.TimeUtils;
import java.math.BigDecimal;
import java.util.Date;

public class SportInfo {
    private String day;
    private Integer status;
    private int stepsSubmit;
    private String output;
    private String redPacket;

    public String getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = TimeUtils.transDay(day);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getStepsSubmit() {
        return stepsSubmit;
    }

    public void setStepsSubmit(int stepsSubmit) {
        this.stepsSubmit = stepsSubmit;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(BigDecimal output) {
        this.output = output.toString();
    }

    public String getRedPacket() {
        return redPacket;
    }

    public void setRedPacket(BigDecimal redPacket) {
        this.redPacket = redPacket.toString();
    }
}
