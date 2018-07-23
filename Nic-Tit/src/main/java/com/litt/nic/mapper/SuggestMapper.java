package com.litt.nic.mapper;

import java.util.List;

import com.litt.nic.entity.Suggest;
import com.litt.nic.entity.User;

public interface SuggestMapper {
	int deleteByPrimaryKey(Integer suggestId);

	int insert(Suggest record);

	int insertSelective(Suggest record);

	Suggest selectByPrimaryKey(Integer suggestId);

	List<Suggest> selectAllSuggest();

	int updateByPrimaryKeySelective(Suggest record);

	int updateByPrimaryKey(Suggest record);
	//通过list里面uid查找user
	List<User> findSuggestUser(List<Integer> uidlist);
}