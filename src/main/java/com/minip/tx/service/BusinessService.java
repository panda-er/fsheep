package com.minip.tx.service;

import com.minip.tx.utils.Result;
import java.sql.Timestamp;

public interface BusinessService {
    public final static String BEFORE_ATTEND = "0";
    public final static String ATTEND_TO_ACHIEVE = "1";
    public final static int ATTEND_TO_ACHIEVE_INT = 1;
    public final static String ACHIEVE_TO_ACCOUNT = "2";
    public final static int ACHIEVE_TO_ACCOUNT_INT = 2;
    public final static String ALREADY_ACCOUNT = "3";
    public final static int ALTER_TYPE_BONUS = 1;
    public final static int ALTER_TYPE_WITHDRAW = 2;
    public final static int ALTER_TYPE_ATTEND = 0;

    public Result getBonus(String day);
    public Result attendTomSport(String openId, String day);
    public Result submissionToday(String openId, String day, int steps);
    public void initBonusPool();
    public Result querySportStatusByUser(String openId, String day);
    public Result transToDatabase();
    public Result login(String openId, String avatarUrl, String nickname);
}
