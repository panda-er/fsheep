package com.minip.tx.dao;

import com.minip.tx.dao.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoDao {
    public UserInfo getUserInfoByOpenId(@Param("openId")String openId);
    public int register(@Param("userInfo")UserInfo userInfo);
    public UserInfo checkExist(@Param("openId")String openId);
    public int updatePhotoAndNickname(@Param("userInfo")UserInfo userInfo);
}
