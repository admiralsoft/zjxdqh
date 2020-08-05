package com.tcp.tcp.convert.parse.impl;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.tcp.vo.send.vo.PackDataDownVo;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/15.
 * Time: 下午7:54.
 * @Title:
 * @Description: 数据包升级回复解析
 */
@Service("packDataDownParseImpl")
public class PackDataDownParseImpl extends BaseParse implements TCPParseService<PackDataDownVo> {

    @Override
    public PackDataDownVo getInfo(byte[] data) {
        int index = SUBSCRIPT;
        PackDataDownVo result = new PackDataDownVo();
        //升级指示
        result.setUpgradeType(data[index++]);
        //软件版本号
        result.setVersion(DataUtil.byteAsciiToString(new byte[]{data[index++],data[index++],data[index++],data[index++]}));
        //总数据长度
        result.setResultLength(String.valueOf(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        //总数据包数
        result.setResultNum(String.valueOf(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        //已发送数据长度
        result.setSentResultLength(String.valueOf(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        //当前包数
        result.setNowResultNum(String.valueOf(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})));
        //升级地址
        result.setUpgradeLocal(String.valueOf(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index]})));
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), result);
        return result;
    }
}
