package com.tcp.tcp.convert.command.impl;

import com.tcp.bean.MUpgradeCommand;
import com.tcp.mapper.MUpgradeCommandMapper;
import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.command.CommandService;
import com.tcp.util.DataUtil;
import com.tcp.util.JsonUtils;
import com.tcp.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author TcT
 * Date: 2018/8/24.
 * Time: 下午5:45.
 * @Title:
 * @Description:
 */
@Slf4j
@Service("updateFTPCommandImpl")
public class UpdateFTPCommandImpl extends BaseCommand implements CommandService<String> {

    @Resource
    MUpgradeCommandMapper mUpgradeCommandMapper;
    @Override
    public byte[] getByteInfo(String str, String pileNum, short cmd) {
        //mq来的消息先入库
        //流程走完之后修改状态为执行成功
        //给平台返回信息
        //判断版本号
        /* 数据段头部 */
        int num = headLength;
        byte[] dataHead = getSendHead(pileNum, cmd, 1);
        MUpgradeCommand mUpgradeCommand = JsonUtils.toObject(str,MUpgradeCommand.class);
        mUpgradeCommand.setId(StringUtil.get32UUID());
        mUpgradeCommand.setPileNum(pileNum);
        mUpgradeCommand.setCreateTime(new Date());
        if(null!=mUpgradeCommand){
            mUpgradeCommandMapper.insert(mUpgradeCommand);
        }
        //解析数据拼装数据返回tcp
        //按照对应bean里面的属性做对应的转换
        //FTP地址长度
//        byte i = Byte.parseByte(String.valueOf(mUpgradeCommand.getFtp().length()), 16);
        byte i = DataUtil.intToHexByte(mUpgradeCommand.getFtp().length());
        //FTP地址
        byte[] s = DataUtil.StringToAsciiByte(mUpgradeCommand.getFtp(), mUpgradeCommand.getFtp().length());

        //FTP账号
        byte[] s1 = DataUtil.StringToAsciiByte(mUpgradeCommand.getFtpAccount(),8);
        //FTP密码
        byte[] s2 = DataUtil.StringToAsciiByte(mUpgradeCommand.getFtpPassword(),8);
        byte[] data = new byte[1124];
        System.arraycopy(dataHead,0,data,0,headLength);
        data[num++] = i;
        System.arraycopy(s,0,data,1+headLength,s.length);
        System.arraycopy(s1,0,data,1+s.length+headLength,s1.length);
        System.arraycopy(s2,0,data,1+s.length+s1.length+headLength,s2.length);
        int a = num+s.length+s1.length+s2.length;
        return getData(data,a);
    }
}
