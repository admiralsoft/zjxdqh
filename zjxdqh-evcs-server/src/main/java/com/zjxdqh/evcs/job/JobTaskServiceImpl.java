package com.zjxdqh.evcs.job;

import com.zjxdqh.evcs.service.EvcsService;
import com.zjxdqh.evcs.supervise.SuperviseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * <p>
 * mq指令定时任务
 * </p>
 *
 * @author chenshunhua
 * @date 2019-01-10
 */
@Component
@EnableScheduling
public class JobTaskServiceImpl implements MqTaskService {

    /**
     * 监管平台id
     */
    @Value("${supervise.supersiveId}")
    public String supersiveId;

    @Autowired
    EvcsService evcsService;

    private static final Logger log = LoggerFactory.getLogger(JobTaskServiceImpl.class);

    /**
     * cron表达式生成器:
     * http://cron.qqe2.com/
     *
     * @return
     * @throws Exception
     */
    @Override
    @Scheduled(cron = "${batchTimeProofread.job.cron}")
//    @Scheduled(fixedDelay = 10000)
    public boolean batchTimeProofread() throws Exception {
        SuperviseUtils.OperatorInfoList.set(Collections.singletonList(evcsService.getSupersiveInfoById(supersiveId)));
        log.info("监管平台补贴定时任务、开始----------");
        evcsService.notificationRecordsAmountInfo();
        log.info("监管平台补贴定时任务、结束----------");
        return true;
    }

    @Override
    @Scheduled(cron = "${Timing.PushPileStatus.cron}")
    public boolean pushPileStatus() throws Exception {
        SuperviseUtils.OperatorInfoList.set(Collections.singletonList(evcsService.getSupersiveInfoById(supersiveId)));
        evcsService.timerPushPileState();
        return true;
    }
}

