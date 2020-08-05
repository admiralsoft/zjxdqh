package com.tcp.business.service;

import com.tcp.TCPApplication;
import com.tcp.bean.LogCardVerification;
import com.tcp.bean.TPileStatusInfo;
import com.tcp.mapper.LogCardVerificationMapper;
import com.tcp.mapper.TPileStatusInfoMapper;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.convert.parse.impl.SignCardRequestParseImpl;
import com.tcp.tcp.vo.receive.vo.SignCardRequestVo;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author yaweiXu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TCPApplication.class)
public class TarServiceImplTest {

    @Resource
    LogCardVerificationMapper logCardVerificationMapper;

    @Resource
    private TPileStatusInfoMapper pileStatusInfoMapper;

    @Resource
    private TCPParseService<SignCardRequestVo> signCardRequestParseImpl;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testSetDao() throws Exception {
        //TODO: Test goes here...
    }

    @Test
    public void testInsert() {
        LogCardVerification logCardVerification = new LogCardVerification();
        logCardVerification.setId(StringUtil.get32UUID());
        logCardVerificationMapper.insert(logCardVerification);
        System.out.println(JsonUtils.toJson(logCardVerification));
    }

    @Test
    public void testPileStatus() {
        TPileStatusInfo statusInfo = pileStatusInfoMapper.selectLastOnlineTime("2810000000000061");
        System.out.println(JsonUtils.toJson(statusInfo));
    }

}
