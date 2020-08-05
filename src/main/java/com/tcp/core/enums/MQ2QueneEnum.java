package com.tcp.core.enums;

public enum MQ2QueneEnum{

    ToOperation(1,"运营消息队列"),
    ToOperationCharging(4,"运营充电中消息队列"),
    ToMainTain(2, "运维消息队列"),
    ToOperTain(3, "运营和运维消息队列");


    private int quene;
    private String toname;

    MQ2QueneEnum(int quene, String toname) {
        this.quene = quene;
        this.toname = toname;
    }

    public int getQuene() {
        return quene;
    }

    public String getToname() {
        return toname;
    }
}
