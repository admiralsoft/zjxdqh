
package com.tcp.tcp.service.impl;

import com.tcp.bean.JsonObject;
import com.tcp.bean.LogCardVerification;
import com.tcp.util.StringUtil;
import org.springframework.stereotype.Service;

import com.tcp.core.code.MQCode;
import com.tcp.tcp.vo.receive.vo.SignCardRequestVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * 1009验证帐号卡
 * 
 * @author jiangzhilin
 */
@Service("signCardRequestServiceImpl")
public class SignCardRequestServiceImpl extends BaseService<Object> implements TCPService {

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		SignCardRequestVo vo = signCardRequestParseImpl.getInfo(bytes);

		LogCardVerification lc=new LogCardVerification();
		lc.setGunNum((byte) vo.getGunNum().intValue());
		lc.setId(StringUtil.get32UUID());
		lc.setCardNum(vo.getCardNum());
		lc.setCardOutNum(vo.getOutCardNum());
		lc.setCardPassword(vo.getPwd());
		lc.setVin(vo.getVin());
		lc.setPileNum(pileNum);
		lc.setChargingType((byte)((int)vo.getCharging_type()));
		lc.setChargingTypeValue(vo.getCharging_data());
		lc.setCreateTime(new Date());
		lc.setModifyTime(new Date());
		cardSignMapper.insert(lc);

		OutUtil.println("桩体上报：1009" + pileNum + "--------" + vo.getGunNum());

		sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "验证帐号卡",MQCode.SIGN_CARD,true, vo));
	}
}
