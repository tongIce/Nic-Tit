package com.litt.nic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.User;
import com.litt.nic.service.IUserService;
import com.litt.nic.service.impl.UserServiceImpl;

/**
 * 在pc后台对微信用户进行操作
 * @author Lenovo
 */
@Controller("pc")
@RequestMapping("/pc_user")
public class UserController {
	
	@Autowired
	private IUserService userServiceImpl;
	
	
	//查询出所有的微信用户，在pc后台进行显示；
	@RequestMapping("showUsers")
	public String showUsers(HttpServletRequest request){
		List<User> list=userServiceImpl.findAllUsers();
		//request放在里面
		request.setAttribute("userList",list);
		return "/WEB-INF/views/user/showUsers";
	}
	//设置用户消息共享的级别
	@RequestMapping("setShareType")
	public String setShareType(HttpServletRequest request ,Integer userId,Integer userShareType ){
		//id查询到用户，并在service层完成修改
	    userServiceImpl.updateShareType(userId,userShareType);
		List<User> list=userServiceImpl.findAllUsers();
		
		
		
		//request放在里面
		request.setAttribute("userList",list);
		return "/WEB-INF/views/user/showUsers";
	}
	
	
	
}
