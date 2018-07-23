package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.entity.Department;
import com.litt.nic.mapper.DepartmentMapper;
import com.litt.nic.service.IDepartmentService;
@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private  DepartmentMapper departmentmapper;
	@Override
	public List<Department> findAllInfo() {
		return departmentmapper.selectAllInfo();
	}

}
