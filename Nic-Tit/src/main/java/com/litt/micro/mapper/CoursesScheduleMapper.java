package com.litt.micro.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.micro.entity.CoursesSchedule;

public interface CoursesScheduleMapper {

	//根据学号查找学生信息
	//List <CoursesSchedule> finStudentByCard_number(String xh,String xn,String xq);
	ArrayList<CoursesSchedule> finStudentByCard_number(String xh,String xn,String xq);
}
