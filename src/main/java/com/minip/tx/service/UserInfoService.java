package com.minip.tx.service;

import com.minip.tx.utils.Result;

public interface UserInfoService {
    Result getUserInfo(String openId);
    Result register(String openId, String avatarUrl, String nickname);
}
