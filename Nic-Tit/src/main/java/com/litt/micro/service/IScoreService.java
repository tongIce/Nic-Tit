package com.litt.micro.service;

import java.util.ArrayList;

import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.entity.Score;

//ScoreService接口
public interface IScoreService {
	//根据card_number查找出学生所有Score
	ArrayList<Score> findScore(String card_number);

}
