package com.tcp.tcp.service.impl;


import com.tcp.bean.BOfflineChildinfo;
import com.tcp.bean.BOfflineinfo;
import com.tcp.core.code.MQCode;
import com.tcp.core.code.TCPCode;
import com.tcp.mapper.BOfflineChildinfoMapper;
import com.tcp.mapper.BOfflineinfoMapper;
import com.tcp.mq.base.RabbitMqSender;
import com.tcp.tcp.vo.receive.vo.OfflineInfoVo;
import com.tcp.tcp.vo.send.SendOfflineInfo;
import com.tcp.tcp.vo.send.vo.SendOfflineInfoVo;
import com.tcp.core.service.BaseService;
import com.tcp.tcp.base.TCPService;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 上午11:25.
 * @Title:
 * @Description: 发送离线充电数据
 */
@Service("offlineInfoServiceImpl")
@Slf4j
public class OfflineInfoServiceImpl extends BaseService<Object> implements TCPService {

    @Resource
    private RabbitMqSender rabbitMqSender;

    @Resource
    private BOfflineinfoMapper bOfflineinfoMapper;

    @Resource
    private BOfflineChildinfoMapper bOfflineChildinfoMapper;


    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {

        SendOfflineInfoVo infoVo = new SendOfflineInfoVo();

        List<Map<String, Object>> info = offlineInfoParseImpl.getInfo(bytes);

        try{
            Integer num =(Integer) info.get(0).get("num");
            infoVo = dataHanding(info,infoVo,num);
            /*回复桩体0010*/
            sendMessage(ctx,offlineInfoResultCommandImpl.getByteInfo(infoVo,pileNum, TCPCode.offline_info),pileNum, TCPCode.offline_info);

            /*获取数据,发送通知运营平台*/
            List<String> ids = new ArrayList<>();
            for (Map<String, Object> map : info) {
                String id =(String) map.get("id");
                //增加判断防止null 数据扫描表造成宽带打满
                if (id != null&&!"".equals(id)){
                    ids.add(id);
                }
            }
            /*数据完整*/
            if (num.equals(ids.size())){
                /*所有父表*/
                List<BOfflineinfo> bOfflineinfos = bOfflineinfoMapper.selectOfflineDataByIds(ids);
                /*所有子表*/
                List<BOfflineChildinfo> bOfflineChildinfos = bOfflineChildinfoMapper.selectOfflineDataByParentIds(ids);

                Map<String, OfflineInfoVo> map = new HashMap<>();
                for (BOfflineinfo bOfflineinfo : bOfflineinfos) {
                    OfflineInfoVo offlineInfoVo = new OfflineInfoVo();
                    BeanUtils.copyProperties(bOfflineinfo,offlineInfoVo);
                    map.put(bOfflineinfo.getId(),offlineInfoVo);
                }
                LinkedList<BOfflineChildinfo> childinfos;
                for (BOfflineChildinfo childinfo : bOfflineChildinfos) {
                    String parentId = childinfo.getParentId();
                    OfflineInfoVo offlineInfoVo = map.get(parentId);
                    if (offlineInfoVo.getOfflineInfos() == null){
                        childinfos = new LinkedList<>();
                        childinfos.add(childinfo);
                        offlineInfoVo.setOfflineInfos(childinfos);
                    }else {
                        offlineInfoVo.getOfflineInfos().add(childinfo);
                    }
                }
                /*整理数据并发送通知*/
                List<OfflineInfoVo> infoVos = new ArrayList<>();
                for (Map.Entry<String, OfflineInfoVo> entry : map.entrySet()) {
                    OfflineInfoVo vo = entry.getValue();
                    // 如果 订单号编码中枪号与实际上传枪号不一致， 则不发MQ至运营
                    if (!StringUtil.isEmpty(vo.getOrderNo()) && vo.getOrderNo().substring(vo.getOrderNo().length() - 1).equalsIgnoreCase(String.valueOf(vo.getGunNum()))) {
                        infoVos.add(vo);
                    } else {
                        logger.error("订单[{}]离线数据，存在订单号中枪号与实际枪号不一致的问题",vo.getOrderNo());
                    }
                }
                /*通知130*/
                if (!CollectionUtils.isEmpty(infoVos)) {
                    rabbitMqSender.sendRabbitmqCollectDirect(getResultObj(pileNum,"离线数据", MQCode.OFFLINE_INFO,true,infoVos));
                }

            }else {
                log.info("桩号: " + pileNum + "数据缺失,等待桩体回复");
            }


        }catch (Exception e){
            log.error(pileNum+":离线充电数据异常:" , e);
        }
    }



    private SendOfflineInfoVo dataHanding(List<Map<String, Object>> info,SendOfflineInfoVo infoVo,Integer num){
        infoVo.setNum(num);
        LinkedList<SendOfflineInfo> offlineInfos = new LinkedList<>();
        SendOfflineInfo offlineInfo;
        for (Map<String, Object> map : info) {
            offlineInfo = new SendOfflineInfo();
            offlineInfo.setSerialNum((Integer) map.get("SerialNum"));
            offlineInfo.setReusltCode((Integer)map.get("result"));
            offlineInfos.add(offlineInfo);
        }

        infoVo.setSendOfflineInfos(offlineInfos);
        return infoVo;
    }
}
