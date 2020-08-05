package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.MUpgradeResult;
import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.AddData;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;


/**
 * @Author yaweiXu
 */
@Service("mUpgradeResultParseImpl")
public class MUpgradeResultParseImpl extends BaseParse implements TCPParseService<MUpgradeResult> {
    @Override
    public MUpgradeResult getInfo(byte[] data) {
        int index = SUBSCRIPT;
        MUpgradeResult mUpgradeResult = new MUpgradeResult();
        /** 升级指示**/
        mUpgradeResult.setUpgradeType(data[index++]);
        //软件版本号
        mUpgradeResult.setVersion(DataUtil.byteAsciiToString(AddData.appendNext(data,4,1)));
        //总数据长度
        mUpgradeResult.setSentResultLength(DataUtil.bcdToString(AddData.appendNext(data,4,5)));
        //总数据包数
        mUpgradeResult.setResultNum(DataUtil.bcdToString(AddData.appendNext(data,4,9)));
        //已发送数据长度
        mUpgradeResult.setSentResultLength(DataUtil.bcdToString(AddData.appendNext(data,4,13)));
        //当前包数
        mUpgradeResult.setNowResultNum(DataUtil.bcdToString(AddData.appendNext(data,4,17)));
        //升级地址
        mUpgradeResult.setUpgradeLocal(DataUtil.bcdToString(AddData.appendNext(data,4,21)));

        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), mUpgradeResult);
        return mUpgradeResult;
    }
}
