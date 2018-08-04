package com.litt.micro.service;

import com.litt.micro.entity.CoursesSchedule;

//课表接口
public interface ICoursesScheduleService {

	//根据学号查找学生信息
		CoursesSchedule finStudentByCard_number(String card_number);
}
