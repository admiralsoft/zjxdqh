package com.zjxdqh.evcs.service;

import com.zjxdqh.evcs.supervise.service.SuperviseService;
import com.zjxdqh.evcs.supervise.vo.ChargeOrderInfoParam;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.supervise.vo.TokenRequestParam;
import com.zjxdqh.face.service.HappyService;
import com.zjxdqh.face.service.TestService;
import com.zjxdqh.face.vo.PileSite;
import com.zjxdqh.face.vo.TestVo;
import com.zjxdqh.tools.redis.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@Service
public class TestServer {

    @Autowired
    private TestService testService;
    @Autowired
    private HappyService happyService;

    @Autowired
    private SuperviseService superviseService;


    public TestVo getTest() {
        TestVo test = testService.getTest();
        test.setName("evcs.name.." + RedisTools.get("aaa"));
        return test;
    }


    public PileSite getPileSite(Integer sid) {
        PileSite test = happyService.getPileSite(sid);
        return test;
    }

    public ResponseResult orderInfoTest() {
        ChargeOrderInfoParam orderInfoParam = new ChargeOrderInfoParam();
        orderInfoParam.setConnectorID("11111");
        return superviseService.notificationChargeOrderInfo(orderInfoParam);
    }

    public ResponseResult getToken() {
        TokenRequestParam tokenReqParam = new TokenRequestParam();
        tokenReqParam.setOperatorID("91510100MA6DE1HK00");
        tokenReqParam.setOperatorSecret("4hrC6kkEOhJYQuNy");
        return superviseService.getToken(tokenReqParam);
    }


}
