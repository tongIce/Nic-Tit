package com.litt.micro.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.Examinee;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IExamineeService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;

//查询报名结果
@Controller
@RequestMapping("search")
public class SearchResultsController {
	
	@Autowired
	private IExamineeService exServiceImpl;
	/*
	 * 根据获取到的openid，查询出信息，再到examinee表里面查找出考生信息展示
	 */
	@RequestMapping("searchResults")
	public String searchResults( HttpServletRequest request,String code) throws ParseException{
		//String openid = WeixinUtil.getOpenid(code);
		String openid  = "o4RLBwVUUUCXypK0l7BaS9186-u0";
		//通过回调，返回code获得openid，查找出学生，放到session域中
		Student stu = exServiceImpl.findStudentByOpenid(openid);

		//stu为空的情况没判断,因为表里面没有学号，通过班级查，bug点，一个班里面有重名的
		List<Examinee> exList = exServiceImpl.findExamineeByNumName(stu.getStuName(), stu.getStuClass());
		if(exList==null){
			request.getSession().setAttribute("count", exList);
			return "/jsp/searchResults/searchResults";
		}
		//暂时根据考号划分出等级，存到三个List中，放到session域
		List<Examinee> list1  = new ArrayList<Examinee>(); 
		List<Examinee> list2  =new ArrayList<Examinee>(); 
		List<Examinee> list3  =new ArrayList<Examinee>(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<Examinee> exit = exList.iterator();
		while(exit.hasNext()){
			Examinee next = exit.next();
			String type = next.getExAdmissionTicket().substring(0, 2).trim();
			int parse = Integer.parseInt(type);
			if(parse==24||parse==26||parse==28||parse==29||parse==61||parse==63||parse==64||parse==65){
				//数据库查询报考类型
				String typeName=exServiceImpl.findExamType(parse);
				next.setExType("二级:("+parse+")"+typeName);
				Date exBorn = next.getExBorn();
				String format = sdf.format(exBorn);
				next.setBornTostring(format);
				list1.add(next);
			}else if(parse==35||parse==36||parse==38||parse==39){
				String typeName=exServiceImpl.findExamType(parse);
				next.setExType("三级:("+parse+")"+typeName);
				Date exBorn = next.getExBorn();
				String format = sdf.format(exBorn);
				next.setBornTostring(format);
				list2.add(next);
			}else{
				//数据库查询报考类型
				String typeName=exServiceImpl.findExamType(parse);
				next.setExType("四级:("+parse+")"+typeName);
				Date exBorn = next.getExBorn();
				String format = sdf.format(exBorn);
				next.setBornTostring(format);
				list3.add(next);
			}
		}
		request.getSession().setAttribute("list1", list1);
		request.getSession().setAttribute("list2", list2);
		request.getSession().setAttribute("list3", list3);
		request.getSession().setAttribute("count", exList);
		return "/jsp/searchResults/searchResults";
	}
	/**
	 * 暂时报名表里面没有，openid，先通过openid在学生表里面找到学生，再通过学号姓名学号去查询
	 * 
	 */
	
}
