package com.tcp.tcp.service;

import java.util.Map;

public interface TestService {

	public Map<String, Object> test();

	public Map<String, Object> start(String pileNum, Integer gunNum,int cmd, String account, Integer yufufei, int supporPower);

	public Map<String, Object> setHeart(String pileNum, Integer repottime, Integer overtime);

	void test1();
}
