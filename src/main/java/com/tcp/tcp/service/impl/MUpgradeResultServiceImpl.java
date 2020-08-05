package com.tcp.tcp.service.impl;

import com.tcp.bean.MUpgradeResult;
import com.tcp.core.service.BaseService;
import com.tcp.mapper.MUpgradeResultMapper;

import com.tcp.tcp.base.TCPService;
import com.tcp.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Xu
 */
@Service("mUpgradeResultServiceImpl")
@Slf4j
public class MUpgradeResultServiceImpl extends BaseService<MUpgradeResult> implements TCPService {
    @Resource
    MUpgradeResultMapper mUpgradeResultMapper;
    @Override
    public void service(ChannelHandlerContext ctx, byte[] bytes, String pileNum) {
        MUpgradeResult info = mUpgradeResultParseImpl.getInfo(bytes);
        //入库
        info.setId(StringUtil.get32UUID());
        mUpgradeResultMapper.insert(info);
        //通知mq更新

    }
}
