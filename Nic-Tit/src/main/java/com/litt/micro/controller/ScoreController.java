package com.litt.micro.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.litt.micro.entity.Score;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IExamineeService;
import com.litt.micro.service.IScoreService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.SignUtil;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.stu.MicroStu;

@Controller
@RequestMapping("/Score")
public class ScoreController {
	
	@Autowired
	private IStudentService studentServiceImpl;
	private IScoreService scoreserverimpl;
	
	
	
	@RequestMapping("information")
	public String getUserInfo(HttpServletRequest request,String card_number){
		System.out.println("111");
		System.out.println(card_number);
		Score []sc=scoreserverimpl.findScore(card_number);

		System.out.println(sc);
		return "/jsp/error/null";
	}
	
	/**
	 * 进行签名验证，确保是微校数据，验证成功则跳转到登录，
	 * 失败跳转到失败页面
	 * @param state
	 * @param app_key
	 * @param timestamp
	 * @param nonce_str
	 * @param sign
	 */
	
	
	@RequestMapping(value = "/sign",method = {RequestMethod.POST,RequestMethod.GET})
	/*public String  load(HttpServletRequest request,HttpServletResponse response, String state ,String app_key ,
		   String card_number,String timestamp,String nonce_str,String sign,String code )*/
	public String  load(@RequestBody String para) {
		//接受微笑提供的信息(是以post形式传过来的，需要特定方式接受)
		JSONObject  nparse = (JSONObject) JSONObject.parse(para);
		System.out.println(para);
/*		String app_key = (String) nparse.get("app_key");
		String raw_data =(String) nparse.get("raw_data");
		System.out.println("666");
		//将微校传来的raw_data进行解密
        String DeString = AES.Decrypt(raw_data, app_key, (StudentController.app_secret).substring(0, 16));
        System.out.println("解密后的字串是：" + DeString);
        
           //解密之后进行信息提取
        JSONObject  nDeString = (JSONObject) JSONObject.parse(DeString);
        String card_number = (String) nDeString.get("card_number");
        String nonce_str = (String) nDeString.get("nonce_str");
        String timestamp = (String) nDeString.get("timestamp");
        String sign = (String) nDeString.get("sign"); 
        System.out.println(card_number+" "+nonce_str+" "+timestamp+" "+sign);*/
        //签名验证
     /*if (SignUtil.checkSignature(card_number,app_key, timestamp, nonce_str,sign)) {
        	//获取学生信息
           
            
             //填充微校必须的字段(学号，姓名，年级，学院/部门，专业，手机号和身份证不能同时为空)
            MicroStu mstu = new MicroStu();
            
            
            //加密之后
            String param =" ";
  		 request.getSession().setAttribute("code", 0);
   		 request.getSession().setAttribute("message", "success");
   		 request.getSession().setAttribute("param", param);
   		 request.getSession().setAttribute("app_key", app_key); 
   		 return "/jsp/Score/Score";
        }else{
        	return "/jsp/error/error";
        }*/
        //@1如何将解密后的字符串中的信息单独提取出来？？？,包括card_number,app_key,nonce_str,timestamp,sign
		return " ";
        
        
        //@2用微笑的验证方式5个参数，缺少state这个参数
        //  用腾讯的验证方式3个参数，  用哪个？？？   进而设置code，message
        
        //@3 通过card_number查询学生信息  还需要自己写通过card_number查询学生信息的方法？？？？
	}
	
}