package com.litt.nic.service;

import java.util.List;

import com.litt.nic.entity.Status;

public interface IStatusService {
	Status findById(int id);

	public List<Status> findAllStatus();

	public Status findByName(String name);
}
