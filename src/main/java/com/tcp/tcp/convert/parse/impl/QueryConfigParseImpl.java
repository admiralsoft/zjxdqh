package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.TLocalParameterResult;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @author TcT
 * Date: 2018/8/10.
 * Time: 下午6:59.
 * @Title:
 * @Description:
 */
@Service("queryConfigParseImpl")
public class QueryConfigParseImpl extends BaseParse implements TCPParseService<TLocalParameterResult> {

    @Override
    public TLocalParameterResult getInfo(byte[] data) {

        int index = SUBSCRIPT;
        TLocalParameterResult result = new TLocalParameterResult();
        /*硬件版本号*/
        result.setHardwareVersion(DataUtil.byteAsciiToString(new byte[]{data[index++],data[index++],data[index++],data[index++]}));
        /*软件版本号*/
        result.setSoftwareVersion(DataUtil.byteAsciiToString(new byte[]{data[index++],data[index++],data[index++],data[index++]}));
        /*相系*/
        result.setPhaseSystem(data[index++]);
        /** 最大输出电流  **/
        result.setMaxOutput(DataUtil.twoBytesToInt(new byte[]{data[index++],data[index++]}));
        /** 充电桩启动方式 1. 刷卡启动 2. APP启动 3. 刷卡/APP启动**/
        result.setPileStart(data[index++]);
        /** 是否允许离⽹充电 1，允许离⽹充电 2，禁⽌离⽹充电**/
        result.setPileOffnet(data[index++]);
        /** 离网个数 **/
        result.setPileOffnetNum(DataUtil.twoBytesToInt(new byte[]{data[index++],data[index++]}));
        /** 可用枪口个数 **/
        result.setUsableGunNum((int) data[index++]);
        /** 枪使用次数 **/
        result.setGunUseNum(DataUtil.twoBytesToInt(new byte[]{data[index++],data[index++]}));
        /** 充电桩是否⽀持预约 1⽀持 2禁⽌ **/
        result.setUsablePrePile(data[index++]);
        /** 充电桩显⽰屏登陆账号 **/
        byte[] account = new byte[16];
        for (int i = 0; i < 16; i++) {
            account[i] = data[index++];
        }
        result.setPileViewLoadingAccount(DataUtil.byteAsciiToString(account));
        /** 充电桩显示屏登陆密码 **/
        byte[] password = new byte[16];
        for (int i = 0; i < 16; i++) {
            password[i] = data[index++];
        }
        result.setPileViewLoadingPassword(DataUtil.byteAsciiToString(password));
        /*升级结果*/
        result.setUpgradeResult(data[index]);
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), result);
        return result;
    }
}
