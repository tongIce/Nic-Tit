package com.litt.micro.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.litt.micro.entity.Score;
import com.litt.micro.mapper.ScoreMapper;
import com.litt.micro.service.IScoreService;

@Transactional
@Service
public class ScoreServicelmpl implements IScoreService{
	@Autowired
	private ScoreMapper scoreMapper;
	//根据card_number和学号查找出学生所有Score
	public Score[] findScore(String card_number){
		Score Sco[] = scoreMapper.findScore(card_number);
		return Sco;
	}
}
