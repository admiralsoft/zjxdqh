package com.tcp.tcp.active.impl;

import com.alibaba.fastjson.JSONObject;
import com.tcp.bean.JsonObject;
import com.tcp.bean.MControlStartStop;
import com.tcp.bean.TGun;
import com.tcp.bean.TPile;
import com.tcp.biz.order.OrderJournalService;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.mapper.TPileMapper;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.tcp.vo.send.vo.SendStartChargingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 主动下发充停 0101
 * 
 * @author jiangzhilin
 *
 */
@Service("activeStartChargingServiceImp")
@Slf4j
public class ActiveStartChargingServiceImp extends AbStracActiveService<String> {

	@Autowired
	private OrderJournalService orderJournalService;
	@Autowired
	TPileMapper tPileMapper;

	@Override
	public DictCodeEnum.SendMessageResult activeSend(String t, String pileNum) {

		log.info("平台下发：0101"+"---------"+pileNum);

		JsonObject obj=new JsonObject();
		obj.setCode(MQCode.CHARGING);
		obj.setPileNum(pileNum);
		obj.setSuccess(false);

		MControlStartStop mss = JSONObject.parseObject(t, MControlStartStop.class);
		SendStartChargingVo vo = new SendStartChargingVo();
		vo.setCmd(mss.getCmd());
		vo.setF_monney(mss.getFreeBalance().toString());
		vo.setGunNum(Integer.valueOf(mss.getGunNum()));
		vo.setS_account(mss.getCount());
		vo.setS_balance(mss.getBalance().toString());
		vo.setS_prepayment(""+(mss.getPreBalance().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()));
		vo.setSn(mss.getOrderNum());
		vo.setSupport_power(mss.getAssistPowerOption());

		obj.setObj(vo);

		DictCodeEnum.SendMessageResult res= null;
		/**通知平台刷卡结果**/
		try {
			TPile pile = pileService.getPileById(pileNum);
//			TPile pile = tPileMapper.getPileByNum(pileNum);

			log.info("桩数据",JSONObject.toJSONString(pile));

			if (pile != null) {
				log.info("订单号：" + mss.getOrderNum());


					TGun gun = tGunMapper.selectByPG(pileNum, mss.getGunNum());
					if(gun!=null) {
						log.info("枪管理状态：" + gun.getGunAdminState());
						log.info("枪状态：" + gun.getGunChargingState());
						if (gun.getGunAdminState() == 1) {
							if (gun.getGunChargingState() == 2) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(true);
									obj.setMsg("下发成功");
									log.info("下发成功....");

									res = sendMessage(startChargingReultCommantImpl.getByteInfo(vo, pileNum, getCmd()), pileNum,
											getCmd());

									if (res == DictCodeEnum.SendMessageResult.NO_CHANNEL) {
										obj.setSuccess(false);
										obj.setMsg("桩已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_FAIL) {
										obj.setSuccess(false);
										obj.setMsg("该桩缓存通道错误，已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_OK) {

										sender.sendRabbitmqCollectDirect(obj);

										orderJournalService.saveOrderJounal(mss.getOrderNum(), DictCodeEnum.OrderStat.ORDER_ZBZ);

										redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + mss.getGunNum(), mss.getOrderNum());
										redisUtil.setValueExpire(RedisCode.SN, "orderNo:" + mss.getOrderNum(), mss.getPreBalance().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
									}
								} else {
									obj.setSuccess(false);
									obj.setMsg("该笔订单不可结束");
								}
							}
							else if (gun.getGunChargingState() == 1) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(false);
									obj.setMsg("请插枪");
								}
							}
							else if (gun.getGunChargingState() == 3) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(false);
									obj.setMsg("充电枪正在启动中，请稍候");
								}
							} else if (gun.getGunChargingState() == 4) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(false);
									obj.setMsg("该枪正在充电中，请换枪操作");
								} else {
									obj.setSuccess(true);
									obj.setMsg("下发成功");
									log.info("下发成功....");

									res = sendMessage(startChargingReultCommantImpl.getByteInfo(vo, pileNum, getCmd()), pileNum,
											getCmd());

									if (res == DictCodeEnum.SendMessageResult.NO_CHANNEL) {
										obj.setSuccess(false);
										obj.setMsg("桩已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_FAIL) {
										obj.setSuccess(false);
										obj.setMsg("该桩缓存通道错误，已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_OK) {

										sender.sendRabbitmqCollectDirect(obj);

										orderJournalService.saveOrderJounal(mss.getOrderNum(), DictCodeEnum.OrderStat.ORDER_ZBZ);

										redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + mss.getGunNum(), mss.getOrderNum());
										redisUtil.setValueExpire(RedisCode.SN, "orderNo:" + mss.getOrderNum(), mss.getPreBalance().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
									}
								}
							} else if (gun.getGunChargingState() == 5) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(true);
									obj.setMsg("下发成功");
									log.info("下发成功....");

									res = sendMessage(startChargingReultCommantImpl.getByteInfo(vo, pileNum, getCmd()), pileNum,
											getCmd());

									if (res == DictCodeEnum.SendMessageResult.NO_CHANNEL) {
										obj.setSuccess(false);
										obj.setMsg("桩已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_FAIL) {
										obj.setSuccess(false);
										obj.setMsg("该桩缓存通道错误，已离线");

									} else if (res == DictCodeEnum.SendMessageResult.SEND_OK) {

										sender.sendRabbitmqCollectDirect(obj);

										orderJournalService.saveOrderJounal(mss.getOrderNum(), DictCodeEnum.OrderStat.ORDER_ZBZ);

										redisUtil.setValueExpire(RedisCode.SN, pileNum + ":" + mss.getGunNum(), mss.getOrderNum());
										redisUtil.setValueExpire(RedisCode.SN, "orderNo:" + mss.getOrderNum(), mss.getPreBalance().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
									}
								}
							} else if (gun.getGunChargingState() == 6) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(false);
									obj.setMsg("该桩已被预约");
								}
							} else if (gun.getGunChargingState() == 7) {
								if (mss.getCmd() == 1 || mss.getCmd() == 3) {
									obj.setSuccess(false);
									obj.setMsg("该桩已被预约");
								}
							}
						} else if (gun.getGunAdminState() == 2) {
							obj.setSuccess(false);
							obj.setMsg("枪故障");
						} else if (gun.getGunAdminState() == 3) {
							obj.setSuccess(false);
							obj.setMsg("桩不在线");
						}
					}else{
						obj.setSuccess(false);
						obj.setMsg("暂无该枪");
					}
				}else{
				obj.setSuccess(false);
				obj.setMsg("桩缓存未获取");
			}

		}catch(Exception e){
			e.printStackTrace();
			obj.setMsg("暂无该桩,请检查桩号");
			obj.setSuccess(false);
			log.error("暂无该桩,请检查桩号",e);
		}
			if (!obj.isSuccess()) {
				sender.sendRabbitmqCollectDirect(obj);
			}
		return res;
	}

	@Override
	public short getCmd() {
		return TCPCode.START_CHARGING;
	}
}
