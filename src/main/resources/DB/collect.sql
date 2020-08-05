/*
Navicat MySQL Data Transfer

Source Server         : dev003_数据库
Source Server Version : 50626
Source Host           : 112.74.169.195:3306
Source Database       : collect

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-08-03 09:59:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_charge_end_childinfo
-- ----------------------------
DROP TABLE IF EXISTS `b_charge_end_childinfo`;
CREATE TABLE `b_charge_end_childinfo` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `parent_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT ' 父键',
  `child_startime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `child_endtime` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `pow_total` int(255) DEFAULT NULL COMMENT '电量',
  `charging_service_price` int(255) DEFAULT NULL COMMENT '服务费单价',
  `price` int(255) DEFAULT NULL COMMENT '单价',
  `charging_serviceamount` int(255) DEFAULT NULL COMMENT '服务费金额(比例 0.01单位 元)',
  `charging_amount` int(255) DEFAULT NULL COMMENT '充电金额比例 0.01单位 元',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电结束电价信息详情';

-- ----------------------------
-- Table structure for b_charge_end_info
-- ----------------------------
DROP TABLE IF EXISTS `b_charge_end_info`;
CREATE TABLE `b_charge_end_info` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `data_num` int(11) DEFAULT NULL COMMENT '数据个数',
  `serial_num` int(255) DEFAULT NULL COMMENT '记录序列号',
  `how_long` int(255) DEFAULT NULL COMMENT '本序号数据长度',
  `gun_num` int(11) DEFAULT NULL COMMENT '枪口号',
  `account_type` int(255) DEFAULT NULL COMMENT '充电账号类型:0.卡号1.服务器账号2.VIN启动',
  `cardNum` bigint(20) DEFAULT NULL COMMENT '卡号',
  `account` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `vin` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆VIN',
  `charging_startime` bigint(20) DEFAULT NULL COMMENT '充电开始时间',
  `charging_endtime` bigint(20) DEFAULT NULL COMMENT '充电结束时间',
  `power_sum` int(255) DEFAULT NULL COMMENT '充电总电量',
  `power_servce` int(255) DEFAULT NULL COMMENT '充电服务费',
  `power_amount` int(255) DEFAULT NULL COMMENT '充电总金额',
  `charging_time_num` int(11) DEFAULT NULL COMMENT '充电经过的时间段个数',
  `star_soc` int(255) DEFAULT NULL COMMENT '起始SOC',
  `end_soc` int(255) DEFAULT NULL COMMENT '终止SOC',
  `init_kilowatt` int(255) DEFAULT NULL COMMENT '起始充电时电表读数',
  `end_kilowatt` int(255) DEFAULT NULL COMMENT '终止充电时电表读数',
  `start_up` tinyint(4) DEFAULT NULL COMMENT '启动方式 0 刷卡启动 1 app启动',
  `order_no` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电结束数据';

-- ----------------------------
-- Table structure for b_charging_info
-- ----------------------------
DROP TABLE IF EXISTS `b_charging_info`;
CREATE TABLE `b_charging_info` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪号',
  `charging_type` tinyint(255) DEFAULT NULL COMMENT '1. 直流 2. 交流',
  `outputA` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输出交流A相电压',
  `outputB` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输出交流B相电压',
  `outputC` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输出交流C相电压',
  `flowA` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交流A相电流',
  `flowB` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交流B相电流',
  `flowC` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '交流C相电流',
  `dc_voltage` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电直流电压',
  `d_current` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电直流电流',
  `charging_rate` int(11) DEFAULT NULL COMMENT '当前充电桩功率',
  `use_power` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '已充电量',
  `soc` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '当前车辆SOC',
  `vin` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆VIN号',
  `ammeter_reading` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '当前电表读数',
  `when_long` int(32) DEFAULT NULL COMMENT '已充时长',
  `surplus_when_long` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '估算剩余充电时间',
  `pile_temperature` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电桩内部温度',
  `muzz_temperature` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电枪温度',
  `dc_temperature_num` tinyint(32) DEFAULT NULL COMMENT '单节电池最高温度号',
  `dcTemperature` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单节电池最高温度',
  `dcdy_num` tinyint(32) DEFAULT NULL COMMENT '单节电池最高电压号',
  `dcdy` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单节电池最高电压',
  `dcdy_min_num` tinyint(4) DEFAULT NULL COMMENT '电池最低电压号',
  `dcdy_min` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单节电池最低电压',
  `yxcdzgdy` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '允许充电最高电压',
  `yxcddjzgdy` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '允许充电单节最高电压',
  `yxcddjzgwd` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '允许充电单节最高温度',
  `accounts_type` tinyint(4) DEFAULT NULL COMMENT '充电账号类型:0.卡号 1.服务器账号 2.VIN启动',
  `card_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡号:充电账号类型为 卡号时 读取此数 据',
  `charging_accounts` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号:充电账号为服务器账号时读取次数 据',
  `order_num` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `balance` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号实时余额',
  `prepaid_balance` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '预付费余额',
  `free_balance` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '免费余额',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电数据表';

-- ----------------------------
-- Table structure for b_gun_status_log
-- ----------------------------
DROP TABLE IF EXISTS `b_gun_status_log`;
CREATE TABLE `b_gun_status_log` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口号',
  `manager_state` tinyint(255) DEFAULT NULL COMMENT '管理状态:1. 正常运营状态\n2. 故障\n3. 离网(用于充电桩标记自己\n当前状态，无法上传到运营\n平台)\n4. 离网数据上传中\n5. 维护',
  `charging_state` tinyint(255) DEFAULT NULL COMMENT '充电状态:1. 空闲\n2. 充电枪已连接，未启动\n充电\n3. 启动中(已发启动命\n令，等待充电枪连接 。 或者启动充电的过程都 定义为启动中)\n4. 充电中\n5. 充电完成\n6. 已预约\n7. 等待充电中(预约已连\n接车，但未启动充电状\n态)',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='状态改变信息';

-- ----------------------------
-- Table structure for b_offline_childinfo
-- ----------------------------
DROP TABLE IF EXISTS `b_offline_childinfo`;
CREATE TABLE `b_offline_childinfo` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `parent_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT ' 父键',
  `child_startime` datetime DEFAULT NULL COMMENT '开始时间',
  `child_endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `pow_total` int(255) DEFAULT NULL COMMENT '电量',
  `charging_service_price` decimal(18,2) DEFAULT NULL COMMENT '服务费单价',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `charging_serviceamount` decimal(18,2) DEFAULT NULL COMMENT '服务费金额(比例 0.01单位 元)',
  `charging_amount` decimal(18,2) DEFAULT NULL COMMENT '充电金额比例 0.01单位 元',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='离线数据电价信息详情';

-- ----------------------------
-- Table structure for b_offlineInfo
-- ----------------------------
DROP TABLE IF EXISTS `b_offlineInfo`;
CREATE TABLE `b_offlineInfo` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `data_num` int(11) DEFAULT NULL COMMENT '数据个数',
  `serial_num` int(255) DEFAULT NULL COMMENT '记录序列号',
  `how_long` int(255) DEFAULT NULL COMMENT '本序号数据长度',
  `gun_num` int(11) DEFAULT NULL COMMENT '枪口号',
  `account_type` int(255) DEFAULT NULL COMMENT '充电账号类型:0.卡号 1.服务器账号 2.VIN启动',
  `cardNum` bigint(20) DEFAULT NULL COMMENT '卡号',
  `account` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `vin` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆VIN',
  `charging_startime` datetime DEFAULT NULL COMMENT '充电开始时间',
  `charging_endtime` datetime DEFAULT NULL COMMENT '充电结束时间',
  `power_sum` int(255) DEFAULT NULL COMMENT '充电总电量',
  `power_servce` decimal(18,2) DEFAULT NULL COMMENT '充电服务费',
  `power_amount` decimal(18,2) DEFAULT NULL COMMENT '充电总金额',
  `charging_time_num` int(11) DEFAULT NULL COMMENT '充电经过的时间段个数',
  `star_soc` int(255) DEFAULT NULL COMMENT '起始SOC',
  `end_soc` int(255) DEFAULT NULL COMMENT '终止SOC',
  `init_kilowatt` int(255) DEFAULT NULL COMMENT '起始充电时电表读数',
  `end_kilowatt` int(255) DEFAULT NULL COMMENT '终止充电时电表读数',
  `order_no` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `state` tinyint(255) DEFAULT NULL COMMENT '是否处理成功:0未处理,1已处理',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='离线数据信息';

-- ----------------------------
-- Table structure for b_order_charging_info
-- ----------------------------
DROP TABLE IF EXISTS `b_order_charging_info`;
CREATE TABLE `b_order_charging_info` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '启动枪口号',
  `star_type` tinyint(255) DEFAULT NULL COMMENT '1. 充电系统启动\n2. 运营平台发送启动\n3. 其他方式启动\n4. 刷卡启动\n5. VIN启动',
  `end_type` tinyint(255) DEFAULT NULL COMMENT '1. 充满停止\n2. 主动停止\n3. 枪连接断开停止\n4. 故障停止(详情查看故障信\n息)。\n5. 异常停止\n6. 余额不足停止\n7. 失电停止',
  `star_account` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '启动账号',
  `account_balance` decimal(18,2) DEFAULT NULL COMMENT '账号余额',
  `prepay_balance` decimal(18,2) DEFAULT NULL COMMENT '预付费余额',
  `free_balance` decimal(18,2) DEFAULT NULL COMMENT '免费余额',
  `kilowatt_now` decimal(64,2) DEFAULT NULL COMMENT '当前电表读数',
  `order_no` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `order_type` tinyint(255) DEFAULT NULL COMMENT '订单类型:0停止单,1启动单',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电订单信息表';

-- ----------------------------
-- Table structure for b_pile_circle_data
-- ----------------------------
DROP TABLE IF EXISTS `b_pile_circle_data`;
CREATE TABLE `b_pile_circle_data` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口号',
  `manager_state` tinyint(4) DEFAULT NULL COMMENT '管理状态:1. 正常运营状态\n2. 故障\n3. 离网(用于充电桩标记自己\n当前状态，无法上传到运\n营平台)\n4. 离网数据上传中\n5. 维护',
  `charging_state` tinyint(4) DEFAULT NULL COMMENT '充电状态:1. 空闲\n2. 充电枪已连接，未启动充电\n3. 启动中(已发启动命令，等\n待充电枪连接 。或者启动充\n  电的过程都定义为启动中)\n4. 充电中\n5. 充电完成\n6. 已预约\n7. 等待充电中(预约已连接\n  车，但未启动充电状态)',
  `net_type` tinyint(255) DEFAULT NULL COMMENT '网络连接方式:0 有线网络 1无线网络 2未知连接',
  `network` tinyint(255) DEFAULT NULL COMMENT '网络信号',
  `pile_voltage` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电桩当前电压',
  `pile_current` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电桩当前电流',
  `ammeter_reading` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '当前电表读数',
  `pile_temperature` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电桩内部温度',
  `muzz_temperature` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电枪温度',
  `inputA` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输入A相电压',
  `inputB` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输入B相电压',
  `inputC` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '输入C相电压',
  `flowA` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'A相电流',
  `flowB` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'B相电流',
  `flowC` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'C相电流',
  `charging_rate` int(32) DEFAULT NULL COMMENT '当前充电桩功率',
  `gun_use_num` bigint(20) DEFAULT NULL COMMENT '充电枪使用次数',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时数据';

-- ----------------------------
-- Table structure for charge_end_childoffline
-- ----------------------------
DROP TABLE IF EXISTS `charge_end_childoffline`;
CREATE TABLE `charge_end_childoffline` (
  `offline_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据包ID',
  `child_offline_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间段详情ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电结束数据和电价详情中间表';

-- ----------------------------
-- Table structure for factory_pile
-- ----------------------------
DROP TABLE IF EXISTS `factory_pile`;
CREATE TABLE `factory_pile` (
  `fac_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '厂商id',
  `pile_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '电桩id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for log_card_result
-- ----------------------------
DROP TABLE IF EXISTS `log_card_result`;
CREATE TABLE `log_card_result` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口编号',
  `result_num` tinyint(4) DEFAULT NULL COMMENT '结果0，成功 1，失败',
  `card_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡号',
  `order_num` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='刷卡启动结果';

-- ----------------------------
-- Table structure for log_card_verification
-- ----------------------------
DROP TABLE IF EXISTS `log_card_verification`;
CREATE TABLE `log_card_verification` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪号',
  `card_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡号',
  `card_out_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '卡外编号',
  `card_password` varchar(128) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '卡密码:没有此值填0，为卡用户 密码，可更改',
  `vin` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆VIN',
  `charging_type` tinyint(255) DEFAULT NULL COMMENT '充电方式:0，默认充满 1，按电量充 2，按金额充 3，按时间充',
  `charging_type_value` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '充电方式参数',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='刷卡验证';

-- ----------------------------
-- Table structure for log_fault_code
-- ----------------------------
DROP TABLE IF EXISTS `log_fault_code`;
CREATE TABLE `log_fault_code` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '充电枪口号',
  `fault_code` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '故障码',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='故障信息表';

-- ----------------------------
-- Table structure for log_ms_data
-- ----------------------------
DROP TABLE IF EXISTS `log_ms_data`;
CREATE TABLE `log_ms_data` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口编号',
  `dc_type` tinyint(4) DEFAULT NULL COMMENT '电池类型HEX码01H 铅酸电池；02H镍氢电池；03H磷酸铁锂电池；04H 锰酸锂电池；05H钴酸锂电池；06H 三元材料电池；07H 聚合物锂离⼦电池；08H 钛酸锂电池；',
  `dc_tcp_type` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电池通信版本',
  `bms_version` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'BMS 软件版本号',
  `dc_dealer` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电池⽣产⼚商',
  `dcz_serial` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电池组序号',
  `dcz_charge_number` int(15) DEFAULT NULL COMMENT '电池组充电次数',
  `dcz_create_time` datetime DEFAULT NULL COMMENT '电池组⽣产⽇期',
  `dz_demand` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电压需求',
  `dl_demand` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电流需求',
  `dc_rated_capacity` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电池额定容量',
  `dc_rated_voltage` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电池额定总电压',
  `rechargeable_voltage_max` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '单体动⼒蓄电池最⾼允许充电电压',
  `rechargeable_current_max` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最⾼允许充电电流',
  `rechargeable_temperature_max` int(11) DEFAULT NULL COMMENT '最⾼允许温度',
  `rechargeable_status` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '整车动⼒蓄电池荷电状态 0~100%；',
  `dc_voltage_now` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '整车动⼒蓄电池当前电池电压',
  `voltage_out_max` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最⾼输出电压',
  `voltage_out_min` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最低输出电压',
  `current_out_max` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最⼤输出电流',
  `current_out_min` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最小输出电流',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='BMS返回信息';

-- ----------------------------
-- Table structure for log_tcp_message
-- ----------------------------
DROP TABLE IF EXISTS `log_tcp_message`;
CREATE TABLE `log_tcp_message` (
  `message_id` varchar(50) NOT NULL,
  `message_cmd` int(5) DEFAULT NULL COMMENT '报文命令',
  `message_info` varchar(1024) DEFAULT NULL COMMENT '报文信息',
  `message_type` int(1) DEFAULT '1' COMMENT '1-联盟，2-盛宏',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='协议报文记录';

-- ----------------------------
-- Table structure for log_vin_charge
-- ----------------------------
DROP TABLE IF EXISTS `log_vin_charge`;
CREATE TABLE `log_vin_charge` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口编号',
  `vin_num` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '车辆VIN',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='vin验证充电';

-- ----------------------------
-- Table structure for log_warning_protect_code
-- ----------------------------
DROP TABLE IF EXISTS `log_warning_protect_code`;
CREATE TABLE `log_warning_protect_code` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` tinyint(4) DEFAULT NULL COMMENT '枪口编号',
  `warning_code1` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '告警码1',
  `warning_code2` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '告警码2',
  `protect_code1` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保护码1',
  `protect_code2` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保护码2',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='告警码及保护码';

-- ----------------------------
-- Table structure for m_control_start_stop
-- ----------------------------
DROP TABLE IF EXISTS `m_control_start_stop`;
CREATE TABLE `m_control_start_stop` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `pile_num` varchar(50) DEFAULT NULL COMMENT '枪号',
  `cmd` tinyint(5) DEFAULT NULL COMMENT '运营平台主动发送命令 1. 运营平台启动2. 运营平台停止3. 手机APP 启动4. 手机APP 停止',
  `count` varchar(50) DEFAULT NULL COMMENT '启动帐号',
  `balance` decimal(18,6) DEFAULT NULL COMMENT '账号余额',
  `pre_balance` decimal(18,6) DEFAULT NULL COMMENT '预付费余额',
  `free_balance` decimal(18,6) DEFAULT NULL COMMENT '免费余额',
  `order_num` varchar(50) DEFAULT NULL COMMENT '订单号',
  `assist_power_option` tinyint(3) DEFAULT NULL COMMENT '辅助电源选项 1，12V 2，24V',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送控制启停命令';

-- ----------------------------
-- Table structure for m_heart_beat
-- ----------------------------
DROP TABLE IF EXISTS `m_heart_beat`;
CREATE TABLE `m_heart_beat` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `reporting_period` varchar(50) DEFAULT NULL COMMENT '上报周期',
  `timeout_length` varchar(50) DEFAULT NULL COMMENT '超时时间长度',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 运营平台设置⼼跳包命令';

-- ----------------------------
-- Table structure for m_local_parameters
-- ----------------------------
DROP TABLE IF EXISTS `m_local_parameters`;
CREATE TABLE `m_local_parameters` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `phase_system` tinyint(3) DEFAULT NULL COMMENT '相系',
  `max_output` int(11) DEFAULT NULL COMMENT '设置最大输出电流 配置值 0 为不更改当前配置',
  `pile_work_pattern` tinyint(3) DEFAULT NULL COMMENT '设置充电桩⼯作模式 1. 恒压模式 2. 恒流模式',
  `off_net_charge` tinyint(3) DEFAULT NULL COMMENT '设置是否允许离⽹充电 1，允许离⽹充电 2，禁⽌离⽹充电 配置值 0 为不更改当前配置',
  `off_net_charge_num` int(11) DEFAULT NULL COMMENT '设置离⽹充电记录个数',
  `usable_gun_num` int(11) DEFAULT NULL COMMENT '设置可⽤枪⼜个数',
  `gun_use_num` int(11) DEFAULT NULL COMMENT '设置枪使⽤次数',
  `usable_pre_pile` tinyint(3) DEFAULT NULL COMMENT '设置充电桩是否⽀持预约 1⽀持 2禁⽌',
  `pile_view_loading_account` varchar(50) DEFAULT NULL COMMENT '设置充电桩显⽰屏登陆账号',
  `pile_view_loading_password` varchar(50) DEFAULT NULL COMMENT '设置充电桩显⽰屏登陆密码',
  `heart_beat_interval_time` int(11) DEFAULT NULL COMMENT '设置⼼跳包发送间隔时间',
  `server_ip` varchar(30) DEFAULT NULL COMMENT '设置服务器IP地址',
  `server_port` int(7) DEFAULT NULL COMMENT '设置服务器端口号',
  `qrcode_pile_num` int(11) DEFAULT NULL COMMENT '二维码对应的枪口号',
  `gun_id` int(11) DEFAULT NULL COMMENT '枪口号',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送远程配置充电设备本地参数命令';

-- ----------------------------
-- Table structure for m_pre_command
-- ----------------------------
DROP TABLE IF EXISTS `m_pre_command`;
CREATE TABLE `m_pre_command` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `pill_num` varchar(50) DEFAULT NULL COMMENT '枪号',
  `pre_type` tinyint(3) DEFAULT NULL COMMENT '预约 1. 开始预约 2. 停止预约',
  `pre_account` varchar(255) DEFAULT NULL COMMENT '预约账号',
  `pre_start_time` datetime DEFAULT NULL COMMENT '预约开始时间',
  `pre_end_time` datetime DEFAULT NULL COMMENT '预约结束时间',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送预约命令';

-- ----------------------------
-- Table structure for m_prescribed_rate
-- ----------------------------
DROP TABLE IF EXISTS `m_prescribed_rate`;
CREATE TABLE `m_prescribed_rate` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `gun_num` int(11) DEFAULT NULL COMMENT '枪号',
  `time_num` int(11) DEFAULT NULL COMMENT '时间段个数',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='主动充电费率及时间段';

-- ----------------------------
-- Table structure for m_prescribed_rate_info
-- ----------------------------
DROP TABLE IF EXISTS `m_prescribed_rate_info`;
CREATE TABLE `m_prescribed_rate_info` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `parent_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父键',
  `time_num` int(11) DEFAULT NULL COMMENT '时间段个数',
  `star_time` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间段结束',
  `power_rate` int(11) DEFAULT NULL COMMENT '电价',
  `service_rate` int(11) DEFAULT NULL COMMENT '服务费',
  `state` tinyint(4) DEFAULT NULL COMMENT '发送状态',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='主动充电费率及时间段详情';

-- ----------------------------
-- Table structure for m_remote_charging_config
-- ----------------------------
DROP TABLE IF EXISTS `m_remote_charging_config`;
CREATE TABLE `m_remote_charging_config` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_type` int(11) DEFAULT NULL COMMENT '设备类型:1. 直流快充 2. 直流慢充 3. 交流快充 4. 交流慢充 5. 交直流混合,0.为不更改当前配置',
  `pile_longitude` decimal(56,8) DEFAULT NULL COMMENT '经度:0不更改',
  `pile_latitude` decimal(56,8) DEFAULT NULL COMMENT '纬度:0不更改',
  `carport_num` int(11) DEFAULT NULL COMMENT '车位号:0不更改',
  `parking_num` int(11) DEFAULT NULL COMMENT '停车位号:0不更改',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `ip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `power_station_nupower_station_num` int(11) DEFAULT NULL COMMENT '所属电站编号:0不更改',
  `area_num` int(11) DEFAULT NULL COMMENT '所属地区编号:0不更改',
  `operation_type` int(11) DEFAULT NULL COMMENT '营运类型:1. 私有，不对外开放充电系统 2. 私有，对外开放充电系统 3. 公有免费充电系统 4. 公有收费充电系统,0不更改',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='主动远程配置系统';

-- ----------------------------
-- Table structure for m_upgrade_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `m_upgrade_blacklist`;
CREATE TABLE `m_upgrade_blacklist` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `search_information` tinyint(3) DEFAULT NULL COMMENT '查询信息 1. 下发 2.更新校准',
  `ftp_id` varchar(50) DEFAULT NULL COMMENT 'ftpid',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台下发/更新⿊名单';

-- ----------------------------
-- Table structure for m_upgrade_command
-- ----------------------------
DROP TABLE IF EXISTS `m_upgrade_command`;
CREATE TABLE `m_upgrade_command` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `ftp_length` int(11) DEFAULT NULL COMMENT 'FTP地址长度',
  `ftp` varchar(255) DEFAULT NULL,
  `ftp_account` varchar(255) DEFAULT NULL COMMENT 'FTP账号',
  `ftp_password` varchar(255) DEFAULT NULL COMMENT 'FTP密码',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送充电桩软件升级命令 FTP升级';

-- ----------------------------
-- Table structure for m_upgrade_pass
-- ----------------------------
DROP TABLE IF EXISTS `m_upgrade_pass`;
CREATE TABLE `m_upgrade_pass` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `gun_num` int(11) DEFAULT NULL,
  `new_pass` varchar(255) DEFAULT NULL COMMENT '新密钥',
  `start_time` datetime DEFAULT NULL COMMENT '更新时间',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送密钥更新命令';

-- ----------------------------
-- Table structure for m_upgrade_request_command
-- ----------------------------
DROP TABLE IF EXISTS `m_upgrade_request_command`;
CREATE TABLE `m_upgrade_request_command` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `upgrade_type` tinyint(3) DEFAULT NULL COMMENT '升级类型 1. 软件升级（通讯板软件） 2.固件升级（控制板软件）',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营平台发送充电桩软件升级请求命令';

-- ----------------------------
-- Table structure for m_upgrade_result
-- ----------------------------
DROP TABLE IF EXISTS `m_upgrade_result`;
CREATE TABLE `m_upgrade_result` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `upgrade_type` tinyint(3) DEFAULT NULL COMMENT '升级指⽰ 1，进⼊升级模式 2，升级数据  3，数据包下发完毕',
  `version` varchar(50) DEFAULT NULL COMMENT '软件版本号',
  `result_length` varchar(50) DEFAULT NULL COMMENT '总数据长度',
  `result_num` varchar(50) DEFAULT NULL COMMENT '总数据包数',
  `sent_result_length` varchar(50) DEFAULT NULL COMMENT '已发送数据长度',
  `now_result_num` varchar(50) DEFAULT NULL COMMENT '当前包数',
  `upgrade_local` varchar(255) DEFAULT NULL COMMENT '升级地址',
  `upgrade_result_length` varchar(50) DEFAULT NULL COMMENT '升级数据长度',
  `uograde_result` varchar(255) DEFAULT NULL COMMENT '升级数据',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 运营平台发送充电桩软件升级命令 数据包形式升级';

-- ----------------------------
-- Table structure for offline_childoffline
-- ----------------------------
DROP TABLE IF EXISTS `offline_childoffline`;
CREATE TABLE `offline_childoffline` (
  `offline_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '离线数据包ID',
  `child_offline_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '时间段详情ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='离线数据和电价详情中间表';

-- ----------------------------
-- Table structure for power_station
-- ----------------------------
DROP TABLE IF EXISTS `power_station`;
CREATE TABLE `power_station` (
  `power_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '电费模板id',
  `station_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '电站id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电费费率及电站中间表';

-- ----------------------------
-- Table structure for station_pile
-- ----------------------------
DROP TABLE IF EXISTS `station_pile`;
CREATE TABLE `station_pile` (
  `station_id` bigint(20) NOT NULL COMMENT '电站id',
  `pile_id` bigint(20) NOT NULL COMMENT '电桩id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电站电桩中间表';

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_button`;
CREATE TABLE `sys_button` (
  `bt_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '按钮主键',
  `func_id` bigint(20) DEFAULT NULL COMMENT '功能id',
  `bt_code` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能按钮编码',
  `bt_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '按钮名',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `field1` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单按钮管理表';

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `func_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '功能主键',
  `parent_id` bigint(20) NOT NULL COMMENT '父级菜单id',
  `func_code` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能编码',
  `func_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能页面名',
  `func_desc` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '功能页面描述',
  `func_action` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '页面url',
  `stauts` tinyint(8) DEFAULT NULL COMMENT '启动状态:1正常0停用',
  `sort_order` tinyint(8) DEFAULT NULL COMMENT '排序',
  `is_parent` tinyint(8) DEFAULT NULL COMMENT '是否父级菜单:1是0否',
  `fg_id` bigint(20) NOT NULL COMMENT '一级菜单id',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `field1` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统菜单功能表';

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `org_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_pinyin` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组织名称pinyin',
  `quanlujin` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组织全名',
  `org_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组织名',
  `org_level` bigint(20) DEFAULT NULL COMMENT '组织级别',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级id',
  `status` int(11) DEFAULT NULL COMMENT '启用状态',
  `remark` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `field1` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='公司组织管理表';

-- ----------------------------
-- Table structure for sys_r_role_button
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_role_button`;
CREATE TABLE `sys_r_role_button` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  `BT_ID` bigint(20) NOT NULL COMMENT '按钮id',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色功能按钮中间表';

-- ----------------------------
-- Table structure for sys_r_role_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_role_function`;
CREATE TABLE `sys_r_role_function` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id',
  `FUNC_ID` bigint(20) NOT NULL COMMENT '功能菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单功能中间表';

-- ----------------------------
-- Table structure for sys_r_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_user_role`;
CREATE TABLE `sys_r_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户id',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户角色中间表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `role_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建ip',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `field1` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `pwd` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `user_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `status` int(11) DEFAULT NULL COMMENT '状态:0停用1启用',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `email` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_ip` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `ext1` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `ext2` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `ext3` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `ext4` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='后台管理用户表';

-- ----------------------------
-- Table structure for t_factory_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_factory_manager`;
CREATE TABLE `t_factory_manager` (
  `fac_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '厂商名',
  `address` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '厂商地址',
  `phone` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '厂商电话',
  `linkman` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`fac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for t_gun
-- ----------------------------
DROP TABLE IF EXISTS `t_gun`;
CREATE TABLE `t_gun` (
  `gun_id` varchar(50) NOT NULL,
  `pile_num` varchar(50) DEFAULT NULL,
  `gun_type` int(1) DEFAULT NULL COMMENT '枪类型1-国标，2-欧标，3-美标，4-日标',
  `gun_admin_state` int(2) DEFAULT '1' COMMENT '1-正常，2-故障，3-离网，4-离线数据上传中，5-维护',
  `gun_charging_state` int(2) DEFAULT '1' COMMENT '充电状态：1-空闲，2-充电枪已连接，3-启动中，4-充电中，5-充电完成，6-已预约，7等待充电预约已插枪',
  `gun_temp` int(5) DEFAULT NULL COMMENT '充电枪温度比例0.1单位度',
  `gun_use_num` int(11) DEFAULT NULL COMMENT '使用次数',
  `gun_power` int(11) DEFAULT NULL COMMENT '当前功率',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`gun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电枪信息';

-- ----------------------------
-- Table structure for t_gun_qrcode
-- ----------------------------
DROP TABLE IF EXISTS `t_gun_qrcode`;
CREATE TABLE `t_gun_qrcode` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `gun_id` int(11) DEFAULT NULL COMMENT '枪口号',
  `gun_num_qrcode_length` int(11) DEFAULT NULL COMMENT '设置充电桩枪⼜1⼆维码长度',
  `gun_num_qrcode_result` varchar(255) DEFAULT NULL COMMENT '枪⼜1⼆维码数据',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ip',
  `version_information` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '版本信息',
  `command` int(11) DEFAULT NULL COMMENT '命令',
  `m_time` bigint(20) DEFAULT NULL COMMENT '传输时间戳',
  `pile_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '桩号',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='枪口二维码数据';

-- ----------------------------
-- Table structure for t_pile
-- ----------------------------
DROP TABLE IF EXISTS `t_pile`;
CREATE TABLE `t_pile` (
  `pile_num` varchar(50) NOT NULL DEFAULT '0' COMMENT '桩号',
  `pile_type` int(1) DEFAULT NULL COMMENT '1直流快充，2直流慢充，3交流快充，4交流慢充，5交直流混合',
  `pile_power` int(1) DEFAULT NULL COMMENT '功率1单位w',
  `pile_voltage` int(1) DEFAULT NULL COMMENT '电压比例0.1单位V',
  `pile_longitude` decimal(13,10) DEFAULT NULL COMMENT '经度',
  `pile_latitude` decimal(13,10) DEFAULT NULL COMMENT '纬度',
  `pile_phase_system` int(1) DEFAULT NULL COMMENT '相系：1-单相，2-三相',
  `pile_max_output` int(11) DEFAULT NULL COMMENT '最大输出电流',
  `pile_offnet_num` int(11) DEFAULT NULL COMMENT '离网个数',
  `pile_usable_gun_num` int(11) DEFAULT NULL COMMENT '可用枪个数',
  `pile_is_pre` int(1) DEFAULT NULL COMMENT '是否支持预约：1-支持，2-不支持',
  `pile_signin_account` varchar(11) DEFAULT NULL COMMENT '屏幕登录帐号',
  `pile_signin_password` varchar(11) DEFAULT NULL COMMENT '屏幕登录密码',
  `pile_heartbeat_time` int(5) DEFAULT NULL COMMENT '心调时间比例1单位s',
  `pile_service_ip` varchar(11) DEFAULT NULL COMMENT '服务器ip',
  `pile_service_port` int(5) DEFAULT NULL COMMENT '服务器端口',
  `pile_hardware_version` varchar(10) DEFAULT NULL COMMENT '硬件版本',
  `pile_software_version` varchar(10) DEFAULT NULL COMMENT '软件版本',
  `pile_key` varchar(50) DEFAULT NULL COMMENT '报文密钥',
  `pile_operate_type` int(1) DEFAULT NULL COMMENT '1-私有不开放，2-私有开放，3-公有免费，4-公有收费',
  `pile_signin_state` int(1) DEFAULT '2' COMMENT '1-已经注册入网，2-未注册入网',
  `pile_is_message` int(1) DEFAULT '0' COMMENT '0-不进行监听1-监听接收报文2-发送报文3-全部监听',
  `station_id` varchar(50) DEFAULT NULL COMMENT '电站id',
  `creation_time` datetime DEFAULT NULL COMMENT '创建日期',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `build_status` tinyint(3) DEFAULT '0' COMMENT '建站状态',
  `audit_status` tinyint(3) DEFAULT '0' COMMENT '审核状态',
  `field1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`pile_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充电桩信息';

-- ----------------------------
-- Table structure for t_pile_info
-- ----------------------------
DROP TABLE IF EXISTS `t_pile_info`;
CREATE TABLE `t_pile_info` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `pile_num` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩号',
  `type` tinyint(255) DEFAULT NULL COMMENT '设备类型:1. 直流快充 2. 直流慢充 3. 交流快充 4. 交流慢充 5. 交直流混合',
  `power` int(255) DEFAULT NULL COMMENT '额定功率',
  `voltage` int(255) DEFAULT NULL COMMENT '额定电压',
  `longitude` decimal(64,8) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(64,8) DEFAULT NULL COMMENT '纬度',
  `power_station_num` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属电站编号',
  `area_num` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属地区编号',
  `operation_type` tinyint(4) DEFAULT NULL COMMENT '营运类型:1. 私有，不对外开放充电系统 2. 私有，对外开放充电系统 3. 公有免费充电系统\n4. 公有收费充电系统',
  `gun_num_in` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '桩内编号',
  `muzzle_num` int(11) DEFAULT NULL COMMENT '枪口个数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电桩注册信息表';

-- ----------------------------
-- Table structure for t_pile_info_childinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_pile_info_childinfo`;
CREATE TABLE `t_pile_info_childinfo` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `parent_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '父键',
  `muzzle_num` int(11) DEFAULT NULL COMMENT '核对标识:枪口个数',
  `parking_num` int(11) DEFAULT NULL COMMENT '停车位号:枪又1或者分机1所占的车位号',
  `gun_type` tinyint(255) DEFAULT NULL COMMENT '充电枪口接口类型:1国标 2欧标\n3美标 4日标',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `field1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `field5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电桩注册信息详情表';

-- ----------------------------
-- Table structure for t_pile_station
-- ----------------------------
DROP TABLE IF EXISTS `t_pile_station`;
CREATE TABLE `t_pile_station` (
  `station_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '电站id',
  `name` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '站名',
  `address` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电站地址',
  `city` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属城市',
  `quick_charge` tinyint(4) DEFAULT NULL COMMENT '快充次数',
  `slow_charge` tinyint(4) DEFAULT NULL COMMENT '慢充次数',
  `parking_pay` decimal(18,6) DEFAULT NULL COMMENT '停车费',
  `template_pay` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '电费模板',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `linkman` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人',
  `link_phone` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系电话',
  `build_status` tinyint(3) DEFAULT '0' COMMENT '建站状态',
  `audit_status` tinyint(3) DEFAULT '0' COMMENT '审核状态',
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='充电站';

-- ----------------------------
-- Table structure for t_power_template_info
-- ----------------------------
DROP TABLE IF EXISTS `t_power_template_info`;
CREATE TABLE `t_power_template_info` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `power_id` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '父键',
  `star_time` datetime DEFAULT NULL COMMENT '开始时间段',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间段',
  `price` decimal(16,6) DEFAULT NULL COMMENT '电价(元)/度',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电费模板费率详情';

-- ----------------------------
-- Table structure for t_power_template_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_power_template_manager`;
CREATE TABLE `t_power_template_manager` (
  `power_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板名称',
  `mark_no` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模板标识号',
  `remark` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`power_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='电费费率表';

-- ----------------------------
-- Table structure for log_vin_result
-- ----------------------------
DROP TABLE IF EXISTS `log_vin_result`;
CREATE TABLE `log_vin_result` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `pile_num` varchar(50) DEFAULT NULL COMMENT '枪号',
  `verification_result` tinyint(4) DEFAULT NULL COMMENT '1，验证成功 2，失败，⽆此VIN记录 3，失败，桩车绑定验证失败',
  `gun_num` int(11) DEFAULT NULL COMMENT '枪口号',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单号',
  `recharge_amount` decimal(18,6) DEFAULT NULL COMMENT '充电金额',
  `deleted` tinyint(3) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆VIN验证回复信息';

