package com.xqxls.order.task;

import com.xqxls.order.dao.TblOrderEventDao;
import com.xqxls.order.entity.TblOrderEvent;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import java.util.List;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/9/13 11:07
 */
@Component
@Slf4j
public class ProduceTask {

    @Autowired
    private TblOrderEventDao tblOrderEventDao;

    @Autowired
    private Queue queue;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(cron="0/5 * * * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void task(){
        System.out.println("定时任务");

        List<TblOrderEvent> tblOrderEventList = tblOrderEventDao.selectByOrderType("1");
        for (int i = 0; i < tblOrderEventList.size(); i++) {
            TblOrderEvent event = tblOrderEventList.get(i);
            // 更改这条数据的orderType为2
            tblOrderEventDao.updateEvent(event.getOrderType());
            log.info("订单数据库事务表修改完成");
            jmsMessagingTemplate.convertAndSend(queue,JSONObject.fromObject(event).toString());

        }


    }

}
