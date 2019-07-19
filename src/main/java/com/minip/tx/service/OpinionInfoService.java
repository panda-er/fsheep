package com.minip.tx.service;

import com.minip.tx.utils.Result;

public interface OpinionInfoService {
    public Result submitOpinion(String openId, String nickname, String mail, String content);
}
