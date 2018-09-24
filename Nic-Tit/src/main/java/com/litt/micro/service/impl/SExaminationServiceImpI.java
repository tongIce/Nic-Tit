package com.litt.micro.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.SExamination;
import com.litt.micro.mapper.SExaminationMapper;
import com.litt.micro.service.ISExaminationService;


//SExamination业务层实现类
@Transactional
@Service
public class SExaminationServiceImpI implements ISExaminationService{
	@Autowired
	private SExaminationMapper sexaminationMapper;
	
	//根据card_number查找出学生所有SExamination
	public ArrayList<SExamination> findSExamination(String card_number,String xn,String xq){
		DynamicDataSourceHolder.setDataSource("dataSource2");
		ArrayList<SExamination> se;
		se=sexaminationMapper.findSExamination(card_number,xn,xq);
		return se;
	}
}
