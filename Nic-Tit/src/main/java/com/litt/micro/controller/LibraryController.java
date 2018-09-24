package com.litt.micro.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.Score;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IScoreService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.service.impl.ScoreServicelmpl;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.MD5.MD5;
import com.litt.micro.util.prompt.HintoFront;


/**
 * 图书馆选座
 * @author Cardiac
 *
 */
@Controller
@RequestMapping("/Library")
public class LibraryController {
	
	@Autowired
	private IStudentService studentServiceImpl;
	private IScoreService scoreserverimpl;
	@RequestMapping("/test")

	public String test(HttpServletRequest Request,String name,String card_number){
		System.out.println(name+":"+card_number);
		
		Student stu= studentServiceImpl.findStu(name,card_number);
		System.out.println(stu);
		
/*		List l=new ArrayList<Score>();
		l = scoreserverimpl.findScore(card_number);
		for(int i=0;i<l.size();i++)
			System.out.println(l.get(i));*/
		return "/jsp/error/null";
	}
	
	@RequestMapping("/ass")
	public String inputMes(String code,HttpServletRequest request) throws Exception
	{	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1e611c20a19c682b&redirect_uri=http://xxzx.tit.edu.cn/Nic-Tit/Library/ass&responese_type=code&scope=snsapi_base#wechat_redirect
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("openid="+openid);
		Student stu= studentServiceImpl.findStudentByOpenid(openid);
		//如果找不到此学生，提示当前用户并未绑定信息
/*		if(stu==null){
			HintoFront.alertMsg("您尚未绑定个人信息", null, response);
			return "/jsp/student/stu_identify";
		}*/
		
		//String app_key=StudentController.app_key; 
		String app_key="tygyxysdhfj3343##$$";
		String sch_id = "133";
		String stud_no = stu.getStuCardNumber();
		String stud_name = stu.getStuName();
		System.out.println("appkey:"+app_key);

		
		//获取到用户信息并对其信息进行加密	

		 String sign=MD5.checkSignature(sch_id,stud_no,stud_name,app_key);
		 
		 System.out.println("sign=============================="+sign);

		 request.getSession().setAttribute("sch_id", 133);
		 request.getSession().setAttribute("stud_no", stu.getStuCardNumber());
		 request.getSession().setAttribute("stud_name", stu.getStuName());
		 request.getSession().setAttribute("sign", sign);   
		 
		//往接口跳
		return "/jsp/Library/Library";
	}

}
