package com.tcp.tcp.vo.receive.vo;


/**
 * @author TcT
 *         Date: 2018/7/24.
 *         Time: 上午10:48.
 * @Title:
 * @Description: 发送远程配置充电系统
 */
public class RemoteChargingConfigVo {

    /**
     * 设备类型:1. 直流快充 2. 直流慢充 3. 交流快充 4. 交流慢充 5. 交直流混合,0不更改
     */
    private int pileType;

    /**
     * 经度 12 小数后8 0不更改 ASCII码
     */
    private String pileLongitude;

    /**
     * 纬度 12  0不更改 ASCII码
     */
    private String pileLatitude;

    /**
     * 车位号 2 0不更改
     */
    private int carportNum;

    /**
     * 停车位号 4 0不更改
     */
    private int parkingNum;

    /**
     * 所属电站编号 13 0不更改
     */
    private String powerStationNum;

    /**
     * 所属地区编号 13 0不更改
     */
    private String areaNum;

    /**
     * 营运类型:1. 私有，不对外开放充电系统 2. 私有，对外开放充电系统 3. 公有免费充电系统 4. 公有收费充电系统,0不更改
     */
    private int operationType;

    public int getPileType() {
        return pileType;
    }

    public void setPileType(int pileType) {
        this.pileType = pileType;
    }

    public String getPileLongitude() {
        return pileLongitude;
    }

    public void setPileLongitude(String pileLongitude) {
        this.pileLongitude = pileLongitude;
    }

    public String getPileLatitude() {
        return pileLatitude;
    }

    public void setPileLatitude(String pileLatitude) {
        this.pileLatitude = pileLatitude;
    }

    public int getCarportNum() {
        return carportNum;
    }

    public void setCarportNum(int carportNum) {
        this.carportNum = carportNum;
    }

    public int getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(int parkingNum) {
        this.parkingNum = parkingNum;
    }

    public String getPowerStationNum() {
        return powerStationNum;
    }

    public void setPowerStationNum(String powerStationNum) {
        this.powerStationNum = powerStationNum;
    }

    public String getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(String areaNum) {
        this.areaNum = areaNum;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
}
