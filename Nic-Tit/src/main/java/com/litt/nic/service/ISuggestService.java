package com.litt.nic.service;

import java.util.List;

import com.litt.nic.entity.Suggest;
import com.litt.nic.entity.User;

public interface ISuggestService {

	void addsuggest(com.litt.nic.entity.Suggest suggest);

	List<Suggest> searchAll();

	void deletesuggest(int id);
	//通过list里面uid查找user
	List<User> findSuggestUser(List<Integer> uidlist);

}
