package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.send.vo.PackDataDownVo;
import com.tcp.util.DataUtil;
import com.tcp.util.DownPackDataUtill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TcT
 * Date: 2018/8/16.
 * Time: 上午9:15.
 * @Title:
 * @Description: 数据包下载回复,流程回调
 */
@Service("packDataDownCommandImpl")
@Slf4j
public class PackDataDownCommandImpl extends BaseCommand implements CommandService<PackDataDownVo> {


    @Override
    public byte[] getByteInfo(PackDataDownVo info, String pileNum, short cmd) {

        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        Map<String, HashMap<String, byte[]>> downData = DownPackDataUtill.downData;
        //总包数
        HashMap<String, byte[]> map = downData.get(pileNum);


        /* 解析*/
        //升级指示 1，进入升级模式 2，升级数据 3，数据包下发完毕
        int command = info.getCommand();
        data[num++] = (byte) command;
        //软件版本号
        byte[] toAsciiByte = DataUtil.StringToAsciiByte(info.getVersion(), 4);
        for (int i = 0; i < 4; i++) {
            data[num++] = toAsciiByte[i];
        }
        //总数据长度
        byte[] lengths = DataUtil.intToFourBytes(Integer.parseInt(info.getResultLength()));
        for (int i = 0; i < 4; i++) {
            data[num++] = lengths[i];
        }
        //总数据包数
        byte[] allNum = DataUtil.intToFourBytes(map.size());
        for (int i = 0; i < 4; i++) {
            data[num++] = allNum[i];
        }
        //已发送数据长度
        byte[] sends = DataUtil.intToFourBytes(Integer.parseInt(info.getSentResultLength()));
        for (int i = 0; i < 4; i++) {
            data[num++] = sends[i];
        }
        //当前包数
        int nowNumber = Integer.parseInt(info.getNowResultNum());
        byte[] nowPackNum = DataUtil.intToFourBytes(nowNumber);
        for (int i = 0; i < 4; i++) {
            data[num++] = nowPackNum[i];
        }
        //升级地址
        for (int i = 0; i < 4; i++) {
            data[num++] = 0;
        }
        //升级数据长度
        byte[] packData = info.getData();
        int length = packData.length;
        byte[] bytes = DataUtil.intToBytes(length);
        for (int i = 0; i < 2; i++) {
            data[num++] = bytes[i];
        }
        //升级数据
        for (int i = 0; i < packData.length; i++) {
            data[num++] = packData[i];
        }
        //包尾
        return getData(data, num);
    }
}
