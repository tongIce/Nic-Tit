package com.litt.micro.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.mapper.CoursesScheduleMapper;
import com.litt.micro.service.ICoursesScheduleService;


@Transactional
@Service
public class CoursesScheduleImpl implements ICoursesScheduleService{

	@Autowired
	private  CoursesScheduleMapper coursesschedulemapper;

	
	public CoursesSchedule finStudentByCard_number(String card_number) {
		// TODO Auto-generated method stub
		System.out.println("card_number="+card_number);
		CoursesSchedule cs=finStudentByCard_number(card_number);
		return cs;
	}

	
    
}
