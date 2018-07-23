package com.litt.nic.mapper;

import com.litt.nic.entity.Information;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKeyWithBLOBs(Information record);

    int updateByPrimaryKey(Information record);
}