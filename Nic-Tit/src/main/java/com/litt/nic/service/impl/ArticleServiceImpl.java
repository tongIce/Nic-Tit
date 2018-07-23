package com.litt.nic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.nic.entity.Article;
import com.litt.nic.mapper.ArticleMapper;
import com.litt.nic.service.IArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public int findMaxId() {
		return articleMapper.findMaxId();
	}

	@Override
	public Article findById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(Article article) {
		return articleMapper.insert(article);
	}

}
