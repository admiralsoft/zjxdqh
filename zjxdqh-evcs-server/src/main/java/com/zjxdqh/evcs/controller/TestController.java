package com.zjxdqh.evcs.controller;

import com.alibaba.fastjson.JSONObject;
import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.service.TestServer;
import com.zjxdqh.evcs.supervise.vo.BaseResult;
import com.zjxdqh.evcs.supervise.vo.ResponseResult;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.vo.PileSite;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Yorking
 * @date 2019/05/06
 */
@RestController
public class TestController {

    @Resource
    private TestServer testServer;

    @Resource
    private EvcsService evcsService;

    @PostMapping("order")
    public BaseResult getTest(@RequestBody HashMap param){
        System.out.println(JsonUtils.toJsonString(param));
        return testServer.orderInfoTest();
    }

    @GetMapping("token")
    public String getToken(@RequestBody HashMap param){
        System.out.println(JsonUtils.toJsonString(param));
        BaseResult token = testServer.getToken();

        if (token != null) {
            return JSONObject.toJSONString(token);
        }
        return "error";
    }

    @GetMapping("testPushOrder")
    public String testPushOrder(){
        System.out.println("开始推送订单信息.....刘红......");
        evcsService.pushOrderInfo("A2019092616261828100000000000602");
        return "success";
    }


    @GetMapping("pile/{sid}")
    public ResponseResult getPile(@PathVariable Integer sid){
        PileSite token = testServer.getPileSite(sid);

        if (token != null) {
            return ResponseResult.success(token);
        }

        return ResponseResult.error(ResponseResult.RET_BUSY);
    }

    @PostMapping("notification_equip_charge_status")
    public void test(){
        evcsService.pushOrderStat("A2018112310392551180200100112012");
    }
}
