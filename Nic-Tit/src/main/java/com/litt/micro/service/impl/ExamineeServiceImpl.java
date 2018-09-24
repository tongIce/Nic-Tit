package com.litt.micro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.Examinee;
import com.litt.micro.entity.Student;
import com.litt.micro.mapper.ExamineeMapper;
import com.litt.micro.service.IExamineeService;

//等级考试业务层处理类
@Service
@Transactional
public class ExamineeServiceImpl implements IExamineeService {

	@Autowired
	private ExamineeMapper exMapper;
	//通过学号和姓名到相应表查考是否已经报名了
	public List<Examinee> findExamineeByNumName(String name, String stuClass) {
        DynamicDataSourceHolder.setDataSource("dataSource1");
		 List<Examinee> exList = exMapper.findExamineeByNumName(name, stuClass);
		 return  exList;
	}
	//通过openid查询到学生信息
	public Student findStudentByOpenid(String openid) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		Student student= exMapper.findStudentByOpenid(openid);
		return student;
	}
	//通过学号和姓名以及报考的类型查询，这个科目时候已经报考了
	public Examinee findExamineeByType(String name, String number, String type) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		Examinee ex = exMapper.findExamineeByType(name,number,type);
		return ex;
	}
	//数据库查询名族
	public String findNation(String exNation) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return exMapper.findNation(exNation);
	}
	//数据库查询报考类型
	public String findExamType(int parse) {
		DynamicDataSourceHolder.setDataSource("dataSource1");
		return exMapper.findExamType(parse);
	}
	
	

}
