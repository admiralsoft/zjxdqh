package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 确定是否是同一个枪
 * <p>
 *
 * @author PengWei
 * @date 2019/5/13
 */
@Data
public class GunEle {

    /**
     * 桩号
     */
    private String pileNum;

    /**
     * 枪号
     */
    private String gunNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GunEle gunEle = (GunEle) o;
        return Objects.equals(pileNum, gunEle.pileNum) &&
                Objects.equals(gunNum, gunEle.gunNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pileNum, gunNum);
    }
}

