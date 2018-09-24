package com.litt.nic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.nic.entity.Information;
import com.litt.nic.mapper.InformationMapper;
import com.litt.nic.service.IInformationService;

@Service
public class InformationServiceImpl implements IInformationService {

	@Autowired
	private InformationMapper informationMapper;

	@Override
	public void addnews(Information information) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		informationMapper.insert(information);
	}

}
