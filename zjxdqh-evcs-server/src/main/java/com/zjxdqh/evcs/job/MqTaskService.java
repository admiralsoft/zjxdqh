package com.zjxdqh.evcs.job;

/**
 * <p>
 * mq指令定时任务
 * </p>
 *
 * @author chenshunhua
 * @date 2019-01-10
 */
public interface MqTaskService {


        /**
         *
         * 批量时间校时发送指令
         * @return
         */
        boolean batchTimeProofread() throws Exception;

        /**
         * 定时推送设备状态
         * @return
         * @throws Exception
         */
        boolean pushPileStatus() throws Exception;

}
