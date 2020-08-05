
package com.tcp.mapper;

import com.tcp.bean.TPile;
import com.tcp.tcp.vo.receive.PrescribedRateInfo;
import com.tcp.tcp.vo.receive.vo.PrescribedRateVo;

import java.util.List;

public interface TPileMapper {

	TPile getPileByNum(String pileNum);

	int updPileByNum(TPile record);

	List<PrescribedRateInfo> getPriceRates(String pileNum);

	Integer getOffLineMinByNum(String pileNum);


}