package com.litt.nic.mapper;

import java.util.List;

import com.litt.nic.entity.Status;

public interface StatusMapper {
	int deleteByPrimaryKey(Integer statusId);

	int insert(Status record);

	int insertSelective(Status record);

	Status selectByPrimaryKey(Integer statusId);

	int updateByPrimaryKeySelective(Status record);

	int updateByPrimaryKey(Status record);

	/**
     * 
     */
	public List<Status> findAllStatus();

	public Status findByName(String name);
}