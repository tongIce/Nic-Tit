package com.litt.nic.service;

import com.litt.nic.entity.Article;

public interface IArticleService {
	int findMaxId();

	Article findById(int id);

	int save(Article article);
}
