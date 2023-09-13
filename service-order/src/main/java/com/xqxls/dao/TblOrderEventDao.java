package com.xqxls.dao;

import com.xqxls.entity.TblOrderEvent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TblOrderEventDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TblOrderEvent record);

    int insertSelective(TblOrderEvent record);

    TblOrderEvent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblOrderEvent record);

    int updateByPrimaryKey(TblOrderEvent record);

    List<TblOrderEvent> selectByOrderType(String orderType);

    int updateEvent(String orderType);
}