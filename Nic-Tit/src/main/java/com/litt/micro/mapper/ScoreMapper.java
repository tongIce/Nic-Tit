package com.litt.micro.mapper;

import java.util.ArrayList;

import com.litt.micro.entity.Score;

public interface ScoreMapper {
	//根据name和学号查找出student
	ArrayList<Score> findScore(String card_number);
}
