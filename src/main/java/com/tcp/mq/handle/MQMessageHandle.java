
package com.tcp.mq.handle;

import com.tcp.bean.JsonObject;
import com.tcp.mq.base.MQFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 进行消息处理
 */
@Component
@Qualifier("mqMessageHandle")
public class MQMessageHandle {

	@Autowired
	@Qualifier("mqFactoryImpl")
	protected MQFactory<JsonObject> mqFactoryImpl;

	public void process(JsonObject message) {

		mqFactoryImpl.mqMessage(message);
	}

	public void process(Object code, Object pileNum, JsonObject message) {

		mqFactoryImpl.mqMessage(code, pileNum, message);
	}

}
