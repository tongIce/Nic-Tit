package com.litt.micro.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.litt.micro.entity.Examinee;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IExamineeService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.check.Checkout;
import com.litt.micro.util.img.PhotoUtil;
import com.litt.micro.util.prompt.HintoFront;
import com.litt.micro.util.properties.PropertiesReadUtils;

import net.sf.json.JSONObject;

/**
 * 腾讯微校，工具，计算机等级考试报名
 * @author Lenovo
 */
@Controller
@RequestMapping("/exam")
public class ExaminationController {
	@Autowired
	private IExamineeService examineeServiceImpl;
	@Autowired
	private IStudentService studentServiceImpl;
		
	@RequestMapping("signUp")
	public String signUp(HttpServletRequest request,HttpServletResponse response,String name,String number,String ID,String nation ,
			String level,String type,String openid,
			@RequestParam("files") MultipartFile file){
		
		/**
		 * 首先查询此学生是否已经报名，已将报名则显示报名信息(存在用户可以报名多次情况)，还未报名则显示报名页面
		 *    在接受到用户的报名信息之后，查询相应的考试类别中是否有此用户，有则提示不能重复报名，，，，，
		 * 如果没有，则在考试类别中插入信息，提示报名成功------
		 */
		 //首先验证用户的手机号和身份证号格式，是否正确
		 boolean mobileNum = Checkout.isMobileNum(number);
		if (mobileNum == false) {
			HintoFront.alertMsg("请输入正确的手机号", null, response);
			return "/jsp/computerExam/exam";
		}
		boolean idNumber = Checkout.isIDNumber(ID);
		if(idNumber == false){
			HintoFront.alertMsg("请输入正确的身份证", null, response);
		}
		//通过学号和姓名以及报考的类型查询，这个科目时候已经报考了
		Examinee ex = examineeServiceImpl.findExamineeByType(name,number,type);
		if(ex==null){
			//表里面不存在，将考生信息插入到报名表,在学生表里面将学生信息查询出来
			Student stu = studentServiceImpl.findStu(name, number);
			Examinee ex1 = new Examinee();
			//examineeServiceImpl.saveExaminee();
		}
		
		
		//保存图片，保存学生信息，并将图片地址保存到数据库中
		/**
		 * 设计报名表,查出
		 * 1、一个科目一个报名表，将学生信息保存进去
		 * 2、报名表包含：
		 * 	报名人的基本信息，名族，考试科目，以及一寸照片存放的位置，
		 * 	
		 */
		//将saveFile里面的反斜扛代替
		
		
		return "";
	}
	
	@RequestMapping("testJsp")
	public String testJsp(HttpServletRequest request 
			){
		Student student = new Student();
		student.setStuName("张晓晓");
		student.setStuCardNumber("152056120");
		student.setStuPhone("15735041479");
		System.out.println("进入到了报名页面------");
		request.getSession().setAttribute("student", student);
		
		return "/jsp/computerExam/exam";
	}
	
	
	/**
	 * 通过微校后台添加地址，直接回调此地址，获得code
	 * @param code
	 */
	@RequestMapping("enrol")
	public String enrol( HttpServletRequest request, String code,
			String name,String number,String tele,String ID
			
			){
		String openid = WeixinUtil.getOpenid(code);
		//通过唯一标示查询到用户,在跳转页面之前，显示学生信息
		Student student = examineeServiceImpl.findStudentByOpenid(openid);
		request.getSession().setAttribute("student", student);
		System.out.println("学生信息是："+student.getStuName());
		
		return "/jsp/computerExam/exam";
	}
}
