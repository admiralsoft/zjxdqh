package com.tcp.tcp.service.impl;

import com.tcp.bean.BOrderChargingInfo;
import com.tcp.bean.JsonObject;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.biz.order.OrderService;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.StartChargingVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

import java.math.BigDecimal;

/**
 * 启动命令采集回复 0002
 * 
 * @author Administrator
 *
 */
@Service("startChargingFromPileServiceImp")
public class StartChargingFromPileServiceImp extends BaseService<Object> implements TCPService {

    @Autowired
    private OrderJournalService orderJournalService;


    @Autowired
    private OrderService orderService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		// TODO Auto-generated method stub
		StartChargingVo vo = startChargingFromPileParseImpl.getInfo(bytes);
		OutUtil.println("桩体上报：1002" + pileNum);

        String orderNo = (String) redisUtil.get(RedisCode.SN, pileNum + ":" + vo.getGun_num());
        // 枪口缓存的订单号与指令订单号不一致则清理前一条订单，缓存新的订单
        if (!StringUtil.isEmpty(orderNo) && !orderNo.equalsIgnoreCase(vo.getSn())) {
            redisUtil.remove(RedisCode.SN, "orderNo:" + orderNo);
            orderJournalService.saveOrderJounal(orderNo, DictCodeEnum.OrderStat.ORDER_YSX);
        }
        //保存新订单的流水
        redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + vo.getGun_num(), vo.getSn());
        orderJournalService.saveOrderJounal(vo.getSn(), DictCodeEnum.OrderStat.ORDER_CDZ);

//        JsonObject obj=new JsonObject();
//		obj.setCode(MQCode.START_RESULT);
//		obj.setPileNum(pileNum);
//		obj.setObj(vo);
//        obj.setSuccess(true);
//		/**通知平台启动结果**/
//		sender.sendRabbitmqCollectDirect(obj);

		sendMessage(ctx, startChargingToPileResultCommandImpl.getByteInfo(vo, pileNum, TCPCode.start_charging_back),
				pileNum, TCPCode.start_charging_back);
		//保存开始充电数据
        BOrderChargingInfo chargingInfo = new BOrderChargingInfo();
        chargingInfo.setPileNum(pileNum);
        chargingInfo.setGunNum(Byte.valueOf(String.valueOf(vo.getGun_num())));
        chargingInfo.setOrderNo(vo.getSn());
        chargingInfo.setAccountBalance(vo.getS_balance());
        chargingInfo.setFreeBalance(vo.getF_money());
        chargingInfo.setPrepayBalance(vo.getS_prepayment());
        chargingInfo.setKilowattNow(new BigDecimal(vo.getReadnum()));
        chargingInfo.setStarAccount(vo.getS_account());
        orderService.saveStartChargingInfo(chargingInfo);

	}

}
