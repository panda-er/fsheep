package com.minip.tx.dao;

import com.minip.tx.dao.bean.AccountInfo;
import com.minip.tx.dao.bean.AccountInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Mapper
public interface AccountInfoDao {
    AccountInfo getUserAccountByOpenId(@Param("openId")String openId);
    BigDecimal queryAccountNum(@Param("openId")String openId);
    int updateAccountNumByUser(@Param("openId")String openId, @Param("accountNum")BigDecimal accountNum);
    int increAccountNumByUser(@Param("openId")String openId, @Param("bonus")BigDecimal bonus);
    int addAccountInfo(@Param("accountInfoVo")AccountInfoVo accountInfoVo);
}
