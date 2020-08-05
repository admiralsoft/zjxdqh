package com.tcp.tcp.active.impl;

import com.tcp.bean.JsonObject;
import com.tcp.core.code.TCPCode;
import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/14.
 * Time: 下午5:10.
 * @Title:
 * @Description: 0x0116 运营平台查询软件/固件下载是否成功命令
 */
@Service("activeDownResultServiceImpl")
@Slf4j
public class ActiveDownResultServiceImpl extends AbStracActiveService<JsonObject> {


    @Override
    public DictCodeEnum.SendMessageResult activeSend(JsonObject jsonObject, String pileNum) {
        try {
            //查询信息 0字节
            if (jsonObject.getObj()!=null) {
                String obj = (String) jsonObject.getObj();
            }
        }catch (Exception e){
            log.info("0x0116 运营平台查询软件/固件下载是否成功命令:获取查询信息异常");
        }
       /*下发*/
        return sendMessage(activeDownResultCommandImpl.getByteInfo(jsonObject,pileNum,getCmd()),pileNum,getCmd());
    }

    @Override
    public short getCmd() {
        return TCPCode.DOWN_RESULT;
    }


}
