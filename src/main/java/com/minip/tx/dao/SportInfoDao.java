package com.minip.tx.dao;

import com.minip.tx.dao.bean.SportInfo;
import com.minip.tx.dao.bean.SportInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface SportInfoDao {
    public List<SportInfo> getSportInfoListByUser(@Param("openId") String openId);
    public int addSportInfo(@Param("sportInfoVo")SportInfoVo sportInfoVo);
    public int submitSport(@Param("sportInfoVo")SportInfoVo sportInfoVo);
    public SportInfo getSportInfo(@Param("openId")String openId, @Param("day")String day);
    public int accountSport(@Param("openId")String openId, @Param("day")String day, @Param("bonus")BigDecimal bonus);
}
