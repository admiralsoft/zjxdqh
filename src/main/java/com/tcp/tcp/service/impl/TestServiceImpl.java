
package com.tcp.tcp.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tcp.bean.JsonObject;
import com.tcp.bean.MControlStartStop;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.vo.send.vo.ActiveSendSetUpHeartbeatVo;
import com.tcp.util.DateUtil;
import com.tcp.util.MMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcp.bean.TGun;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.service.TestService;

@Service
public class TestServiceImpl extends BaseService<TGun> implements TestService {
	@Autowired
	RabbitMqSender mqSender;

	@Override
	public Map<String, Object> test() {
		return null;
	}

	@Override
	public Map<String, Object> start(String pileNum,Integer gunNum,int cmd, String account, Integer prepayment, int supportPower) {
		// TODO Auto-generated method stub
		JsonObject obj=new JsonObject();
		obj.setCode(101);
		obj.setPileNum(pileNum);
		obj.setTimestemp(System.currentTimeMillis());

		MControlStartStop mss=new MControlStartStop();
		mss.setCount(account);
		mss.setBalance(new BigDecimal(0));
		mss.setPreBalance(new BigDecimal(prepayment));
		mss.setGunNum(""+gunNum);
		mss.setAssistPowerOption((byte)supportPower);
		mss.setPileNum(pileNum);
		mss.setFreeBalance(new BigDecimal(0));
		mss.setCmd((byte)cmd);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			mss.setOrderNum(MMSUtil.getPileOrderSN(pileNum,gunNum,0));

			obj.setObj(mss);
			mqSender.sendRabbitmqCollectDirect(obj);

			map.put("code", 10000);
			map.put("msg", "操作成功");

		} catch (MMSUtil.TypeException e) {
			e.printStackTrace();
			map.put("code", 100);
			map.put("msg", "启动类型异常");
		} catch (MMSUtil.GunException e) {
			e.printStackTrace();
			map.put("code", 100);
			map.put("msg", "枪号异常");
		} catch (MMSUtil.PileException e) {
			e.printStackTrace();
			map.put("code", 100);
			map.put("msg", "桩号异常");
		} catch (MMSUtil.DataSupplementException e) {
			e.printStackTrace();
			map.put("code", 100);
			map.put("msg", "数据补位异常");
		}

		return map;
	}

	@Override
	public Map<String, Object> setHeart(String pileNum, Integer repottime, Integer overtime) {
		// TODO Auto-generated method stub
		// sendMQ("107," + pileNum + "," + repottime + "," + overtime);
		JsonObject obj=new JsonObject();
		obj.setCode(107);
		obj.setPileNum(pileNum);
		obj.setTimestemp(System.currentTimeMillis());
		ActiveSendSetUpHeartbeatVo vo=new ActiveSendSetUpHeartbeatVo();
		vo.setReportTime(repottime);
		vo.setOverTime(overtime);
		obj.setObj(vo);
		mqSender.sendRabbitmqCollectDirect(obj);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 10000);
		map.put("msg", "操作成功");
		return map;
	}


	@Override
	public void test1(){
		System.out.println(DateUtil.getNow() + " 发送信息 ");
		mqSender.sendDelay("10");
		mqSender.sendDelay("15");
		mqSender.sendDelay("5");
	}


}
