package com.litt.micro.mapper;

import com.litt.micro.entity.Student;

public interface StudentMapper {
	//根据name和学号查找出student
	Student findStu(String name, String number);
	//将获取到的openId和student表关联
	void saveOpenId(String openid, String name, String number, String telephone);
	
}
