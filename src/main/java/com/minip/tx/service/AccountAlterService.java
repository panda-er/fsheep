package com.minip.tx.service;

import com.minip.tx.utils.Result;

public interface AccountAlterService {
    public Result getAlterList(String openId, int pageNow, int pageSize);
}
