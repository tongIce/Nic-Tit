package com.litt.micro.mapper;

import com.litt.micro.entity.CoursesSchedule;

public interface CoursesScheduleMapper {

	//根据学号查找学生信息
	CoursesSchedule finStudentByCard_number(String card_number);
}
