package com.tcp.core.task;

import com.tcp.bean.TGun;
import com.tcp.bean.TPile;
import com.tcp.biz.pile.PileService;
import com.tcp.core.code.RedisCode;
import com.tcp.tcp.fo.PileAccessTime;
import com.tcp.tcp.storage.TCPMap;
import com.tcp.util.DateUtil;
import com.tcp.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class SignStatusTask {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PileService pileService;

    private boolean firstRun = true;

    public static final org.slf4j.Logger logremind = org.slf4j.LoggerFactory.getLogger("com.tcp.off.line.remind");


    /**
     * 桩体通讯过期时间，默认3分钟。（单位：秒）
     */
    private long expireTime = 60 * 3;//默认3分钟

    @Scheduled(cron = "0 */2 * * * ?")
    public void offlineTask(){
        if (firstRun) {
            firstRun = false;
            return;
        }
        long expire = System.currentTimeMillis() - expireTime*1000; // 通讯过期时间
        log.debug("清理 长时间未通讯的 离线桩， 定时任务启动");
        //logremind.debug("场站[{}]，桩号[{}]离线，时间[{}]", "机场航站楼", "2610000000000192", DateUtil.getNow());
        //logremind.debug("场站[{}]，桩号[{}]离线，时间[{}]", "机场航站楼", "2610000000000009", DateUtil.getNow());

        for (String pileNum : TCPMap.connMap.keySet()) {
            PileAccessTime accessTime = (PileAccessTime) redisUtil.get(RedisCode.PILE_LAST, pileNum);
            if (accessTime != null && accessTime.getLastAccess() < expire) {// 桩体通讯已经超时
                log.info("桩体{}， 超过{}秒未通讯，执行下线操作", accessTime.getPileNum(), expireTime);
                /* 关闭通道 */
                TCPMap.connMap.get(pileNum).close();
                /* 删除通道 */
                TCPMap.connMap.remove(pileNum);
                /* 删除TCP连接缓存 */
                redisUtil.remove(RedisCode.REDIS_TCP, pileNum);
                /* 修改数据库状态为离线状态 */
                TPile pile = new TPile();
                pile.setPileNum(pileNum);
                pile.setPileSigninState(TPile.SIGN_OFFLINE);
                pileService.updatePile4OnOff(pile);
                /*删除缓存*/
//			redisUtil.remove(RedisCode.CHANNEL,address);
                TGun tGun = new TGun();
                tGun.setPileNum(pileNum);
                tGun.setGunAdminState(3);
                pileService.updateGunAdminState(tGun);
            }
        }
        log.debug("清理 长时间未通讯的 离线桩， 定时任务结束");
    }

}
