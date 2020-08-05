
package com.tcp.util;

public class OutUtil {

	// DEBUG来控制是否为调试模式
	private static final boolean	DEBUG	= true;

	public static void println(String str) {

		if (DEBUG) {
			System.out.println(str);
		}
	}

	public static void println(Integer str) {

		if (DEBUG) {
			System.out.println(str);
		}
	}
}
