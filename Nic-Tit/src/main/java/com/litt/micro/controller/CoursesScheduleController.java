package com.litt.micro.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.litt.wechat.Util.SignUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.entity.Student;
import com.litt.micro.service.ICoursesScheduleService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.stu.Rdata;

/**
 * 查课表
 * 
 * @author 18317
 *
 */

@Controller
@RequestMapping("/CoursesSchedule")
public class CoursesScheduleController {

	@Autowired
	private ICoursesScheduleService CoursesScheduleImpl;

	/**
	 * 进行签名验证，确保是微校数据，验证成功则返回课表数据， 失败跳转到失败页面
	 * 
	 * @param request
	 * @param response
	 * @param raw_data
	 *            加密前的数据
	 * @param app_key
	 *            appkey
	 * @return
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String load(@RequestBody String param) throws IOException// 将JSON字符串中的两个变量的值分别赋予了两个字符串
	{
		// 接收微校发送的数据(因为以Post方式传过来，需要特定方式接收)
		System.out.println(param);
		JSONObject parse = JSONObject.parseObject(param);
		System.out.println("param=" + param);
		String raw_data = parse.getString("raw_data");
		String app_key = parse.getString("app_key");
		System.out.println("app_key="+app_key);
		HttpServletRequest request = null;
		// 解密
		String DeString = null;
		try {
			DeString = AES.Decrypt(raw_data, app_key, StudentController.app_secret.substring(0, 16));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("解密后的数据:" + DeString);
		

		System.out.println();
		String name="linshili";
		System.out.println("============================================="+name);
		
		/*JSONObject json = JSONObject.parseObject(DeString); 
		String card_number =json.getString("card_number"); 
		String app_key1 =json.getString("app_key"); 
		String nonce_str =json.getString("nonce_str"); 
		Long str = (Long) json.get("timestamp");
		String timestamp = String.valueOf(str); 
		String sign =json.getString("sign");*/
		
		DeString=DeString.replace("{","");
		DeString=DeString.replace("}","");
		System.out.println("FinalDeString="+DeString);
		Map<String,String> map=new HashMap<String,String>();
		String str[]=DeString.split(",");
		String arr[]=new String [2];
		for(int i=0;i<str.length;i++)
		{
			System.out.println("=========================================================");
			arr=str[i].split(":");
			arr[0]=arr[0].replaceAll("\"", "");
			arr[1]=arr[1].replaceAll("\"", "");
			System.out.print(arr[0]);System.out.println("   "+arr[1]);
            map.put(arr[0], arr[1]);
               
            
		}
		String car_number=map.get("card_number");
		String app_key1 = map.get("app_key");
		String nonce_str = map.get("nonce_str");
		String timestamp = map.get("timestamp");
		//timestamp="\""+timestamp+"\"";
		String sign = map.get("sign");
				  
				  
				  
		System.out.println("card_number=" + car_number + "   app_key1=" + app_key1 + "   nonce_str=" + nonce_str
				+ "  timestamp=" + timestamp + "  sign=" + sign);
		
		CoursesSchedule cs=CoursesScheduleImpl.finStudentByCard_number(car_number);
		
		/*// 验证签名
		if (com.litt.micro.util.SignUtil.checkSignature(car_number, app_key1, timestamp, nonce_str, sign)) { 
			System.out.println("签名正确");
			// 根据学号取得课表信息 
			studentServiceImpl.finStudentByCard_number(car_number);
		
			return "/jsp/CoursesSchedule/CoursesSchedule";
		} else { // 请求失败，需保证返回的code 不为 0。
			// message 字段在 code 不为 0 时返回错误的信息。 message = "fail";
		    System.out.println("签名失败");
			return "/jsp/error/CoursesSchedule";
		}*/
	
		return "";
		
	}
	 
	
}
