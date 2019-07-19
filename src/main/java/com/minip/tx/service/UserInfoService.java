package com.minip.tx.service;

import com.minip.tx.utils.Result;

public interface UserInfoService {
    public Result getUserInfo(String openId);
    public Result register(String openId, String avatarUrl, String nickname);
}
