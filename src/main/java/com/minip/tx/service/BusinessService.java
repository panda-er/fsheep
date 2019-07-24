package com.minip.tx.service;

import com.minip.tx.utils.Result;
import java.sql.Timestamp;

public interface BusinessService {
    String BEFORE_ATTEND = "0";
    String ATTEND_TO_ACHIEVE = "1";
    int ATTEND_TO_ACHIEVE_INT = 1;
    String ACHIEVE_TO_ACCOUNT = "2";
    int ACHIEVE_TO_ACCOUNT_INT = 2;
    String ALREADY_ACCOUNT = "3";
    int ALTER_TYPE_BONUS = 1;
    int ALTER_TYPE_WITHDRAW = 2;
    int ALTER_TYPE_ATTEND = 0;

    Result getBonus(String day);
    Result attendTomSport(String openId, String day);
    Result submissionToday(String openId, String day, int steps);
    void initBonusPool();
    Result querySportStatusByUser(String openId, String day);
    Result transToDatabase();
    Result login(String openId, String avatarUrl, String nickname);
}
