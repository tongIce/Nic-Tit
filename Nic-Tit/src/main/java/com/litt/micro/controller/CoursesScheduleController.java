package com.litt.micro.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.interceptor.AroundTimeout;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.litt.wechat.Util.SignUtil;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.entity.Result;
import com.litt.micro.entity.Score;
import com.litt.micro.entity.Student;
import com.litt.micro.entity.Yuangou;
import com.litt.micro.mapper.CoursesScheduleMapper;
import com.litt.micro.service.ICoursesScheduleService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.TermNumber.TermNumber;
import com.litt.micro.util.Timetable.Timetable;
import com.litt.micro.util.post.PostWithJson;
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

	public static ArrayList<CoursesSchedule> arrCourse = new ArrayList<CoursesSchedule>();  //提取出来的学生信息
	public static List list = new ArrayList();//处理后的学生信息
	@Autowired
	private ICoursesScheduleService CoursesScheduleImpl;


	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/load",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody

	public String load(@RequestBody String param) throws Exception
	{	
		String parse = param;
		
		//接收post数据并解析出row_data,app_key
		Map<String, String> map_a = Deal_Post(parse);
		String raw_data = map_a.get("raw_data");
		String app_key = map_a.get("app_key");
		//System.out.println("raw_data : "+raw_data+"   "+"app_key : "+app_key);
		
		//对raw_data进行解密并解析出card_number，app_key，nonce_str，timestamp，sign
		Map<String, String> map_b = Deal_Row_Date(raw_data,app_key);
		String card_number = map_b.get("card_number");
		String app_key1 = map_b.get("app_key");
		String nonce_str = map_b.get("nonce_str");
		String timestamp = map_b.get("timestamp");
		String sign = map_b.get("sign");
		
		//签名认证
		if (com.litt.micro.util.SignUtil.KBcheckSignature(card_number,
				app_key1, timestamp, nonce_str, sign))
		 {
		 System.out.println("签名正确");
		//获取当前   年月;学年学期
		 Map<String,Object> map_c = getDate();
		 int year = (int) map_c.get("year");
		 String xn = (String) map_c.get("xn");
		 String xq = (String) map_c.get("xq");
		 
		 
		// 查询并取出数据
		arrCourse = CoursesScheduleImpl.finStudentByCard_number(card_number, xn, xq);
		System.out.println("总共"+arrCourse.size()+"条数据");
		for(int i=0;i<arrCourse.size();i++){
			System.out.println(arrCourse.get(i).toString());
		}
		
		//封装rowdate
		String strEncryp = Deal_RowDate(card_number,year);
		list.clear();  //清空list，防止切换周数后在原数据基础上再添加
		
		//给微校返回的数据
		 String  jstu1 = Deal_Back(strEncryp,app_key);
		 //String jstu = new String(jstu1.getBytes("gbk"),"UTF-8");
		 return jstu1;  
		 }
 	else{
		 System.out.println("签名错误");
		 String  jstu1 = FDeal_Back(app_key);
		 return jstu1;
		 }
	}

	
		//接收post数据并解析出row_data,app_key
		public static Map<String, String> Deal_Post(String param){
		// 接收微校发送的数据(因为以Post方式传过来的是json配对的字符串，将其转换成jsonobjec形式方便提取信息)
			Map<String, String> map = new HashMap<String, String>();
			JSONObject parse = JSONObject.parseObject(param);
			String raw_data = parse.getString("raw_data");
			String app_key = parse.getString("app_key");
			map.put("raw_data",raw_data);
			map.put("app_key", app_key);
			return map;	
		}

		//对raw_data进行解密并解析出card_number，app_key，nonce_str，timestamp，sign
		public static Map<String, String> Deal_Row_Date(String raw_data,String app_key){
			
			String DeString = null;
			try {
				DeString = AES.Decrypt(raw_data, app_key, StudentController.app_secret.substring(0, 16));
			} catch (Exception e) {
				e.printStackTrace();
			}

			DeString = DeString.replace("{", "");
			DeString = DeString.replace("}", "");
			Map<String, String> map = new HashMap<String, String>();
			String str[] = DeString.split(",");
			String arr[] = new String[2];
			for (int i = 0; i < str.length; i++) {
				arr = str[i].split(":");
				arr[0] = arr[0].replaceAll("\"", "");
				arr[1] = arr[1].replaceAll("\"", "");
				map.put(arr[0], arr[1]);
			}
			return map;
		}

		//获取当前   年月;学年学期
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
	
		//获得学生信息time_table返回给list
		public static List Deal_time_table(){
			for (int i = 0; i < arrCourse.size(); i++) {
				String regex = "\\d{1,}-\\d{1,}";
				Pattern pat = Pattern.compile(regex);
				Matcher mat = pat.matcher(arrCourse.get(i).getStimezc());

				// 如果匹配1-16周情况(型如stimezc='1-16')
				if (mat.matches()) {
						// stimezc按"-"进行分割
						String stimezc = arrCourse.get(i).getStimezc();
						String strr[] = stimezc.split("-");
						int low = Integer.valueOf(strr[0]);
						int high = Integer.valueOf(strr[1]);
						/**
						 * 封装每条记录(每一条重复封装好多次)
						 */
						for (int x = low; x <= high; x++) {
							
							//单双周都上课
							if(Integer.valueOf(arrCourse.get(i).getDSZ())==0)
							{
								Result result=new Result();
								result.setCourse_id(arrCourse.get(i).getKCDM());
								result.setCourse_class(arrCourse.get(i).getXqj().substring(2, 3));
								result.setBegin_time(Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setEnd_time(Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setCourse_name(arrCourse.get(i).getLessname());
								result.setTeacher(arrCourse.get(i).getJsxm());
								result.setDay(arrCourse.get(i).getXqj().substring(1, 2));
								result.setClass_name(arrCourse.get(i).getSKBJ());
								result.setWeek(String.valueOf(x));
								result.setAddress(arrCourse.get(i).getRoom());
								result.setRequired_course("-1");
								list.add(result);
							}
							
							//仅单周上课
							if(Integer.valueOf(arrCourse.get(i).getDSZ())==1&&x%2==1)
							{
								Result result=new Result();
								result.setCourse_id(arrCourse.get(i).getKCDM());
								result.setCourse_class(arrCourse.get(i).getXqj().substring(2, 3));
								result.setBegin_time(Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setEnd_time(Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setCourse_name(arrCourse.get(i).getLessname());
								result.setTeacher(arrCourse.get(i).getJsxm());
								result.setDay(arrCourse.get(i).getXqj().substring(1, 2));
								result.setClass_name(arrCourse.get(i).getSKBJ());
								result.setWeek(String.valueOf(x));
								result.setAddress(arrCourse.get(i).getRoom());
								result.setRequired_course("-1");
								list.add(result);
							}
							
							//仅双周上课
							if(Integer.valueOf(arrCourse.get(i).getDSZ())==2&&x%2==0)
							{
								Result result=new Result();
								result.setCourse_id(arrCourse.get(i).getKCDM());
								result.setCourse_class(arrCourse.get(i).getXqj().substring(2, 3));
								result.setBegin_time(Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setEnd_time(Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
								result.setCourse_name(arrCourse.get(i).getLessname());
								result.setTeacher(arrCourse.get(i).getJsxm());
								result.setDay(arrCourse.get(i).getXqj().substring(1, 2));
								result.setClass_name(arrCourse.get(i).getSKBJ());
								result.setWeek(String.valueOf(x));
								result.setAddress(arrCourse.get(i).getRoom());
								result.setRequired_course("-1");
								list.add(result);
							}
							
				        }	
				  }

				// 不连续上课情况(如形式与政策stimezc='8'/stimezc='12')
				else {
					Result result=new Result();
					result.setCourse_id(arrCourse.get(i).getKCDM());
					result.setCourse_class(arrCourse.get(i).getXqj().substring(2, 3));
					result.setBegin_time(Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
					result.setEnd_time(Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
					result.setCourse_name(arrCourse.get(i).getLessname());
					result.setTeacher(arrCourse.get(i).getJsxm());
					result.setDay(arrCourse.get(i).getXqj().substring(1, 2));
					result.setClass_name(arrCourse.get(i).getSKBJ());
					result.setWeek(String.valueOf(arrCourse.get(i).getStimezc()));
					result.setAddress(arrCourse.get(i).getRoom());
				    result.setRequired_course("-1");
					list.add(result);	
				  }
			}
			return list;		
		}

		
		//封装rowdate
		public static String Deal_RowDate(String card_number,int year){
			Yuangou FZ=new Yuangou();
			FZ.setCard_number(card_number);
			FZ.setType("0");
			FZ.setSession(year+ TermNumber.TermNumberConvert(arrCourse.get(0).getXq()));
			
			//获得学生信息time_table
			List list = Deal_time_table();
			System.out.println("本学期全部课程数："+list.size());
			
			FZ.setTimetable(list);
			//rowdate转成json字符串
			String jstu = JSONObject.toJSONString(FZ);

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

		//请求成功返回的数据
		public static String Deal_Back(String strEncryp,String app_key){
		    Map<String,Object> mapp = new HashMap<String,Object>();
		    mapp.put("code", 0);                                
		    mapp.put("message", "success");    
		    mapp.put("raw_data", strEncryp);
		    mapp.put("app_key", app_key);
		    System.out.println(mapp);
		    String jstu1 = JSONObject.toJSONString(mapp);
		    //System.out.println(jstu1);
		    return jstu1;
		}
		//请求失败返回的数据
		public static String FDeal_Back(String app_key){
		    Map<String,Object> mapp = new HashMap<String,Object>();
		    mapp.put("code", 1);                                
		    mapp.put("message", "false");    
		    mapp.put("raw_data", " ");
		    mapp.put("app_key", app_key);
		    String jstu1 = JSONObject.toJSONString(mapp);
			return jstu1;
			
		}
}
