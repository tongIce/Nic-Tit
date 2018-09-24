package com.litt.micro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.litt.micro.datasourse.DynamicDataSourceHolder;
import com.litt.micro.entity.NewScore;
import com.litt.micro.entity.Score;
import com.litt.micro.entity.Student;
import com.litt.micro.service.IExamineeService;
import com.litt.micro.service.IScoreService;
import com.litt.micro.util.AES.AES;
import com.litt.micro.util.TermNumber.TermNumber;

/**
 * 查成绩
 * @author Cardiac
 *
 */
@Controller
@RequestMapping("/Score")
public class ScoreController {
	

	public static ArrayList<Score> arrScore = new ArrayList<Score>();  //提取出来的学生信息
	public static Map<String,List> list = new HashMap<String,List>();  //存储result
	
	
	@Autowired
	private IScoreService scoreserverimpl;	
	//检测数据库sqlserver的连接问题
	@RequestMapping(value="/test1")
	public String test1(HttpServletRequest Request,String card_number){

		System.out.println("进入");
		List l=new ArrayList<Score>();
		l = scoreserverimpl.findScore(card_number);
		for(int i=0;i<l.size();i++)
			System.out.println(l.get(i));
		return "/jsp/error/null";
	}
	
	//检测的数据库mysql的连接问题
	@Autowired
	private IExamineeService iExamineeService;
	@RequestMapping(value="/test2")
	public String test2(HttpServletRequest Request,String openid){
		//DynamicDataSourceHolder.setDataSource("dataSource1");
		System.out.println("进入");
		Student l=iExamineeService.findStudentByOpenid(openid);
		System.out.println(l);
		return "/jsp/error/null";
	}

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sign",method = RequestMethod.POST)
	@ResponseBody
	public String  load(@RequestBody String param) throws Exception{
		//切换到数据库sqlserver
		
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
			// 查询并取出数据
			 arrScore = scoreserverimpl.findScore(card_number);
			 System.out.println("总共"+arrScore.size()+"条");
			 /*	for(int i=0;i<arrScore.size();i++){
				 System.out.println(arrScore.get(i));
			 }*/
			//获得学生信息time_table返回给list
			 Deal_time_table();
			 
			//封装rowdate
			String strEncryp = Deal_RowDate(card_number);
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

		//获得学生信息result返回给list
		public static Map<String,List> Deal_time_table(){
			if(arrScore != null)
			{
				String Com = arrScore.get(0).getXN() +TermNumber.TermNumberScore(arrScore.get(0).getXQ());
				List brrScore = new ArrayList();
				for(int i=0;i<arrScore.size();i++){	
					
					String Date = arrScore.get(i).getXN() +TermNumber.TermNumberScore(arrScore.get(i).getXQ());
					
					if(Date.equals(Com))
					{
						NewScore newScore = new NewScore();
						newScore.setCourse_id(arrScore.get(i).getKCDM());
						newScore.setCourse_name(arrScore.get(i).getKCMC());
						newScore.setScore(arrScore.get(i).getCj_num());
						newScore.setGpa(arrScore.get(i).getXFJD());
						brrScore.add(newScore);
						if(i == (arrScore.size()-1))
						{
							System.out.println(Com);
							list.put(Com, brrScore);
						}
					}
					else
					{
						System.out.println(Com);

						list.put(Com, brrScore);
						brrScore = new ArrayList<NewScore>();
						Com = Date;
						i--;
					}
					
				}

				for(int i=0;i<list.size();i++)
					for(Object value : list.keySet()){  //只能遍历value
						System.out.println("Value = "+value);
						System.out.println(list.get(value));
					}
				String jstu = JSONObject.toJSONString(list);
//				System.out.println(list);
//				System.out.println(jstu);	
			}
			return list;
		}
		
		//封装rowdate
		public static String Deal_RowDate(String card_number) throws Exception{
			Map map = new HashMap();
			map.put("card_number", card_number);
			//获得学生信息result
			Deal_time_table();
			map.put("result", list);
			
			//rowdate转成json字符串
			String jstu = JSONObject.toJSONString(map);

	        String cKey=StudentController.app_key;
	        String cIv=StudentController.app_secret.substring(0, 16);
	        String  strEncryp = null;
	        
			try {
	           strEncryp=AES.Encrypt(jstu, cKey, cIv);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			 System.out.println("strEncryp : "+strEncryp);
			 System.out.println("解密后："+new String(AES.Decrypt(strEncryp,cKey,cIv))); 
			 
	        return strEncryp;       
		}
}