package com.xqxls.pay.task;

import com.xqxls.pay.dao.TblOrderEventDao;
import com.xqxls.pay.dao.TblPayDao;
import com.xqxls.pay.entity.TblOrderEvent;
import com.xqxls.pay.entity.TblPay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/9/13 11:28
 */
@Component
@Slf4j
public class ConsumerTask {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    @Autowired
    private TblPayDao tblPayDao;

    @Scheduled(cron="0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task(){
        List<TblOrderEvent> tblOrderEventList = tblOrderEventDao.selectByOrderType("1");
        for (int i = 0; i < tblOrderEventList.size(); i++) {
            TblOrderEvent event = tblOrderEventList.get(i);
            // 更改这条数据的orderType为2
            tblOrderEventDao.updateEvent(event.getOrderType());
            log.info("支付数据库事务表修改完成");
            TblPay pay = new TblPay();
            pay.setPayName("新建支付");
            tblPayDao.insert(pay);
            log.info("支付数据库新增支付数据");
        }
    }
}
