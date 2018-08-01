package com.litt.micro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.micro.entity.Student;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.MD5.MD5;
import com.litt.micro.util.prompt.HintoFront;

@Controller
@RequestMapping("/Library")
public class LibraryController {
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@RequestMapping("/ass")
	public String inputMes(String openid,HttpServletRequest request)
	{
		
		System.out.println("openid="+openid);
		Student stu= studentServiceImpl.findStudentByOpenid(openid);
		//如果找不到此学生，提示当前用户并未绑定信息
		/*if(stu==null){
			HintoFront.alertMsg("您尚未绑定个人信息", null, response);
			return "/jsp/student/stu_identify";
		}*/
		
		String app_key=StudentController.app_key;
		
		//获取到用户信息并对其信息进行加密	
		/* String key="133"+stu.getStuName()+stu.getStuCardNumber()+"tygyxysdhfj3343##$$";*/
		 String sign=MD5.checkSignature("133",stu.getStuName(),stu.getStuCardNumber(),app_key);

		 System.out.println("sign=============================="+sign);
		 request.getSession().setAttribute("sch_id", 133);
		 request.getSession().setAttribute("stud_no", stu.getStuCardNumber());
		 request.getSession().setAttribute("stud_name", stu.getStuName());
		 request.getSession().setAttribute("sign", sign);   
		//往接口跳
		return "/jsp/Library/Library";
		
	}

}
