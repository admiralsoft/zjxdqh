package com.tcp.tcp.convert.command.impl;

import com.tcp.bean.JsonObject;
import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/14.
 * Time: 下午7:44.
 * @Title:
 * @Description: 0x0117 运营平台查询软件/固件更新是否成功命令
 */
@Service("activeUpdateResultCommandImpl")
@Slf4j
public class ActiveUpdateResultCommandImpl extends BaseCommand implements CommandService<JsonObject> {
    @Override
    public byte[] getByteInfo(JsonObject jsonObject, String pileNum, short cmd) {
        log.info("0x0117 运营平台查询软件/固件更新是否成功命令");
        byte[] data = getSendHead(pileNum, cmd, 1);
        int num = headLength;
        return getData(data, num);
    }
}
