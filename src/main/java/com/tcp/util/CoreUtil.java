
package com.tcp.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public class CoreUtil {

	public static String getRandomString() { // length表示生成字符串的长度

		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 字符串为 null 或者内部字符全部为 ' ' '\t' '\n' '\r' 这四类字符时返回 true
	 */
	public static boolean isBlank(String str) {

		if (str == null) { return true; }
		int len = str.length();
		if (len == 0) { return true; }
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
				case ' ' :
				case '\t' :
				case '\n' :
				case '\r' :
					// case '\b':
					// case '\f':
					break;
				default :
					return false;
			}
		}
		return true;
	}

	// 判断是否是是100的倍数
	public static boolean isMultiple(int dollar) {

		if (dollar % 100 == 0) { return true; }
		return false;
	}

	/**
	 * 获取ip
	 * 
	 * @return
	 */
	public static String getHost() {

		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}

}
