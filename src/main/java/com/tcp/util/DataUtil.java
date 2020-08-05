
package com.tcp.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

/**
 * 数据工具类
 */
@Slf4j
public class DataUtil {

	protected static Logger logger = LoggerFactory.getLogger(DataUtil.class);

	public static byte[] number2byte(Number value, int len) throws Exception {
		switch (len) {
			case  8:
				return longToEightBytes((Long) value);
			case 4:
				return intToFourBytes((Integer) value);
			case 2:
				return intToBytes((Integer) value);
			case 1:
				byte[] src = new byte[1];
				src[0] = (byte) (((Integer) value) & 0xFF);
				return src;
			default:
				throw new Exception("number2byte数据转换失败：" + value + " : " + len);
		}
	}


	public static Number byte2Number(byte[] bytes, int len) throws Exception {
		switch (len) {
			case 8:
				return eightByteToLong(bytes);
			case 4:
				return fourBytesToInt(bytes);
			case 2:
				return twoBytesToInt(bytes);
			case 1:
				return (int) bytes[0];
			default:
				throw new Exception("byte2Number数据转换失败：" + bytes + " : " + len);
		}
	}

	/**
	 * int 转为两个字节的字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] intToBytes(int value) {

		byte[] src = new byte[2];
		src[0] = (byte) ((value >> 8) & 0xFF);
		src[1] = (byte) ((value >> 0) & 0xFF);
		return src;
	}

	/**
	 * Long转四个字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] longToFourBytes(Long value) {

		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * short 转byte
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] shortToTwoBytes(short value) {

		byte[] src = new byte[2];
		src[0] = (byte) ((value >> 8) & 0xFF);
		src[1] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * Integer转四个字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] intToFourBytes(Integer value) {

		byte[] src = new byte[4];
		src[0] = (byte) ((value >> 24) & 0xFF);
		src[1] = (byte) ((value >> 16) & 0xFF);
		src[2] = (byte) ((value >> 8) & 0xFF);
		src[3] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * long转八个字节
	 * 
	 * @param
	 * @return
	 */
	public static byte[] longToEightBytes(long value) {

		byte[] b = new byte[8];
		b[0] = (byte) ((value >> 56) & 0xFF);
		b[1] = (byte) ((value >> 48) & 0xFF);
		b[2] = (byte) ((value >> 40) & 0xFF);
		b[3] = (byte) ((value >> 32) & 0xFF);
		b[4] = (byte) ((value >> 24) & 0xFF);
		b[5] = (byte) ((value >> 16) & 0xFF);
		b[6] = (byte) ((value >> 8) & 0xFF);
		b[7] = (byte) (value & 0xFF);
		return b;
	}

	/**
	 * 获取效验码
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] getCheckCode(byte[] bytes) {

		long sum = 0;
		for (int i = 0; i < bytes.length - 4; i++) {
			sum = sum + (bytes[i] & 0xFF);
		}
		return intToBytes((int) (sum % 0xffff));
	}

	/**
	 * 校验
	 * 
	 * @param bytes
	 * @return
	 */
	public static boolean check(byte[] bytes) {

		if(bytes==null)
		{
			logger.error("校验bytes为空!");
			return false;
		}

		/* 校验包头 0x0601*/
		if (bytes[0] != 0x06 && bytes[1] != 0x01) { return false; }
		/*数据包长度*/
		int l = bytes.length;
		/* 校验包尾巴 0x0f02*/
		if (bytes[l - 2] != 0x0F || bytes[l - 1] != 0x02) {
			OutUtil.println("包尾错误:" + DataUtil.printHexByte(bytes));
			return false;
		}
		/*数据长度 == 包头,数据长度,密文,和包尾*/
		int length2 = twoBytesToInt(new byte[]{bytes[2], bytes[3]});
		if (l != length2) {
			OutUtil.println("获取到的实际长度为：" + l + "读取的长度为" + length2 + ":" + DataUtil.printHexByte(bytes));
			return false;
		}
		/*校验码*/
		byte[] j = new byte[]{bytes[l - 4], bytes[l - 3]};
		long sum = 0;
		for (int i = 0; i < bytes.length - 4; i++) {
			sum = sum + (bytes[i] & 0xFF);
		}
		/*除包尾外所有数据和除 以0x10000的余数
		例如 和为0x123456则 校验码为0x3456*/
		if (DataUtil.twoBytesToInt(j) != (sum % 0xFFFF)) {
			OutUtil.println("MY：" + (sum % 0xFFFF) + "----YOUR:" + DataUtil.twoBytesToInt(j));
			OutUtil.println("校验不通过" + DataUtil.printHexByte(bytes));
			return false;
		}
		return true;
	}

	/**
	 * 获取数据段长度
	 * 
	 * @return
	 */
	public static int getLength(byte[] bytes) {

		return bytes.length;
	}

	/**
	 * 获取当前系统时间/S（4个字节）
	 * 
	 * @return
	 */
	public static byte[] getTime() {

		long gettime = new Date().getTime() / 1000;
		byte[] time = DataUtil.longToFourBytes((Long) gettime);
		return time;
	}

