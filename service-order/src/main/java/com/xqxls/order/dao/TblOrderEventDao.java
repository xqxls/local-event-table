package com.xqxls.order.dao;

import com.xqxls.order.entity.TblOrderEvent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/9/13 11:07
 */
@Component
@Mapper
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