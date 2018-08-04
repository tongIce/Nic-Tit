package com.litt.micro.service;

import com.litt.micro.entity.Student;

//studentservice接口
public interface IStudentService {
	//根据name和学号查找出student
	Student findStu(String name, String number);
	//将获取到的openId和student表关联
	void saveOpenId(String openid, String name, String number, String telephone);
	//通过openId查找学生信息
	Student findStudentByOpenid(String openid);	
}