	public static byte[] testTime() {
		Date parse = DateUtil.parse("2018-09-25 23:56:00");

		long gettime = parse.getTime() / 1000;
		byte[] time = DataUtil.longToFourBytes((Long) gettime);
		return time;
	}

	/**
	 * 根据时间获取字节
	 * 
	 * @param time
	 * @return
	 */
	public static byte[] getTime(long time) {

		long gettime = time / 1000;
		return DataUtil.longToFourBytes((Long) gettime);
	}

	/**
	 * 获取数字的ASCII码并存入字节数组中
	 *
	 * @param
	 *
	 * @param length
	 *            输出的ASCII长度
	 * @return 存储ASCII码的字节数组
	 */
	public static byte[] StringToAsciiByte(String value, int length) {

		char[] chars = value.toCharArray();
		byte[] bytes = new byte[length];
		if (value.length() >= length) {
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) chars[i];
			}
		} else {
			int c = length - value.length();
			for (int i = 0; i < c; i++) {
				bytes[i] = 0x30;
			}
			for (int i = c; i < length; i++) {
				bytes[i] = (byte) chars[i - c];
			}
		}
		return bytes;
	}

	/**
	 * 将4个字节转换为int
	 * 
	 * @return
	 */
	public static int fourBytesToInt(byte[] bytes) {

		return (int) ((bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF) << 0);
	}

	/**
	 * 将4个字节转换为String
	 * 
	 * @return
	 */
	public static String fourBytesToString(byte[] bytes) {

		return ((bytes[0] & 0xFF) << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF) << 0) + "";
	}

	/**
	 * 将8个字节转换为Long
	 * 
	 * @param
	 * @return
	 */
	public static Long eightByteToLong(byte[] b) {

		long l = ((long) b[0] << 56) & 0xFF00000000000000L; // 如果不强制转换为long，那么默认会当作int，导致最高32位丢失
		l |= ((long) b[1] << 48) & 0xFF000000000000L;
		l |= ((long) b[2] << 40) & 0xFF0000000000L;
		l |= ((long) b[3] << 32) & 0xFF00000000L;
		l |= ((long) b[4] << 24) & 0xFF000000L;
		l |= ((long) b[5] << 16) & 0xFF0000L;
		l |= ((long) b[6] << 8) & 0xFF00L;
		l |= (long) b[7] & 0xFFL;
		return l;
	}

	/**
	 * 将4个字节转成Long （如果不强制转换为long，那么默认会当作int，导致最高32位丢失）
	 * 
	 * @param b
	 * @return
	 */
	public static Long fourByteToLong(byte[] b) {

		long l = ((long) b[0] << 24) & 0xFF000000L;
		l |= ((long) b[1] << 16) & 0xFF0000L;
		l |= ((long) b[2] << 8) & 0xFF00L;
		l |= (long) b[3] & 0xFFL;
		return l;
	}

	/**
	 * 将2个字节转换为int
	 * 
	 * @param bytes
	 * @return
	 */
	public static int twoBytesToInt(byte[] bytes) {

		return (int) ((bytes[0] & 0xFF) << 8 | (bytes[1] & 0xFF) << 0);
	}

	/**
	 * 将1个字节转换为int
	 * 
	 * @param bytes
	 * @return
	 */
	public static int oneBytesToInt(byte bytes) {

		return (int) (bytes & 0xFF);
	}

	/**
	 * ascii转换为String
	 * 
	 * @param
	 * @return
	 */
	public static String byteAsciiToString(Byte b) {

		return (char) Integer.parseInt(b.toString()) + "";
	}

	/**
	 * ascii转String
	 * 
	 * @param b
	 * @return
	 */
	public static String byteAsciiToString(byte[] b) {

		try {
			return new String(b, "ascii");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 打印16进制
	 * 
	 * @param data
	 */
	public static String printHexByte(byte[] data) {

		StringBuffer str = new StringBuffer();
		for (byte num : data) {
			str.append(Integer.toHexString(num & 0xff) + " ");
		}
		return str.toString();
	}

	/**
	 * @功能: BCD码转为10进制串(阿拉伯数据)
	 * @参数: BCD码
	 * @结果: 10进制串
	 */
	public static String bcdToString(byte[] bytes) {

		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	/**
	 * String 转BCD
	 * 
	 * @param asc
	 * @return
	 */
	public static byte[] stingTOBCD(String asc) {

		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * 进行解密
	 * 
	 * @param value
	 * @param key
	 * @return
	 */
	public static byte[] decode(byte[] value, byte[] key) {

		try {
			Integer length = DataUtil.twoBytesToInt(new byte[]{value[2], value[3]});// 数据段长度
			byte[] ciphertext = new byte[length - 28];// 密文段
			Integer fill = length - 28;// 密文长度
			System.arraycopy(value, 24, ciphertext, 0, fill);// 获取密文
			byte[] plaintext = SMS4.decodeSMS4(ciphertext, key);// 明文
			System.arraycopy(plaintext, 0, value, 24, fill);
			return value;
		} catch (Exception e) {
			OutUtil.println("解密失败>>>>>>>>>>>>>>>>>>>>>>>");
			return null;
		}
	}

	/**
	 * double转12个字节
	 * @param d
	 * @return
	 */
	public static byte[] doubleToBytes(double d) {
		long value = Double.doubleToRawLongBits(d);
		byte[] byteRet = new byte[12];
		for (int i = 0; i < 12; i++) {
			byteRet[i] = (byte) ((value >> 12 * i) & 0xff);
		}
		return byteRet;
	}

	/**
	 *  12字节转double
	 * @param arr
	 * @return
	 */
	public static double bytesToDouble(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 12; i++) {
			value |= ((long) (arr[i] & 0xff)) << (12 * i);
		}
		return Double.longBitsToDouble(value);
	}


	public static boolean isNumber(String input) {

		String regex = "^\\d+$";
		return (input.replace(",", "")).matches(regex);

	}

	public static byte stringToHexByte(String str){

		int devIdInt = Integer.parseInt(str);
		String devIdString = Integer.toHexString(devIdInt);
		return Byte.parseByte(devIdString, 16);
	}

	public static byte intToHexByte(int devInt){
		String devIdString = Integer.toHexString(devInt);
		return Byte.parseByte(devIdString, 16);
	}


	/**
	 * 故障,告警,保护码解析
	 */
	public static StringBuffer parseCode(byte[] code) {
		StringBuffer builder = new StringBuffer();
		for (int j = 0; j < code.length; j++) {
			byte codeIndex = code[j];
			if (codeIndex > 0 && j != 0) { //第一个字节不解析
				/*获取当前坐标位数,故障码字节*/
				int index = j + 1;
				/*字节转二进制,获取故障码*/
				String binaryStr = Integer.toBinaryString((codeIndex & 0xFF) + 0x100).substring(1);
				/*倒序获取故障码位置*/
				binaryStr = new StringBuffer(binaryStr).reverse().toString();
				char[] array = binaryStr.toCharArray();
				for (int k = 0; k < array.length; k++) {
					int value = Integer.parseInt(String.valueOf(array[k]));
					if (value > 0) {
						int codeMark = k + 1;
						/*拼接完整解析后的故障码*/
						String errorCode = index + "-" + codeMark;
						if (k < array.length - 1) {
							builder.append(errorCode).append(",");
						} else {
							builder.append(errorCode);
						}
					}
				}
			}
		}
		return builder;
	}



	public static int DownLoadPages(String urlsrc,String fileName){
		//文件输出位置
//		String outpath="/Users/mac/Documents/log/"+fileName;
		String outpath="/opt/mms_collect/"+fileName;
		// 输入流
		InputStream in;
		// 文件输出流
		FileOutputStream out;
		try{
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams,10000); //设置连接超时为10秒
			HttpClient client = new DefaultHttpClient(httpParams); // 生成一个http客户端发送请求对象
			HttpGet httpget1 = new HttpGet(urlsrc); //对查询页面get
			HttpResponse httpResponse1 = client.execute(httpget1); // 发送请求并等待响应
			// 判断网络连接是否成功
			log.info("状态码："+httpResponse1.getStatusLine().getStatusCode());
			if (httpResponse1.getStatusLine().getStatusCode() != 200)
				log.info("网络错误异常！!!!");
			else
				log.info("网络连接成功!!!");
			httpget1.abort(); //关闭get
			HttpGet httpget2 = new HttpGet(urlsrc); //对下载链接get实现下载
			HttpResponse httpResponse2 = client.execute(httpget2);
			HttpEntity entity = httpResponse2.getEntity(); // 获取响应里面的内容
			in = entity.getContent(); // 得到服务气端发回的响应的内容（都在一个流里面）
			File file = new File(outpath);
			File parentFile = file.getParentFile();
			if (!parentFile.exists()){
				parentFile.mkdirs();
			}
			file.createNewFile();
			out = new FileOutputStream(file);
			byte[] b = new byte[1048576];//一次一M
			int len = 0;
			while((len=in.read(b))!= -1){
				out.write(b,0,len);
			}
			in.close();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			log.error("下载异常:",e);
			return 0;
		}
		log.info("download, success!!");
		return 1;
	}


	//需要使用2字节表示b
	public static String numToHex16(int b) {
		return String.format("%04x", b);
	}

	public static void main(String[] args) throws Exception {
		byte[] bs = stingTOBCD("12az11");
		System.out.println(printHexByte(bs) + " : " + bcdToString(bs));

		byte[] bytes = number2byte(208012, 4);
		System.out.println(printHexByte(bytes) + ":  " + byte2Number(bytes,4));


	}

	public static String cmdTohexString(Integer cmd){
		String hexString = Integer.toHexString(cmd);
		StringBuffer buffer = new StringBuffer();
		buffer.append(hexString);
		while (buffer.length() < 4) {
			buffer.insert(0,"0");
		}
		return buffer.toString();
	}
}
