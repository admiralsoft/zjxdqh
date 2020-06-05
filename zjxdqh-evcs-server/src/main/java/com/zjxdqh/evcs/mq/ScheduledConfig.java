//package com.zjxdqh.evcs.mq;
//
//import com.zjxdqh.evcs.service.EvcsService;
//import com.zjxdqh.evcs.supervise.SuperviseUtils;
//import com.zjxdqh.evcs.supervise.vo.OperatorInfo;
//import com.zjxdqh.evcs.tools.JsonUtils;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Yorking
// * @date 2019/08/07
// */
////@Component
//@Log4j2
//public class ScheduledConfig {
//
//    @Autowired
//    private static EvcsService evcsService;
//
//    @Value("${evcs.scheduled.threads:8}")
//    private static Integer coreSize = 8;
//
//    private static LinkedList<MqObject> mqs = new LinkedList<>();
//
//    private static ScheduledExecutorService scheduleds = Executors.newScheduledThreadPool(coreSize);
//
//    static {
//
//        for (int i = 0; i < coreSize; i++) {
//
//            scheduleds.scheduleAtFixedRate(() -> {
//                int size = mqs.size();
//                int max = size / coreSize;
//                if (max == 0 && size < coreSize) {
//                    max = 1;
//                }
//                MqObject mq = null;
//                try {
//                    while ((max--) > 0 && (mq = getFirst()) != null) {
//                        String pileNum = mq.getDeviceNo();
//                        if (!StringUtils.isEmpty(pileNum)) {
//                            List<OperatorInfo> infos = evcsService.getSupersiveInfoByPilenum(pileNum);
//                            if (CollectionUtils.isEmpty(infos)) {
//                                continue;
//                            }
//                            SuperviseUtils.OperatorInfoList.set(infos);
//                        }
//
//                        switch (mq.getCode()) {
//                            case MqObject.CODE_ORDER_INFO:
//                                evcsService.pushOrderInfo(mq.getOrderNo());
//                                break;
//                            case MqObject.CODE_ORDER_STATE:
//                                evcsService.pushOrderStat(mq.getOrderNo());
//                                break;
//                            case MqObject.CODE_ORDER_STOP:
//                                evcsService.pushOrderStop(mq.getOrderNo());
//                                break;
//                            case MqObject.CODE_PILE_STATE:
//                                evcsService.pushPileState(mq.getDeviceNo(), mq.getGunNo());
//                                break;
//                            case MqObject.CODE_START_CHARGE_RESULT:
//                                evcsService.pushStartChargingResult(mq.getOrderNo());
//                                break;
//                            default:
//                                break;
//                        }
//                        log.info("消息:{} 操作完成, 剩余消息:{}", JsonUtils.toJsonString(mq), mqs.size());
//                        Thread.sleep(50);
//                    }
//
//                } catch (Exception e) {
//                    log.error("消息 消费异常:{}", JsonUtils.toJsonString(mq));
//                    log.error("消息 消费异常:{}", e);
//                }
//
//
//            }, 4, 2, TimeUnit.SECONDS);
//        }
//
//    }
//
//    public void addMqObj(MqObject mqObject) {
//        mqs.addLast(mqObject);
//    }
//
//    private static String lock = "";
//
//    private static MqObject getFirst() {
//
//        synchronized (lock) {
//
//            try {
//                return mqs.removeFirst();
//            } catch (Exception e) {
//                return null;
//
//            }
//        }
//
//    }
//
//}
