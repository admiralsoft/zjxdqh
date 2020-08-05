package com.tcp.tcp.vo.receive.vo;

import com.tcp.tcp.base.message.BaseMessageBody;
import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;

import java.util.List;

/**
 * @author TcT
 *         Date: 2018/7/20.
 *         Time: 下午1:42.
 * @Title:
 * @Description: 充电费率及时间段
 */
public class PrescribedRateVo implements BaseMessageBody {

    /**
     * 枪号
     */
    @Data(byteType = DataByteTypEnum.Number)
    private int	gunNum;

    /**
     *时间段个数
     */
    @Data(order = 1, byteType = DataByteTypEnum.Number)
    private int	timeNum;

    /**
     * 时间与费用
     */
    @Data(order = 2, byteType = DataByteTypEnum.ChildDataList, byteLenAttr = "timeNum")
    private List<PrescribedRateInfo> prescribedRateInfos;

    public int getGunNum() {
        return gunNum;
    }

    public void setGunNum(int gunNum) {
        this.gunNum = gunNum;
    }

    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }

    public int getTimeNum() {
        return timeNum;
    }

    public List<PrescribedRateInfo> getPrescribedRateInfos() {
        return prescribedRateInfos;
    }

    public void setPrescribedRateInfos(List<PrescribedRateInfo> prescribedRateInfos) {
        this.prescribedRateInfos = prescribedRateInfos;
    }

}
