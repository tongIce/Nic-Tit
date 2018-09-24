package com.litt.micro.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.litt.micro.entity.NewSExamination;
import com.litt.micro.entity.Result;
import com.litt.micro.entity.SExamination;
import com.litt.micro.entity.Yuangou;
import com.litt.micro.service.ISExaminationService;
import com.litt.micro.service.impl.SExaminationServiceImpI;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.TermNumber.TermNumber;
import com.litt.micro.util.Timetable.Timetable;

/**
 * 查考试
 * @author Cardiac
 *
 */

@Controller
@RequestMapping("/SExamination")
public class SExaminationController {
	
	
	@Autowired
	private ISExaminationService SExaminationimpl;
	
	@RequestMapping(value="/test")
	public String test(HttpServletRequest Request,String card_number,String xn,String xq){
		List l=new ArrayList<SExamination>();
		l = SExaminationimpl.findSExamination(card_number,xn,xq);
		for(int i=0;i<l.size();i++)
			{SExamination array=(SExamination) l.get(i);
					System.out.println(array.toString());
			}
		return "/jsp/error/null";
	}
	
	public static ArrayList<SExamination> arrSExamination= new ArrayList<SExamination>();
	public static List list = new ArrayList();//处理后的学生信息
	
	@Autowired
	private SExaminationServiceImpI SExaminationserviceimpl;
	
	@RequestMapping(value = "/sign",method = RequestMethod.POST)
	@ResponseBody
	public String  load(@RequestBody String param) throws Exception{
		String parse = param;
		//接收post数据并解析出row_data,app_key
		Map<String, String> map_a = CoursesScheduleController.Deal_Post(parse);
		String raw_data = map_a.get("raw_data");
		String app_key = map_a.get("app_key");
//		System.out.println("raw_data : "+raw_data+"   "+"app_key : "+app_key);
		
		//对raw_data进行解密并解析出card_number，app_key，nonce_str，timestamp，sign
		Map<String, String> map_b = CoursesScheduleController.Deal_Row_Date(raw_data,app_key);
		String card_number = map_b.get("card_number");
		String app_key1 = map_b.get("app_key");
		String nonce_str = map_b.get("nonce_str");
		String timestamp = map_b.get("timestamp");
		String sign = map_b.get("sign");
        /*System.out.println(card_number+" "+nonce_str+" "+timestamp+" "+sign);*/
		//签名认证
		 if (com.litt.micro.util.SignUtil.KBcheckSignature(card_number,
		 app_key1, timestamp, nonce_str, sign))
		 {
			 
			 System.out.println("签名正确");
			// 获取时间日期
			
			Map<String, Object> map_c =getDate();
			int year = (int) map_c.get("year");
			String xn = (String) map_c.get("xn");
			String xq = (String) map_c.get("xq");
			// 查询并取出数据
			 arrSExamination = SExaminationserviceimpl.findSExamination(card_number,xn,xq);
			 System.out.println("共获取到" + arrSExamination.size() + "条数据");

			//封装rowdate
			String strEncryp = Deal_RowDate(card_number,year);
			list.clear();  //清空list，防止切换周数后在原数据基础上再添加
			
			//给微校返回的数据
			 String  jstu1 = CoursesScheduleController.Deal_Back(strEncryp,app_key);
			 return jstu1; 
		 }
	 	else{
	 		 System.out.println("签名错误");
			 String  jstu1 = CoursesScheduleController.FDeal_Back(app_key);
			 return jstu1;
			 }
		
	}


//获取当前日期并转换
public static Map<String, Object> getDate(){
	Map<String,Object> map = new HashMap<String,Object>();
	// 获取当前年月
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;

	// 计算学年和学期并为其赋值（数据库中的信息本学期的上学期是本学期的课表）
	String xn, xq;
	if (month <= 7) {
		year = year - 1;
		xn = String.valueOf(year);
		xq = "1";
	} else {
		xn = String.valueOf(year);
		xq = "0";
	}
	map.put("year", year);
	map.put("month", month);
	map.put("xn", xn);
	map.put("xq", xq);
	return map;
}
//获得学生信息result返回给list
public static List Deal_result(){
	for (int i = 0; i < arrSExamination.size(); i++) {
		NewSExamination	 newSE = new NewSExamination();
		newSE.setCourse_name(arrSExamination.get(i).getKCH());
		newSE.setTime(arrSExamination.get(i).getKSRQ());   //未改
		newSE.setLocation(arrSExamination.get(i).getSKDD());
		newSE.setSeat(arrSExamination.get(i).getZWH());
		list.add(newSE);
	  }	
	return list;		
}

//封装rowdate
	public static String Deal_RowDate(String card_number,int year){
		Map<String,Object> mapp = new HashMap<String,Object>();
		mapp.put("card_number", card_number);
		if(arrSExamination.size() !=0){
		mapp.put("session", year+ TermNumber.TermNumberConvert(arrSExamination.get(0).getXQ()));
		//获得学生信息time_table
		List list = Deal_result();
		System.out.println("本学期全部课程数："+list.size());
		mapp.put("result", list);
		}
		else{
			mapp.put("session", "");
			//获得学生信息time_table
			List list = Deal_result();
			System.out.println("本学期全部课程数："+list.size());
			mapp.put("result", list);
		}
		
		//rowdate转成json字符串
			String jstu = JSONObject.toJSONString(mapp);
		
		    String cKey=StudentController.app_key;
		    String cIv=StudentController.app_secret.substring(0, 16);
		    String  strEncryp = null;
		    
			try {
		       strEncryp=AES.Encrypt(jstu, cKey, cIv);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			// System.out.println("strEncryp : "+strEncryp);
			 //System.out.println("解密后："+new String(AES.Decrypt(strEncryp,cKey,cIv))); 
			 
		    return strEncryp;       
}

}