package com.litt.nic.mapper;

import com.litt.nic.entity.Article;

public interface ArticleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	int findMaxId();
}