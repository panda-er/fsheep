package com.minip.tx.utils;

public class KeyGenerator {
    private final static String BONUS_POOL = "bonus_pool:";
    private final static String ACHIEVE_SET = "achieve_set:";
    private final static String SPORT_STATUS = "sport_status:";
    private final static String MAOHAO = ":";

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
