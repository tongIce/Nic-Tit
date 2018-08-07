package com.litt.micro.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.litt.wechat.Util.SignUtil;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.litt.micro.entity.CoursesSchedule;
import com.litt.micro.entity.Student;
import com.litt.micro.mapper.CoursesScheduleMapper;
import com.litt.micro.service.ICoursesScheduleService;
import com.litt.micro.service.IStudentService;
import com.litt.micro.util.WeixinUtil;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.TermNumber.TermNumber;
import com.litt.micro.util.Timetable.Timetable;
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

	public static int count = 0;
	public static ArrayList<CoursesSchedule> arrCourse = new ArrayList<CoursesSchedule>();
	public static List timetable = new ArrayList();
	@Autowired
	private ICoursesScheduleService CoursesScheduleImpl;

	/**
	 * 进行签名验证，确保是微校数据，验证成功则返回课表数据， 失败跳转到失败页面
	 * 
	 * @param request
	 * @param response
	 * @param raw_data
	 * @param app_key
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
		System.out.println("app_key=" + app_key);
		HttpServletRequest request = null;
		// 解密
		String DeString = null;
		try {
			DeString = AES.Decrypt(raw_data, app_key, StudentController.app_secret.substring(0, 16));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("解密后的数据:" + DeString);

		/*
		 * JSONObject json = JSONObject.parseObject(DeString); String car_number
		 * =json.getString("card_number"); String app_key1
		 * =json.getString("app_key"); String nonce_str
		 * =json.getString("nonce_str"); Long str = (Long)
		 * json.get("timestamp"); String timestamp = String.valueOf(str); String
		 * sign =json.getString("sign");
		 */

		DeString = DeString.replace("{", "");
		DeString = DeString.replace("}", "");
		System.out.println("FinalDeString=" + DeString);
		Map<String, String> map = new HashMap<String, String>();
		String str[] = DeString.split(",");
		String arr[] = new String[2];
		for (int i = 0; i < str.length; i++) {
			arr = str[i].split(":");
			arr[0] = arr[0].replaceAll("\"", "");
			arr[1] = arr[1].replaceAll("\"", "");
			System.out.print(arr[0]);
			System.out.println("   " + arr[1]);
			map.put(arr[0], arr[1]);
		}
		String card_number = map.get("card_number");
		String app_key1 = map.get("app_key");
		String nonce_str = map.get("nonce_str");
		String timestamp = map.get("timestamp");
		String sign = map.get("sign");
		System.out.println("card_number=" + card_number + "   app_key1=" + app_key1 + "   nonce_str=" + nonce_str
				+ "  timestamp=" + timestamp + "  sign=" + sign);

		// 获取当前年月
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		System.out.println(year + "年" + month + "月");

		// 计算学年和学期并为其赋值
		String xn, xq;
		if (month <= 7) {
			year = year - 1;
			xn = String.valueOf(year);
			xq = "1";
		} else {
			xn = String.valueOf(year);
			xq = "0";
		}

		String xh = card_number;

		// 查询并取出数据
		arrCourse = CoursesScheduleImpl.finStudentByCard_number(xh, xn, xq);

		// 封装数据
		Map<String, String> arrayMap = new HashMap<String, String>();
		//Map<String, JSONObject> arrmap = new HashMap<String, JSONObject>();
		List<Map> arraylist=new ArrayList<Map>();
		JSONObject obj= new JSONObject();
		//JSONObject innerObj= new JSONObject();
		obj.put("card_number", card_number);
		obj.put("type", "0");
		obj.put("session", arrCourse.get(0).getXn() + TermNumber.TermNumberConvert(arrCourse.get(0).getXq()));

		// 封装timetable
		for (int i = 0; i < arrCourse.size(); i++) {
			System.out.println("共取出:" + arrCourse.size() + "条数据");
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
						System.out.println("low=" + low + "  high=" + high);
		
						/**
						 * 封装每条记录(每一条重复封装好多次)
						 */
						for (int x = low; x <= high; x++) {
							
							arrayMap.put("course_id", arrCourse.get(i).getKCDM());
							arrayMap.put("course_class", arrCourse.get(i).getXqj().substring(2, 3));
							arrayMap.put("begin_time", Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
							arrayMap.put("end_time", Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
							arrayMap.put("course_name", arrCourse.get(i).getLessname());
							arrayMap.put("teacher", arrCourse.get(i).getJsxm());
							arrayMap.put("day", arrCourse.get(i).getXqj().substring(1, 2));
							arrayMap.put("class_name", arrCourse.get(i).getSKBJ());
							arrayMap.put("week", String.valueOf(x));
							arrayMap.put("address", arrCourse.get(i).getRoom());
							arrayMap.put("required_course", "-1");
							/*if(count==1)
							{	
								//arraylist.add(arrayMap);
								obj.put("timetable[", arrayMap);
								timetable.add(obj);					
							}
							else
							{
								//arraylist.add(arrayMap);
								obj.put(",", arrayMap);
								timetable.add(obj);
							}*/
						    
						    //obj= new JSONObject();
							arraylist.add(count++,arrayMap);
							arrayMap = new HashMap<String, String>();
				        }
						
			  }

			// 单周上课情况(如形式与政策stimezc='8'/stimezc='12')
			else {
					
					arrayMap.put("course_id", arrCourse.get(i).getKCDM());
					arrayMap.put("course_class", arrCourse.get(i).getXqj().substring(2, 3));
					arrayMap.put("begin_time", Timetable.startTime(arrCourse.get(i).getXqj().substring(2, 3)));
					arrayMap.put("end_time", Timetable.endTime(arrCourse.get(i).getXqj().substring(2, 3)));
					arrayMap.put("course_name", arrCourse.get(i).getLessname());
					arrayMap.put("teacher", arrCourse.get(i).getJsxm());
					arrayMap.put("day", arrCourse.get(i).getXqj().substring(1, 2));
					arrayMap.put("class_name", arrCourse.get(i).getSKBJ());
					arrayMap.put("week", String.valueOf(arrCourse.get(i).getStimezc()));
					arrayMap.put("address", arrCourse.get(i).getRoom());
					arrayMap.put("required_course", "-1");
					/*if(count==1)
					{
						//arraylist.add(arrayMap);
						obj.put("timetable[", arrayMap);
						timetable.add(obj);
					}
					else
					{
						//arraylist.add(arrayMap);
						obj.put(",", arrayMap);
						timetable.add(obj);
					}*/
					//obj= new JSONObject();
					arraylist.add(count++,arrayMap);
					arrayMap = new HashMap<String, String>();
			  }
			

		}
		
		/*System.out.println("arraylist的长度为:"+arraylist.size());
		for(int y=0;y<arraylist.size();y++)
		{

			System.out.println(arraylist.get(y));
		}*/
		obj.put("timetable", arraylist);
		timetable.add(obj);
		JSONArray jsonArray1 = JSONArray.fromObject(timetable);
		System.out.println("共"+jsonArray1.size()+"条数据");
		for (int j = 0; j < jsonArray1.size(); j++) {
             
			System.out.println("JSON:" + jsonArray1.get(j));
		}
         for(int h=0;h<timetable.size();h++)
         {
        	 System.out.println("timetable:"+timetable.get(h));
         }
		
         
		
		
		//加密
         System.out.println("转换成字符串后为:"+timetable.toString());
         String cKey=StudentController.app_key;
         System.out.println("cKey="+cKey);
         String cIv=StudentController.app_secret.substring(0, 16);
         System.out.println("cIv="+cIv);
		 try {
			 //加密后的字符串
			String  strEncryp=AES.Encrypt(timetable.toString(), cKey, cIv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 
		 
		//返回数据
		
		
		
		/*
		 * // 验证签名 if (com.litt.micro.util.SignUtil.checkSignature(car_number,
		 * app_key1, timestamp, nonce_str, sign)) { System.out.println("签名正确");
		 * // 根据学号取得课表信息 CoursesSchedule
		 * cs=CoursesScheduleImpl.finStudentByCard_number(car_number);
		 * 
		 * return "/jsp/CoursesSchedule/CoursesSchedule"; } else { //
		 * 请求失败，需保证返回的code 不为 0。 // message 字段在 code 不为 0 时返回错误的信息。 message =
		 * "fail"; System.out.println("签名失败"); return
		 * "/jsp/error/CoursesSchedule"; }
		 */

		return "";

	}

}
