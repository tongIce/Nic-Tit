package com.litt.micro.service;

import com.litt.micro.entity.Score;

//ScoreService接口
public interface IScoreService {
	//根据card_number查找出学生所有Score
	Score findScore(String card_number,String XQ,String KCDM);

}
