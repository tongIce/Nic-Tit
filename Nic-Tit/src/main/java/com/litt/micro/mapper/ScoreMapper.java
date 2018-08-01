package com.litt.micro.mapper;

import com.litt.micro.entity.Score;

public interface ScoreMapper {
	//根据name和学号查找出student
	Score[] findScore(String card_number);
}
