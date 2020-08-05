package com.tcp.tcp.convert.anno;

public enum DataByteTypEnum {

    ASCII(1, "ASCII码"),
    ChildData(2, "子类"),
    ChildDataList(3, "子数据集"),
    IPv4(4, "IPv4节点解析"),
    Byte(5, "小整数"),
    Number(6, "整数"),
    Decimal1(7, "1位小数"),
    Decimal2(8, "2位小数"),
    Decimal4(9, "4位小数"),
    DATE_YMD(10, "年月日(4位20170808)"),
    DATE_HMS(11, "时分秒(3位113000)"),
    BCDCODE(12, "BCD码"),
    ;

    DataByteTypEnum(int dataType, String typeDesc) {
        this.dataType = dataType;
        this.typeDesc = typeDesc;
    }

    private int dataType;

    private String typeDesc;
}
