package com.zjxdqh.evcs.supervise.aop;

import com.zjxdqh.evcs.supervise.SuperviseUtils;
import com.zjxdqh.evcs.supervise.annon.CectRequest;
import com.zjxdqh.evcs.supervise.vo.*;
import com.zjxdqh.evcs.tools.JsonUtils;
import com.zjxdqh.face.param.ChargeStateParam;
import com.zjxdqh.face.service.HappyService;
import com.zjxdqh.face.vo.ChargeStateBaseVo;
import feign.Request;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Yorking
 * @date 2019/08/15
 */
@Aspect
@Component
@Log4j2
public class MultipleSuperviseServiceAop {


    @Autowired
    @Qualifier("pushTaskExecutor")
    private Executor pushTaskExecutor;

    @Autowired
    private HappyService happyService;


    @Pointcut("@annotation(com.zjxdqh.evcs.supervise.annon.CectRequest)")
    public void multiple() {
    }


    @Around("multiple()")
    public Object multipleOperator(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            Object[] args = joinPoint.getArgs();
            List<OperatorInfo> ops = SuperviseUtils.OperatorInfoList.get();

            if (CollectionUtils.isEmpty(ops)) {
                OperatorInfo op = SuperviseUtils.OperatorInfo.get();
                if (op != null) {
                    ops = Collections.singletonList(op);
                }
            }
            if (CollectionUtils.isEmpty(ops)) {
                return execute(joinPoint);
            }

            MethodSignature sig = (MethodSignature) joinPoint.getSignature();
//            Method method=sig.getMethod();
//            Class<?> targetClazz = joinPoint.getTarget().getClass();
//            method = targetClazz.getDeclaredMethod(sig.getName(), method.getParameterTypes());
//            CectRequest cect = method.getDeclaredAnnotation(CectRequest.class);
            CectRequest cect=sig.getMethod().getDeclaredAnnotation(CectRequest.class);

            if (cect == null) {
                return execute(joinPoint);
            }
            String orderNo = null;
            if (args != null && args.length > 0
                    && args[0] instanceof BaseChargeSeqParam) {
                BaseChargeSeqParam param = (BaseChargeSeqParam) args[0];
                orderNo = param.getStartChargeSeq()+"";
            }
            for (OperatorInfo op : ops) {
                if (args != null && args.length > 0) {
                    if (!StringUtils.isEmpty(orderNo) && args[0] instanceof BaseChargeSeqParam) {
                        BaseChargeSeqParam param = (BaseChargeSeqParam) args[0];
                        param.setStartChargeSeq(SuperviseUtils.convert2StartChargeSeq(op.getOrgId(), orderNo));
                    }
                    if (args[0] instanceof SubsidyParam) {
                        SubsidyParam param = (SubsidyParam) args[0];
                        param.setOperatorID(op.getOrgId());
                    }else if (args[0] instanceof StationChangeParam) {
                        StationChangeParam param = (StationChangeParam) args[0];
                        param.setOperatorId(op.getOrgId());
                    }
                }

                // 判断 推送接口 是否 排除具体 对接方
                if (cect.excludeSupervise().length > 0
                        && Arrays.asList(cect.excludeSupervise()).contains(op.getOperatorId())) {
                    continue;
                }
                if (cect.includeSupervise().length > 0
                        && !Arrays.asList(cect.includeSupervise()).contains(op.getOperatorId())) {
                    continue;
                }


                if (!cect.supervise() && op.getRegulatory() == OperatorInfo.IS_SUPERVISE) {
                    // 是否推送给监管平台
                    continue;
                }

                String sn = null;

                // 第三方对接充电平台
                if (op.getRegulatory() == OperatorInfo.IS_THIRD_STAGE) {
                    if (!cect.dock()) {
                        // 不推送
                        continue;
                    }
                    if (!StringUtils.isEmpty(orderNo)) {
                        // 推送接口是否只是相关接口 创建订单的信息
                        ChargeStateParam seq = new ChargeStateParam();
                        seq.setStartChargeSeq(orderNo);
                        ChargeStateBaseVo baseOrder = happyService.queryChargeStateBaseVo(seq);
                        if (baseOrder == null || baseOrder.getSuperviseId() == null
                                || op.getSupersiveId().intValue() != baseOrder.getSuperviseId()) {
                            continue;
                        } else {
                            sn = baseOrder.getSuperviseSn();
                        }
                    }
                }

                String thirdOrderNo = sn;

                ProceedingJoinPoint jp = joinPoint;
                pushTaskExecutor.execute(()->{
                    SuperviseUtils.OperatorInfo.set(op);
                    if (!StringUtils.isEmpty(thirdOrderNo)) {
                        SuperviseUtils.OperatorInfo.get().setSn(thirdOrderNo);
                    }
                    execute(jp);
                });
            }
        } catch (Throwable throwable) {
            log.error("推送结果, 发生异常. " ,throwable);
        }
        return obj;

    }

    private Object execute(ProceedingJoinPoint proceedingJoinPoint) {
        Object res = null;
        try {
            res = proceedingJoinPoint.proceed();
            log.info("推送结果 : {}", JsonUtils.toJsonString(res));
        } catch (Throwable throwable) {
            log.error("推送结果, 发生异常. " ,throwable);
        }
//        ResponseResult<?> responseResult = (ResponseResult<?>) res;
//        if (responseResult != null &&  responseResult.getRet() == ResponseResult.RET_TOKEN_ERROR) {
//            throw new RetryableException("token 过期", Request.HttpMethod.GET, null);
//        }
        return res;
    }

}
