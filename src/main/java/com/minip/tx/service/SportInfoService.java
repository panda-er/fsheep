package com.minip.tx.service;

import com.minip.tx.utils.Result;

public interface SportInfoService {
    Result getSportListByUser(String openId, int pageNow, int pageSize);
}
