package com.tcp.convert;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.base.message.BaseMessageBody;

import java.util.List;

public class TestMessage implements BaseMessageBody {

    @Data(byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer pileNum;

    @Data(order = 1, byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer childLen;

    @Data(order = 2, byteType = DataByteTypEnum.ChildDataList, byteLenAttr = "childLen")
    private List<ChildMessage> childs;

    @Data(order = 3,byteType = DataByteTypEnum.Number, byteLen = 2)
    private Integer strLen;

    @Data(order = 4, byteType = DataByteTypEnum.ASCII, byteLenAttr = "strLen")
    private String strDesc;

    public Integer getChildLen() {
        return childLen;
    }

    public void setChildLen(Integer childLen) {
        this.childLen = childLen;
    }

    public List<ChildMessage> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildMessage> childs) {
        this.childs = childs;
    }

    public Integer getStrLen() {
        return strLen;
    }

    public void setStrLen(Integer strLen) {
        this.strLen = strLen;
    }

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    public Integer getPileNum() {
        return pileNum;
    }

    public void setPileNum(Integer pileNum) {
        this.pileNum = pileNum;
    }
}
