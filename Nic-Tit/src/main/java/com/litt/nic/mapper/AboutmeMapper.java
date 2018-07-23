package com.litt.nic.mapper;

import com.litt.nic.entity.Aboutme;

public interface AboutmeMapper {
    int deleteByPrimaryKey(Integer aboutmeId);

    int insert(Aboutme record);

    int insertSelective(Aboutme record);

    Aboutme selectByPrimaryKey(Integer aboutmeId);

    int updateByPrimaryKeySelective(Aboutme record);

    int updateByPrimaryKey(Aboutme record);
}