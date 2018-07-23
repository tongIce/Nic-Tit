package com.litt.micro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//测试微校扩展功能
@Controller
@RequestMapping("/test")
public class ConnectTestController {
	
	@RequestMapping("connect")
	public String connect(String Token,String token){
		System.out.println("进入链接测试代码块");
		System.out.println(Token+"====="+token);
		String name = "123456";
		String one = "fejo"+name+";";
		return "redirect:/test/toNetWork?name= "+name;
		
	}
	
	@RequestMapping("toNetWork")
	public ModelAndView download(String name){  
		System.out.println(name);
	    String url= "http://baidu.com";
	    ModelAndView downloadView = new ModelAndView(new RedirectView(url));  
	    	
	    ModelAndView view = new ModelAndView("/jsp/error/null");
	    //return downloadView;
	    return view;
	}  
	
	
}
