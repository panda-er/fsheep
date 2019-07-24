package com.minip.tx.dao.bean;

import com.minip.tx.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
public class AccountAlteration {
    private String alterMoney;
    private int alterType;
    private String alterTime;
}
