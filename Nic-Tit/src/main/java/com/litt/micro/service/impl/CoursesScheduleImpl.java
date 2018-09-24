package com.litt.micro.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.mapper.CoursesScheduleMapper;
import com.litt.micro.service.ICoursesScheduleService;


@Transactional
@Service
public class CoursesScheduleImpl implements ICoursesScheduleService{

	@Autowired
	private  CoursesScheduleMapper coursesschedulemapper;

	
	public ArrayList<CoursesSchedule> finStudentByCard_number(String xh,String xn,String xq) {
        DynamicDataSourceHolder.setDataSource("dataSource2");
		ArrayList<CoursesSchedule> cs;
		cs=coursesschedulemapper.finStudentByCard_number(xh,xn,xq);
		return cs;
	}

	
    
}
