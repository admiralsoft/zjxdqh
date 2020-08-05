package com.tcp.mapper;

import com.tcp.bean.TGun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TGunMapper {
    int deleteByPrimaryKey(String gunId);

    int insert(TGun record);

    int insertSelective(TGun record);

    TGun selectByPrimaryKey(String gunId);

    int updateByPrimaryKeySelective(TGun record);

    int updateByPrimaryKey(TGun record);

    TGun selectByPG(@Param("pNum") String pNum, @Param("gNum") String gNum);
    List<TGun> selectByPileNum(@Param("pNum") String pNum);

    int updateGunStat(TGun gun);

    void updateGunAdminState(TGun gun);
}