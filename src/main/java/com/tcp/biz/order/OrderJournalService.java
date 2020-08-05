package com.tcp.biz.order;

import com.tcp.bean.LogOrderJournal;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.LogOrderJournalMapper;
import com.tcp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderJournalService {

    @Autowired
    private LogOrderJournalMapper orderJournalMapper;


    /**
     * 保存订单状态流水
     * @param orderJournal
     * @return
     */
    public int saveOrderJounal(LogOrderJournal orderJournal) {
        orderJournal.setId(StringUtil.get32UUID());
        orderJournal.setCreateTime(new Date());
        return  orderJournalMapper.insert(orderJournal);
    }

    /**
     *
     * 保存订单状态流水
     * @param orderNo
     * @param orderStat
     * @return
     */
    public int saveOrderJounal(String orderNo, DictCodeEnum.OrderStat orderStat) {
        LogOrderJournal orderJournal = new LogOrderJournal();
        orderJournal.setOrderNo(orderNo);
        orderJournal.setOderCodeType(orderStat.name());
        orderJournal.setOrderCode(orderStat.getCode());
        return  saveOrderJounal(orderJournal);
    }


    /**
     * 查询订单状态流水
     * @param orderNo
     * @return
     */
    public List<LogOrderJournal> getOrderJournals(String orderNo) {
        return orderJournalMapper.getList(Collections.singletonMap("orderNo", orderNo));
    }

}
