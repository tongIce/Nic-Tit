package com.litt.micro.service.impl;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.entity.Score;
import com.litt.micro.mapper.ScoreMapper;
import com.litt.micro.service.IScoreService;

//Score业务层实现类
@Transactional
@Service
public class ScoreServicelmpl implements IScoreService{
	@Autowired
	private ScoreMapper scoreMapper;
	
	//根据card_number查找出学生所有Score
	public ArrayList<Score> findScore(String card_number){
		 DynamicDataSourceHolder.setDataSource("dataSource2");
		ArrayList<Score> cs;
		cs = scoreMapper.findScore(card_number);
		
		return cs;
	}
}
