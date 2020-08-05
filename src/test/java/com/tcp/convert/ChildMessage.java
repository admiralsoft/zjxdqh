package com.tcp.convert;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.base.message.BaseMessageBody;

public class ChildMessage implements BaseMessageBody {

    @Data(byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer gunNum;

    public Integer getGunNum() {
        return gunNum;
    }

    public void setGunNum(Integer gunNum) {
        this.gunNum = gunNum;
    }
}
