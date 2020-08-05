package com.tcp.util;

import javax.security.auth.login.AccountException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * 联盟协议工具类
 * 
 * @author 蒋志林
 *
 */
public class MMSUtil {

	/**
	 * 测试入口
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	// byte[] card_num = DataUtil.stingTOBCD("1234567891");
	// byte[] out_card = DataUtil.StringToAsciiByte("0", 16);
	// System.out.println("卡：" + DataUtil.printHexByte(out_card));
	// try {
	// String sn = getPileOrderSN("6668880000000123", 1, 0);
	// System.out.println("\n订单号：" + sn);
	// byte[] bytes = DataUtil.StringToAsciiByte(sn, sn.length());
	// System.out.println("十六进制订单号：" + DataUtil.printHexByte(bytes));
	//
	// } catch (TypeException e) {
	// // TODO Auto-generated catch block
	// System.out.println("类型传参错误");
	// } catch (GunException e) {
	// // TODO Auto-generated catch block
	// System.out.println("枪编号不能为空");
	// } catch (PileException e) {
	// // TODO Auto-generated catch block
	// System.out.println("桩编号异常");
	// } catch (DataSupplementException e) {
	// // TODO Auto-generated catch block
	// System.out.println("数据长度异常");
	// }
	//
	// try {
	// String account = DataSupplement("18482180351", 32);
	// System.out.println("\n补账户：" + account);
	// byte[] bytes = DataUtil.StringToAsciiByte(account, account.length());
	// System.out.println("十六进制账户号：" + DataUtil.printHexByte(bytes));
	// } catch (DataSupplementException e) {
	// // TODO Auto-generated catch block
	// System.out.println("账户异常");
	// }
	// }

	/**
	 * 根据桩号、枪号、类型生成订单号
	 * 
	 * @param pileNum
	 * @param gunNum
	 * @param type
	 *            0，app启动；1，刷卡启动；2，后台启动订单
	 * @return
	 * @throws TypeException
	 * @throws GunException
	 * @throws PileException
	 * @throws DataSupplementException
	 */
	public static String getPileOrderSN(String pileNum, Integer gunNum, Integer type)
			throws TypeException, GunException, PileException, DataSupplementException {
		if (type == null) {
			throw new TypeException();
		}
		if (gunNum == null) {
			throw new GunException();
		}
		if (pileNum == null) {
			throw new PileException();
		}
		StringBuffer SN = new StringBuffer();
		switch (type) {
		case 0:
			SN.append("A");// app启动订单
			break;
		case 1:
			SN.append("C");// 刷卡启动订单
			break;
		case 2:
			SN.append("W");// 后台启动订单
			break;
		default:
			throw new TypeException();
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		SN.append(sf.format(System.currentTimeMillis()));
		// 联盟协议桩号16位
		if (pileNum.length() == 16) {
			SN.append(pileNum);
		} else {
			throw new PileException();
		}
		SN.append(gunNum);
		if (SN.toString().length() != 32) {
			throw new DataSupplementException();
		}
		return SN.toString();
	}

	/**
	 * 联盟协议数据补位使用
	 * 
	 * @param account
	 * @return
	 * @throws AccountException
	 */
	public static String DataSupplement(String account, Integer length) throws DataSupplementException {
		if (length == null) {
			throw new DataSupplementException();
		}
		if (account.length() == length) {
			return account;
		} else if (account.length() < length) {
			StringBuffer useraccount = new StringBuffer();
			for (int x = 0; x < length - account.length(); x++) {
				useraccount.append("0");
			}
			useraccount.append(account);
			return useraccount.toString();
		} else {
			throw new DataSupplementException();
		}
	}

	/**
	 * 支付宝订单号生成方法
	 * 
	 * @return
	 */
	public static String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * 类型异常
	 * 
	 * @author 蒋志林
	 *
	 */
	public static class TypeException extends Exception {
		public TypeException() {
		}
	}

	/**
	 * 桩号异常
	 * 
	 * @author 蒋志林
	 *
	 */
	public static class PileException extends Exception {
		public PileException() {
		}
	}

	/**
	 * 枪号异常
	 * 
	 * @author 蒋志林
	 *
	 */
	public static class GunException extends Exception {
		public GunException() {
		}
	}

	/**
	 * 数据补位异常
	 * 
	 * @author 蒋志林
	 *
	 */
	public static class DataSupplementException extends Exception {
		public DataSupplementException() {
		}
	}
}
