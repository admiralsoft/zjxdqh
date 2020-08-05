package com.tcp.tcp.vo.send.vo;

import com.tcp.bean.MUpgradeResult;

/**
 * @author TcT
 * Date: 2018/8/16.
 * Time: 上午9:44.
 * @Title:
 * @Description: 分包下载包装类
 */
public class PackDataDownVo extends MUpgradeResult {

    //数据包
    private byte[] data;

    //当前指令
    private Integer command;

    @Override
    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
