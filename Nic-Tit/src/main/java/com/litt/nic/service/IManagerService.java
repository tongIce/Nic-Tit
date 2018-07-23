package com.litt.nic.service;

import java.util.List;

import com.litt.nic.entity.Manager;

public interface IManagerService {
	Manager findById(int id);

	List<Manager> selectAllManager();

	Manager findByNamePsw(String name, String psw);

	void insert(Manager model);

	void deleteByPrimaryKey(Integer managerId);

	Manager selectByPrimaryKey(Integer managerId);

	void updateByPrimaryKeySelective(Manager model);

	Manager findByName(String manage_name);
}
