package com.tcp.tcp.convert.parse.impl;

import com.tcp.bean.BOfflineChildinfo;
import com.tcp.bean.BOfflineinfo;
import com.tcp.core.code.ResultCode;
import com.tcp.log.CustomerLogger;
import com.tcp.mapper.BOfflineChildinfoMapper;
import com.tcp.mapper.BOfflineinfoMapper;
import com.tcp.tcp.convert.ByteToMessageConvert;
import com.tcp.tcp.convert.parse.BaseParse;
import com.tcp.tcp.convert.parse.TCPParseService;
import com.tcp.util.DataUtil;
import com.tcp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author TcT
 *         Date: 2018/7/23.
 *         Time: 上午11:54.
 * @Title:
 * @Description: 离线数据解析
 */
@Service("offlineInfoParseImpl")
@Slf4j
public class OfflineInfoParseImpl extends BaseParse implements TCPParseService<List<Map<String, Object>>>{

    @Resource
    private BOfflineinfoMapper bOfflineinfoMapper;

    @Resource
    private BOfflineChildinfoMapper bOfflineChildinfoMapper;


    @Override
    public List<Map<String, Object>> getInfo(byte[] data) {
        //记录失败数据
        Map<String,Object> resultMap;
        List<Map<String, Object>> maps = new LinkedList<>();
        int index = SUBSCRIPT;
        int num = DataUtil.oneBytesToInt(data[index++]);
        BOfflineinfo offlineInfo;

        byte[] bytes;
        BigDecimal thousand = new BigDecimal(10000);
        BigDecimal hundred = new BigDecimal(100);
        for (int i = 0; i < num; i++) {
            //获取桩号
            String pileNum = "";
            //序号
            int SerialNum = 0;
            for (int x = 8; x < 24; x++) {
                pileNum += DataUtil.byteAsciiToString(data[x]);
            }
            try {
                offlineInfo = new BOfflineinfo();
                //id
                String parentId = StringUtil.get32UUID();
                offlineInfo.setId(parentId);
                /*桩号*/
                offlineInfo.setPileNum(pileNum);
                /*需要记录个数*/
                offlineInfo.setDataNum(num);
                /*记录序号*/
                SerialNum = DataUtil.oneBytesToInt(data[index++]);
                offlineInfo.setSerialNum(SerialNum);
                /*本序号数据长度*/
                offlineInfo.setHowLong(DataUtil.twoBytesToInt(new byte[]{data[index++],data[index++]}));
                /*枪口号*/
                offlineInfo.setGunNum(DataUtil.oneBytesToInt(data[index++]));
                /* 0.卡号 1.服务器账号 */
                offlineInfo.setAccountType(DataUtil.oneBytesToInt(data[index++]));
                /*卡号*/
                offlineInfo.setCardnum(DataUtil.eightByteToLong(new byte[]{data[index++],data[index++],data[index++],data[index++],data[index++],data[index++],data[index++],data[index++]}));
                bytes = new byte[32];
                for (int j = 0; j < 32; j++) {
                    bytes[j] = data[index++];
                }
                /*账号*/
                offlineInfo.setAccount(DataUtil.byteAsciiToString(bytes));
                bytes = new byte[17];
                for (int j = 0; j < 17; j++) {
                    bytes[j] = data[index++];
                }
                /*车辆VIN*/
                offlineInfo.setVin(DataUtil.byteAsciiToString(bytes));
                /*充电开始时间 时间戳*/
                offlineInfo.setChargingStartime(new Date((long) DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) * 1000 ));
                /*充电结束时间*/
                offlineInfo.setChargingEndtime(new Date((long) DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) * 1000 ));
                /*充电总电量*/
                offlineInfo.setPowerSum(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) / 100.00).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                /*充电服务费*/
                offlineInfo.setPowerServce(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(hundred,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP) );
                /*充电总金额*/
                offlineInfo.setPowerAmount(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(hundred,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP) );
                /*时间个数 1-256*/
                int timeNum = DataUtil.oneBytesToInt(data[index++]);
                /*充电经过的时间段个数*/
                offlineInfo.setChargingTimeNum(timeNum);
                BOfflineChildinfo offlineChildInfo;
                for (int j = 0; j < timeNum; j++) {
                    offlineChildInfo = new BOfflineChildinfo();
                    /*id*/
                    offlineChildInfo.setId(StringUtil.get32UUID());
                    /*父键*/
                    offlineChildInfo.setParentId(parentId);
                    /*充电时间段开始时间*/
                    offlineChildInfo.setChildStartime(new Date((long) DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) * 1000 ));
                    /*充电时间段结束时间*/
                    offlineChildInfo.setChildEndtime(new Date((long) DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) * 1000 ));
                    /*充电时间段电量*/
                    offlineChildInfo.setPowTotal(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) / 100.00).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() );
                    /*充电时间段服务费单价*/
                    offlineChildInfo.setChargingServicePrice(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(thousand,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP));
                    /*充电时间段单价*/
                    offlineChildInfo.setPrice(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(thousand,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP));
                    /*充电时间段服务费金额*/
                    offlineChildInfo.setChargingServiceamount(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(hundred,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP));
                    /*充电时间段充电金额*/
                    offlineChildInfo.setChargingAmount(new BigDecimal(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]})).divide(hundred,2, RoundingMode.HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP));
                    //入库子表
                    offlineChildInfo.setCreateTime(new Date());
                    bOfflineChildinfoMapper.insert(offlineChildInfo);
                }
                /*起始SOC*/
                offlineInfo.setStarSoc(DataUtil.oneBytesToInt(data[index++]));
                /*终止SOC*/
                offlineInfo.setEndSoc(DataUtil.oneBytesToInt(data[index++]));
                /*起始充电时电表读数*/
                offlineInfo.setInitKilowatt(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) / 100.00);
                /*终止充电时电表读数*/
                offlineInfo.setEndKilowatt(DataUtil.fourBytesToInt(new byte[]{data[index++],data[index++],data[index++],data[index++]}) / 100.00);
                bytes = new byte[32];
                for (int j = 0; j < 32; j++) {
                    bytes[j] = data[index++];
                }
                /*订单号*/
                offlineInfo.setOrderNo(DataUtil.byteAsciiToString(bytes));
                //入库父表
                offlineInfo.setCreateTime(new Date());
                bOfflineinfoMapper.insert(offlineInfo);
                resultMap = new HashMap<>();
                resultMap.put("num",num);
                resultMap.put("SerialNum",SerialNum);
                resultMap.put("result", ResultCode.result_success);
                resultMap.put("id",parentId);
                maps.add(resultMap);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(pileNum+":离线数据入库失败",e);
                resultMap = new HashMap<>();
                resultMap.put("num",num);
                resultMap.put("SerialNum",SerialNum);
                resultMap.put("result",ResultCode.result_fail);
                maps.add(resultMap);
            }
        }
        //输出 指令 日志
        CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(data), ByteToMessageConvert.unWrapConvertDeviceNo(data), maps);
        return maps;
    }


}
