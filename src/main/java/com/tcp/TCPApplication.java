
package com.tcp;

import com.tcp.config.*;
import com.tcp.mq.config.RabbitMqConfig;
import com.tcp.mq.config.RabbitMqExchangeConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 项目启动入口
 */
@SpringBootApplication
@Import(value = {
		//数据库相关
		DatasourceConfig.class,          //数据源
		DruidConfig.class,               //连接池
		TransactionConfig.class,         //事务
		//        //持久层
		MyBatisConfig.class, MyBatisMapperScannerConfig.class,
		//rabbitMq
		RabbitMqConfig.class, RabbitMqExchangeConfig.class,
		//redis
		RedisConfig.class
})
public class TCPApplication {

	public static void main(String[] args) {
		SpringApplication.run(TCPApplication.class, args);
	}

}
