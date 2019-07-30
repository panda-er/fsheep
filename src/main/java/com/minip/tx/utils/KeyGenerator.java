package com.minip.tx.utils;

import static com.minip.tx.common.constant.*;

public class KeyGenerator {

    public static String bonusByDay(String day){
        return BONUS_POOL + day;
    }

    public static String keyOfAchieveSet(String day){
        return ACHIEVE_SET + day;
    }

    public static String keyOfStatus(String openId, String day){
        return SPORT_STATUS + day + MAOHAO + openId;
    }
}
