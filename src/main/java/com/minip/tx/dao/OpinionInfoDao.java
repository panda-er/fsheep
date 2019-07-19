package com.minip.tx.dao;

import com.minip.tx.dao.bean.OpinionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OpinionInfoDao {
    public int addOpinion(@Param("opinionInfo")OpinionInfo opinionInfo);
}
