package com.litt.micro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.Student;
import com.litt.micro.mapper.StudentMapper;
import com.litt.micro.service.IStudentService;


//student业务层实现类
@Transactional
@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	
	//根据name和学号查找出student
	public Student findStu(String name, String number) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		Student stu = studentMapper.findStu(name,number);
		return stu;
	}
	//将获取到的openId和student表关联
	public void saveOpenId(String openid, String name, String number, String telephone) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		studentMapper.saveOpenId(openid,name,number,telephone);
	}
	
	public Student findStudentByOpenid(String openid) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
	   System.out.println("openid="+openid);
	   Student stu=studentMapper.findStudentByOpenid(openid);
	   return stu;
	}
	
	

}
