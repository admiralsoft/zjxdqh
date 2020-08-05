package com.tcp.tcp.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.BChargeEndChildinfo;
import com.tcp.bean.BChargeEndInfo;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.tcp.vo.receive.vo.EndChargingVo;
import com.tcp.util.OutUtil;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 桩返回充电结束的结算信息 1011
 * 
 * @author Administrator
 *
 */
@Service("endDataFromPileServiceImp")
public class EndDataFromPileServiceImp extends BaseService<Object> implements TCPService {

	@Autowired
	private OrderJournalService orderJournalService;

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
		EndChargingVo vo = endDataFromPileParseImpl.getInfo(bytes);


		if (!StringUtil.isEmpty(vo.getSn())) {
			redisUtil.remove(RedisCode.SN,pileNum+":"+vo.getGunNum());
			redisUtil.remove(RedisCode.SN,"orderNo:"+vo.getSn());
			orderJournalService.saveOrderJounal(vo.getSn(), DictCodeEnum.OrderStat.ORDER_YWC);
		}
		sendMessage(ctx, endDataToPileResultCommandImpl.getByteInfo(vo, pileNum, TCPCode.end_data_back), pileNum,
				TCPCode.end_data_back);

		logger.info("结算数据："+ JSONObject.toJSONString(vo));
		if(!StringUtil.isEmpty(vo.getSn())) {
			if (!((vo.getSn().substring(vo.getSn().length() - 1)).equalsIgnoreCase(String.valueOf(vo.getGunNum())))) {
				logger.warn("1011结算数据与参数信息不匹配，订单号：{},枪号：{}",vo.getSn(), vo.getGunNum());
				return;
			}
		}

		OutUtil.println("桩体上报：1011" + "--------" + pileNum);

		BChargeEndInfo vo2=new BChargeEndInfo();
		vo2.setId(StringUtil.get32UUID());
		vo2.setCreateTime(new Date());
		vo2.setDataNum(vo.getNum());
		vo2.setAccount(vo.getAccont());
		vo2.setAccountType(vo.getA_type());
		vo2.setSerialNum(vo.getLength());
		vo2.setGunNum(vo.getGunNum());
		vo2.setCardnum(vo.getCard_num());
		vo2.setVin(vo.getVin());
		vo2.setStartUp((byte)vo.getS_type());
		vo2.setChargingStartime(new Date(vo.getStime()*1000));
		vo2.setChargingEndtime(new Date(vo.getEtime()*1000));
		vo2.setStarSoc(vo.getSsoc());
		vo2.setEndSoc(vo.getEsoc());
		vo2.setPowerAmount(vo.getTotleMoney());
		vo2.setPowerServce(vo.getServer_price());
		vo2.setChargingTimeNum(vo.getTime_num());
		vo2.setPileNum(pileNum);
		vo2.setOrderNo(vo.getSn());
		vo2.setPowerSum(vo.getPower());
		vo2.setInitKilowatt(vo.getSread());
		vo2.setEndKilowatt(vo.getEread());

		/**数据入库**/
		endInfoMapper.insert(vo2);
		if (vo.getTimes() != null && !vo.getTimes().isEmpty()) {
			List<BChargeEndChildinfo> childs = new ArrayList<>();
			for (EndChargingVo.Times time : vo.getTimes()) {
				BChargeEndChildinfo childinf = new BChargeEndChildinfo();
				childinf.setId(StringUtil.get32UUID());
				childinf.setParentId(vo2.getId());
				childinf.setPowTotal(time.getPowers());
				childinf.setPrice(time.getEle_p_d());
				childinf.setChargingAmount(time.getEle_p());
				childinf.setChargingServiceamount(time.getServer_p());
				childinf.setChargingServicePrice(time.getServer_p_d());
				childinf.setChildStartime(new Date(time.getStimes()*1000));
				childinf.setChildEndtime(new Date(time.getEtimes()*1000));
				childinf.setCreateTime(vo2.getCreateTime());
				childs.add(childinf);
			}
			if (!childs.isEmpty())
				endChildinfoMapper.insertList(childs);
		}


		Integer endType = (Integer) redisUtil.get(RedisCode.SN, "end_type:" + vo.getSn());
		if (endType != null) {
			vo.setEnd_type(endType);
		}

		redisUtil.remove(RedisCode.SN,"end_type:"+vo.getSn());

		sender.sendRabbitmqCollectDirect(getResultObj(pileNum,"结算数据",MQCode.END_DATA,true,vo));
	}

}
