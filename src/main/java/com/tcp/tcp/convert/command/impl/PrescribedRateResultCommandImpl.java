package com.tcp.tcp.convert.command.impl;

import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;
import com.tcp.util.DataUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author TcT
 *         Date: 2018/7/22.
 *         Time: 上午9:36.
 * @Title:
 * @Description: 设置费率及时间段
 */
@Service("prescribedRateResultCommandImpl")
public class PrescribedRateResultCommandImpl extends BaseCommand implements CommandService<PrescribedRateVo> {


    @Override
    public byte[] getByteInfo(PrescribedRateVo rateVo, String pileNum, short cmd) {
        /* 数据段头部 */
        int num = headLength;
        byte[] data = getSendHead(pileNum, cmd, 1);
        List<PrescribedRateInfo> rateInfos = rateVo.getPrescribedRateInfos();
        /*枪号*/
        data[num++] = DataUtil.intToHexByte(rateVo.getGunNum());
        /*时间段个数*/

        data[num++] = DataUtil.intToHexByte(rateInfos.size());

        for (PrescribedRateInfo rateInfo : rateInfos) {
            String starTime = rateInfo.getStarTime().trim();
            String[] star = starTime.split(":");
            int [] starInt = new int[3];
            for (int i = 0; i < star.length; i++) {
                starInt[i] = Integer.parseInt(star[i]);
            }
                /*时*/
                data[num++] = DataUtil.intToHexByte(starInt[0]);
                /*分*/
                data[num++] =DataUtil.intToHexByte(starInt[1]);
                /*秒*/
                data[num++] = DataUtil.intToHexByte(starInt[2]);
            String endTime = rateInfo.getEndTime().trim();
            String[] end = endTime.split(":");
            int [] endInt = new int[3];
            for (int i = 0; i < end.length; i++) {
                endInt[i] = Integer.parseInt(end[i]);
            }
            /*时*/
            data[num++] = DataUtil.intToHexByte(endInt[0]);
            /*分*/
            data[num++] =DataUtil.intToHexByte(endInt[1]);
            /*秒*/
            data[num++] = DataUtil.intToHexByte(endInt[2]);
            //时间段电价
            int powerRate = rateInfo.getPowerRate().multiply(new BigDecimal("10000")).intValue();
            byte[] powers = DataUtil.intToFourBytes(powerRate);
            data[num++] = powers[0];
            data[num++] = powers[1];
            data[num++] = powers[2];
            data[num++] = powers[3];
            //时间段服务费
            int serviceRate = rateInfo.getServiceRate().multiply(new BigDecimal("10000")).intValue();
            byte[] services = DataUtil.intToFourBytes(serviceRate);
            data[num++] = services[0];
            data[num++] = services[1];
            data[num++] = services[2];
            data[num++] = services[3];
        }
        return getData(data, num);
    }
}
