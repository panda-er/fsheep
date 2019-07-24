package com.minip.tx.dao;

import com.minip.tx.dao.bean.AccountAlteration;
import com.minip.tx.dao.bean.AccountAlterationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountAlterInfoDao {
    List<AccountAlteration> getAlterInfoList(@Param("openId") String openId);
    int addAlterInfo(@Param("accountAlterationVo")AccountAlterationVo accountAlterationVo);
}
