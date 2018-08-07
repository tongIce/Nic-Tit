package com.litt.micro.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.micro.entity.CoursesSchedule;

//课表接口
public interface ICoursesScheduleService {

	//根据学号查找学生信息
	//List <CoursesSchedule> finStudentByCard_number(String xh,String xn,String xq);
	ArrayList<CoursesSchedule> finStudentByCard_number(String xh,String xn,String xq);
}
