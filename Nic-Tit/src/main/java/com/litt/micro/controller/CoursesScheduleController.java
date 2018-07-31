package com.litt.micro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.litt.wechat.Util.SignUtil;
import com.alibaba.fastjson.JSONObject;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.stu.Rdata;
/**
 * 查课表
 * @author 18317
 *
 */

@Controller
@RequestMapping("/CoursesSchedule")
public class CoursesScheduleController {
  
	/**
	 * 进行签名验证，确保是微校数据，验证成功则返回课表数据，
	 * 失败跳转到失败页面
	 * @param request
	 * @param response
	 * @param raw_data 加密前的数据
	 * @param app_key  appkey
	 * @return
	 */
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@RequestMapping(value="/sign",method=RequestMethod.POST)
	public String load(@RequestBody String param,HttpServletRequest request)
	{
		//接收微校发送的数据(因为以Post方式传过来，需要特定方式接收)
		JSONObject nparse=(JSONObject)JSONObject.parse(param);
		System.out.println("param="+param);
		String sSrc=nparse.getString("raw_data");
		String sKey=nparse.getString("app_key");
		
		//解密
		String DeString = null;
		try {
			DeString=AES.Decrypt(sSrc, sKey, StudentController.app_secret.substring(0, 16));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("解密后的数据:"+DeString);
		//nparse.clear();
		JSONObject jsono=(JSONObject)JSONObject.parse(DeString);
	    String card_number=jsono.getString("card_number");
	    String app_key=jsono.getString("app_key");
	    String nonce_str=jsono.getString("nonce_str");
	    String timestamp=jsono.getString("timestamp");
	    String sign=jsono.getString("sign");
	    //jsono.clear();
	    System.out.println("card_number="+card_number+"   app_key="+app_key+"   nonce_str="+nonce_str+"  timestamp="+timestamp+"  sign="+sign);
	    
	    
	    //验证签名
	    String message=null;
	    if(com.litt.micro.util.SignUtil.checkSignature(card_number, app_key, timestamp, nonce_str, sign)){
	    	//请求成功，需保证返回的code 为 0。
	    	//根据学号取得课表信息
	    	Student stu= studentServiceImpl.findStudentByOpenid(card_number);
	    	
	    	message= "success";
        	
			request.getSession().setAttribute("code", 0);
        	request.getSession().setAttribute("message",message);
			request.getSession().setAttribute("param",param);
        	request.getSession().setAttribute("app_key",app_key);
        	
			
			
        	return "/jsp/CoursesSchedule/CoursesSchedule";
	    }
	    else
	    {
	    	//请求失败，需保证返回的code 不为 0。
        	//message 字段在 code 不为 0 时返回错误的信息。
        	message="fail";
			request.getSession().setAttribute("code",1);
        	request.getSession().setAttribute("message",message);
        	request.getSession().setAttribute("param",param);
        	request.getSession().setAttribute("app_key",app_key);
        	return "/jsp/error/CoursesSchedule";
	    }
		

		
	}
	
}
