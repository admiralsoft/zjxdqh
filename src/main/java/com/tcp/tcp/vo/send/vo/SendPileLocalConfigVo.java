package com.tcp.tcp.vo.send.vo;

import com.tcp.bean.MLocalParameters;
import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.base.message.BaseMessageBody;


public class SendPileLocalConfigVo extends MLocalParameters implements BaseMessageBody {

    /**
     *枪口二维码数据 长度
     */
    @Data(order = 13, byteType = DataByteTypEnum.Number)
    private Integer qrCodeLength;

    /**
     *枪口二维码数据
     */
    @Data(order = 14,byteType = DataByteTypEnum.ASCII, byteLenAttr = "qrCodeLength")
    private String qrCode;

    public Integer getQrCodeLength() {
        return qrCodeLength;
    }

    public void setQrCodeLength(Integer qrCodeLength) {
        this.qrCodeLength = qrCodeLength;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
