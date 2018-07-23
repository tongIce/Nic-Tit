package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.entity.Status;
import com.litt.nic.mapper.StatusMapper;
import com.litt.nic.service.IStatusService;

@Service
public class StatusServiceImpl implements IStatusService {

	@Autowired
	private StatusMapper statusmapper;

	public Status findById(int id) {

		return statusmapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Status> findAllStatus() {

		return statusmapper.findAllStatus();
	}

	@Override
	public Status findByName(String name) {
		return statusmapper.findByName(name);
	}

}
