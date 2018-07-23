package com.litt.micro.mapper;

import java.util.List;

import com.litt.micro.entity.Examinee;
import com.litt.micro.entity.Student;

//等级考试持久层处理接口
public interface ExamineeMapper {
	//通过学号和姓名到相应表查考是否已经报名了
	List<Examinee> findExamineeByNumName(String name, String stuClass);
	//通过openid查询到学生信息
	Student findStudentByOpenid(String openid);
	//通过学号和姓名以及报考的类型查询，这个科目时候已经报考了
	Examinee findExamineeByType(String name, String number, String type);
	//数据库查询名族
	String findNation(String number);
	//数据库查询报考类型
	String findExamType(int parse);
		
}
