package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.nic.entity.Suggest;
import com.litt.nic.entity.User;
import com.litt.nic.mapper.SuggestMapper;
import com.litt.nic.service.ISuggestService;

@Service
public class SuggestServiceImpl implements ISuggestService {

	@Autowired
	private SuggestMapper suggestMapper;

	@Override
	public void addsuggest(Suggest suggest) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		suggestMapper.insert(suggest);

	}

	@Override
	public List<Suggest> searchAll() {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		List<Suggest> suggests = suggestMapper.selectAllSuggest();
		return suggests;
	}

	@Override
	public void deletesuggest(int id) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		suggestMapper.deleteByPrimaryKey(id);
	}
	//通过list里面uid查找user
	@Override
	public List<User> findSuggestUser(List<Integer> uidlist) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		List<User> users = suggestMapper.findSuggestUser(uidlist);
		return users;
	}

}
