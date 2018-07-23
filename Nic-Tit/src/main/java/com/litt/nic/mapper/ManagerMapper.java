package com.litt.nic.mapper;

import java.util.List;

import com.litt.nic.entity.Manager;

public interface ManagerMapper {
	int deleteByPrimaryKey(Integer managerId);

	int insert(Manager record);

	int insertSelective(Manager record);

	Manager selectByPrimaryKey(Integer managerId);

	Manager selectByNamePsw(String name, String psw);

	int updateByPrimaryKeySelective(Manager record);

	int updateByPrimaryKey(Manager record);

	List<Manager> selectAllManager();

	/**
	 * 按名字查
	 */
	Manager findByName(String name);
}
