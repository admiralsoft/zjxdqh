
package com.tcp.tcp.convert.command;

import com.tcp.util.DataUtil;

public class BaseCommand {

	/* 数据段开始下标 */
	public static final int	headLength	= 32;

	/**
	 * 尾加上完整数据
	 * 
	 * @param bytes
	 *            数据
	 * @param num
	 *            当前下表
	 * @return
	 */
	public static byte[] getData(byte[] bytes, int num) {

		/* 获取明文长度 */
		int length = num - 24;

		/* 数据包个数 */
		byte[] dataPacketNum = DataUtil.intToBytes(length);
		bytes[30] = dataPacketNum[0];
		bytes[31] = dataPacketNum[1];
		/* 获取密文填充字段 */
		int fill = 16 - (length % 16);
		/*------------------------ 进行加密填充 ---------------------------------*/
		/* 获取密文长度 */
		// int fillLength = length + fill;
		// /* 明文 */
		// byte[] plaintext = new byte[fillLength];
		// System.arraycopy(bytes, 24, plaintext, 0, length);
		// /* 获取密文 */
		// byte[] ciphertext = SMS4.encodeSMS4(plaintext, TCPCode.KEY);
		// /* 将密文放入bytes中 */
		// System.arraycopy(ciphertext, 0, bytes, 24, ciphertext.length);
		/* 添加填充字段 */
		num = num + fill;// 将填字段放入数组
		/* 效验码 */
		bytes[num++] = 0;
		bytes[num++] = 0;
		/* 包尾 */
		bytes[num++] = 0x0f;
		bytes[num++] = 0x02;
		/* 数据段长度 */
		byte[] dataLength = DataUtil.intToBytes(num);// 设置数据段长度 在此之前用到是站位符号
		bytes[2] = dataLength[0];
		bytes[3] = dataLength[1];
		byte[] data = new byte[num];
		System.arraycopy(bytes, 0, data, 0, num);
		byte[] checkCode = DataUtil.getCheckCode(data);// 检验码换算
		data[num - 4] = checkCode[0];
		data[num - 3] = checkCode[1];
		return data;
	}

	/**
	 * 主动发送信息 公共部分
	 * 
	 * @param equipmentNo
	 *            桩号
	 * @param cmd
	 *            命令
	 * @param packageNum
	 *            数据包个数
	 * @return
	 */
	public static byte[] getSendHead(String equipmentNo, int cmd, int packageNum) {

		byte[] bytes = new byte[1124];
		int num = 0;
		/* 头 */
		bytes[num++] = 0x06;
		bytes[num++] = 0x01;
		/* 数据长度 站位符号 */
		bytes[num++] = 0x00;
		bytes[num++] = 0x01;
		/* 密钥更新日期 */
		byte[] time = DataUtil.getTime();// 当前系统时间
		for (int i = 0; i < time.length; i++) {
			bytes[num++] = time[i];
		}
		/* 设备号 */
		byte[] equipment = DataUtil.StringToAsciiByte(equipmentNo, 16);
		for (int i = 0; i < 16; i++) {
			bytes[num++] = equipment[i];
		}

		byte[] cmdByte = DataUtil.intToBytes(cmd);
		/* cmd 时间校验 */
		bytes[num++] = cmdByte[0];
		bytes[num++] = cmdByte[1];

		/* 当前系统时间 */
		byte[] currentTime = DataUtil.getTime();
		for (int i = 0; i < currentTime.length; i++) {
			bytes[num++] = currentTime[i];
		}

		/* 包个数 */
		bytes[num++] = 0x00;
		bytes[num++] = 0x00;
		return bytes;
	}
}
