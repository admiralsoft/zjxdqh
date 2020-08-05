
package com.tcp.tcp.vo.send.vo;

/**
 * 注册命令回复
 */
public class SendSigninVo {

	private int	result;	// 注册结果

	private int	code;	// 失败原因

	public int getResult() {

		return result;
	}

	public void setResult(int result) {

		this.result = result;
	}

	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}

}
