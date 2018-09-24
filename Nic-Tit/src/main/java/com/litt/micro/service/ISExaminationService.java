package com.litt.micro.service;

import java.util.ArrayList;

import com.litt.micro.entity.SExamination;



public interface ISExaminationService {
	//根据学号查找出 考场SExamination
			ArrayList<SExamination> findSExamination(String card_number,String xn,String xq);
}
