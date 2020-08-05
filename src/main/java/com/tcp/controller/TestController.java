
package com.tcp.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.tcp.bean.BChargingInfo;
import com.tcp.bean.TPileStatusInfo;
import com.tcp.mapper.BChargingInfoMapper;
import com.tcp.mapper.TGunMapper;
import com.tcp.mapper.TPileStatusInfoMapper;
import com.tcp.util.JsonUtils;
import com.tcp.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	private TGunMapper gunMapper;

	@Autowired
	private BChargingInfoMapper chargingInfoMapper;

	@Autowired
	private TPileStatusInfoMapper pileStatusInfoMapper;

	@Autowired
	private RedisTemplate byteRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;


	@Autowired
	private RedisUtil redisUtil;

	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> test() {

		return testService.test();
	}

	@GetMapping("/test2")
	@ResponseBody
	public String test2() {
		System.out.println("aaaa");
		List<BChargingInfo> guns = chargingInfoMapper.selectAll();
		BChargingInfo gun = chargingInfoMapper.getOne(Collections.singletonMap("gunId", (Object) "1"));
		System.out.println("size :  " + guns.size());

		System.out.println();
		try {
			System.out.println(gun.getId());
			System.out.println(guns.get(0).getClass());
			BChargingInfo tGun = guns.get(0);
			System.out.println(tGun.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "1";
	}

	@RequestMapping("/test4")
	public String test4(Model model) {

		return "index";
	}

	@RequestMapping("/selectPileStatus")
	@ResponseBody
	public String test4(@RequestParam("pileNum") String pileNum) {
		TPileStatusInfo statusInfo = pileStatusInfoMapper.selectLastOnlineTime(pileNum);
		System.out.println("pileNum:" + pileNum + "：　　statusInfo：" + JsonUtils.toJson(statusInfo));
		return JsonUtils.toJson(statusInfo);
	}
//	@RequestMapping("/test5")
//	public String test5(){
//		test tt = new test();
//		tt.setId("add");
//		rabbitMqSender.sendRabbitmqCollectDirect(tt);
//		return "";
//	}


	/**
	 * 启动充电
	 * 
	 * @param pileNum
	 * @param gunNum
	 * @param account
	 * @param
	 * @return
	 */
	@RequestMapping("/start")
	@ResponseBody
	public Map<String, Object> start(@ModelAttribute("pileNum") String pileNum,
			@ModelAttribute("gunNum") Integer gunNum, @ModelAttribute("cmd") int cmd, @ModelAttribute("account") String account,
			@ModelAttribute("prepayment") Integer prepayment, @ModelAttribute("supportPower") int supportPower) {

		return testService.start(pileNum, gunNum,cmd, account, prepayment, supportPower);
	}

	/**
	 * 设置心跳
	 * 
	 * @param pileNum
	 * 	 * @param repottime
	 * @param overtime
	 * @return
	 */
	@RequestMapping("/setHeart")
	@ResponseBody
	public Map<String, Object> setHeart(@ModelAttribute("pileNum") String pileNum,
			@ModelAttribute("reporttime") Integer reporttime, @ModelAttribute("overtime") Integer overtime) {
		return testService.setHeart(pileNum, reporttime, overtime);
	}


	@RequestMapping("/testDelay")
	public Object testDelay(String name) {
		System.out.println("BBBBBBBBBB");
//		redisUtil.setValueExpire("TTTT:", "TTT:c", "33");
		redisTemplate.opsForValue().set("TTTT:TTT:b", "BBBB", 3, TimeUnit.MINUTES);

		byteRedisTemplate.opsForValue().set("TTTT:TTT:a", "hello 222".getBytes(), 3, TimeUnit.MINUTES);


		byte[] o = (byte[]) byteRedisTemplate.opsForValue().get("TTTT:TTT:a");
		System.out.println("CCCCC: "+ new String(o));

		System.out.println("DDDDD: "+ redisTemplate.opsForValue().get("TTTT:TTT:b"));
		return "ok";
	}

}
