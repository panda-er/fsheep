package com.minip.tx.dao;

import com.minip.tx.dao.bean.SportOverviewInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Mapper
@Repository
public interface SportOverviewDao {
    public SportOverviewInfo getOverviewByUser(@Param("openId")String openId);
    public int increJoinDays(@Param("openId")String openId);
    public int increVicDays(@Param("openId")String openId);
    public int addSportOverviewInfo(@Param("sportOverviewInfo")SportOverviewInfo sportOverviewInfo);
}
