package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.nic.entity.Manager;
import com.litt.nic.mapper.ManagerMapper;
import com.litt.nic.service.IManagerService;

@Service
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private ManagerMapper managerMapper;

	public Manager findById(int id) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return managerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Manager findByNamePsw(String name, String psw) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return managerMapper.selectByNamePsw(name, psw);
	}

	@Override
	public List<Manager> selectAllManager() {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return managerMapper.selectAllManager();
	}

	@Override
	public void insert(Manager record) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		managerMapper.insert(record);
	}

	@Override
	public void deleteByPrimaryKey(Integer managerId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		managerMapper.deleteByPrimaryKey(managerId);
	}

	@Override
	public Manager selectByPrimaryKey(Integer managerId) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return managerMapper.selectByPrimaryKey(managerId);
	}

	@Override
	public void updateByPrimaryKeySelective(Manager model) {
		// TODO Auto-generated method stub
		DynamicDataSourceHolder.setDataSource("dataSource1");
		managerMapper.updateByPrimaryKey(model);

	}

	@Override
	public Manager findByName(String name) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return managerMapper.findByName(name);
	}

}
