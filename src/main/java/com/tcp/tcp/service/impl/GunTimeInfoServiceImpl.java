
package com.tcp.tcp.service.impl;

import com.tcp.bean.BPileCircleData;
import com.tcp.bean.JsonObject;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.RedisCode;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Service;

import com.tcp.core.code.TCPCode;
import com.tcp.tcp.vo.receive.vo.GunTimeInfoVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.OutUtil;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * 定时数据
 */
@Service("gunTimeInfoServiceImpl")
public class GunTimeInfoServiceImpl extends BaseService<Object> implements TCPService {

    public static final Map<String, String> circleCache = new WeakHashMap<>();

	@Override
	public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

		BPileCircleData vo = gunTimeInfoParseImpl.getInfo(bytes);
		vo.setId(StringUtil.get32UUID());
		vo.setPileNum(pileNum);
		vo.setCreateTime(new Date());
		bPileCircleDataMapper.insert(vo);

		OutUtil.println("定时数据：" + pileNum + "--------" + vo.getGunNum());

		sendMessage(ctx, integerResultCommandImpl.getByteInfo((int) vo.getGunNum(), pileNum, TCPCode.time_info), pileNum, TCPCode.time_info);

		String circleGunValue
				= vo.getAmmeterReading()+"_"+vo.getNetType()+"_"+vo.getManagerState()+"_"+vo.getChargingState();
		String circleTimeKey = RedisCode.PILE_CIRCLE+pileNum+":"+vo.getGunNum();
		//String lastCircleValue = circleCache.get(circleTimeKey);
		//if (StringUtil.isEmpty(lastCircleValue) || !lastCircleValue.equalsIgnoreCase(circleGunValue)) {
            //circleCache.put(circleTimeKey, circleGunValue);
			//通知运营平台桩体状态
			sender.sendRabbitmqCollectDirect(getResultObj(pileNum, "定时数据", MQCode.GUN_TIME, true, vo));
		//} else {
			//logger.debug("桩体[{}]，枪号[{}],定时数据无变化 ", pileNum, vo.getGunNum());
		//}

	}

}
