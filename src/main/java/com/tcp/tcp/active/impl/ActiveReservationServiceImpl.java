package com.tcp.tcp.active.impl;

import com.tcp.core.enums.DictCodeEnum;
import com.tcp.tcp.active.AbStracActiveService;
import com.tcp.bean.MPreCommand;
import com.tcp.core.code.TCPCode;
import com.tcp.mapper.MPreCommandMapper;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TcT
 *         Date: 2018/8/4.
 *         Time: 上午12:15.
 * @Title:
 * @Description: 0x0102 运营平台发送预约命令
 */
@Service("activeReservationServiceImpl")
@Slf4j
public class ActiveReservationServiceImpl extends AbStracActiveService<String> {

    @Resource
    private MPreCommandMapper mPreCommandMapper;

    @Override
    public DictCodeEnum.SendMessageResult activeSend(String strings, String pileNum) {

        MPreCommand command = JsonUtils.toObject(strings, MPreCommand.class);

        MPreCommand preCommand = new MPreCommand();
        preCommand.setId(StringUtil.get32UUID());
        /*预约桩号,枪口号*/
        preCommand.setPillNum(pileNum);
        preCommand.setGunNum(command.getGunNum());
        /*预约 1. 开始预约 2. 停止预约*/
        preCommand.setPreType(command.getPreType());
        /*预约帐号*/
        preCommand.setPreAccount(command.getPreAccount());
        /*预约开始时间*/
        Date star = converTime(command.getPreStartTime(), pileNum);
        preCommand.setPreStartTime(star);
        /*预约结束时间*/
        Date end = converTime(command.getPreEndTime(), pileNum);
        preCommand.setPreEndTime(end);
        /*入库*/
        mPreCommandMapper.insert(preCommand);
        /*下发*/
        return sendMessage(activeReservationCommandImpl.getByteInfo(preCommand,pileNum, getCmd()),pileNum,getCmd());
    }

    private Date converTime(Date time,String pileNum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(time);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("时间转化错误: 桩号=="+ pileNum);
        }
        return parse;
    }

    @Override
    public short getCmd() {
        return TCPCode.PILE_RESERVATION;
    }
}
