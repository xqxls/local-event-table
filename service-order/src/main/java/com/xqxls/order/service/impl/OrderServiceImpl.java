package com.xqxls.order.service.impl;

import com.xqxls.order.dao.TblOrderDao;
import com.xqxls.order.dao.TblOrderEventDao;
import com.xqxls.order.entity.TblOrder;
import com.xqxls.order.entity.TblOrderEvent;
import com.xqxls.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/9/13 11:18
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    @Autowired
    private TblOrderDao tblOrderDao;

    @Transactional(rollbackFor = Exception.class)
    public void insertOrder(){
        TblOrder order = new TblOrder();
        order.setOrderName("新建订单");
        tblOrderDao.insert(order);
        log.info("订单数据库订单表新增数据");

        TblOrderEvent tblOrderEvent = new TblOrderEvent();
        tblOrderEvent.setOrderType("1");
        tblOrderEvent.setContent("新建订单");
        tblOrderEvent.setProcess("new");
        tblOrderEvent.setCreateTime(new Date());
        tblOrderEvent.setUpdateTime(new Date());
        tblOrderEventDao.insert(tblOrderEvent);
//        int i = 1/0;
        log.info("订单数据库事务表新增数据");
    }
}
