package com.tcp.tcp.convert;

import com.tcp.tcp.base.message.BaseMessageBody;
import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;

/**
 * 返回简单结果
 *
 * @author yorking
 */
public class SimpleByteMessageResult implements BaseMessageBody {

    /**
     * 配置成功
     */
    public static final byte RESULT_SUCCESS = 1;
    /**
     * 配置失败
     */
    public static final byte RESULT_FAIL = 2;

    public static SimpleByteMessageResult getInstance(byte result) {
        SimpleByteMessageResult instance = new SimpleByteMessageResult();
        instance.setResult(result);
        return instance;
    }

    /**
     * 返回结果 1配置成功，2配置失败
     */
    @Data(byteType = DataByteTypEnum.Byte)
    private byte result;

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
