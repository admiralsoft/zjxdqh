package com.tcp.tcp.convert.command.impl;


import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.vo.RemoteChargingConfigVo;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;



/**
 * @author TcT
 *         Date: 2018/7/24.
 *         Time: 下午4:20.
 * @Title:
 * @Description: 远程充电配置
 */
@Service("remoteChargingConfigCommandImpl")
public class RemoteChargingConfigCommandImpl extends BaseCommand implements CommandService<RemoteChargingConfigVo> {

    @Override
    public byte[] getByteInfo(RemoteChargingConfigVo vo, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        /*设备类型*/
        data[num++] = (byte)vo.getPileType();
         /*经度*/
        String longitude = vo.getPileLongitude();
        byte[] doubleToBytes = DataUtil.StringToAsciiByte(longitude, 12);
        for (int i = 0; i < 12; i++) {
            data[num++] = doubleToBytes[i];
        }
        /*纬度*/
        String latitude = vo.getPileLatitude();
        byte[] bytes = DataUtil.StringToAsciiByte(latitude, 12);
        for (int i = 0; i < 12; i++) {
            data[num++] = bytes[i];
        }
        /*车位号*/
        byte[] carports = DataUtil.intToBytes(vo.getCarportNum());
        data[num++] = carports[0];
        data[num++] = carports[1];
        /*停车位号*/
        byte[] parkings = DataUtil.intToFourBytes(vo.getParkingNum());
        data[num++] = parkings[0];
        data[num++] = parkings[1];
        data[num++] = parkings[2];
        data[num++] = parkings[3];
        /*所属电站编号*/
        String stationNum = vo.getPowerStationNum();
//        char a = (char)0; 值0须Ascii码
//        char[] chars = new char[13];
        byte[] stations = DataUtil.StringToAsciiByte(stationNum, 13);
        for (int i = 0; i < 13; i++) {
            data[num++] = stations[i];
        }
        /*所属地区编号*/
        String areaNum = vo.getAreaNum();
        byte[] areaNums = DataUtil.StringToAsciiByte(areaNum, 13);
        for (int i = 0; i < 13; i++) {
            data[num++] = areaNums[i];
        }
        /*营运类型*/
        byte[] types = DataUtil.intToBytes(vo.getOperationType());
        data[num++] = types[0];
        data[num++] = types[1];
        return getData(data, num);
    }
}
