package com.litt.nic.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.Suggest;
import com.litt.nic.entity.User;
import com.litt.nic.service.ISuggestService;
import com.litt.nic.service.IUserService;

@Controller
@RequestMapping(value = "/suggest")
public class ShowSuggestController {

	@Autowired
	private ISuggestService suggestService;
	@Autowired
	private IUserService userService;
	List<String> userList = new ArrayList<String>();;

	/**
	 * 跳转到管理留言页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toshowsuggest")
	public String toshow(HttpServletRequest request,
			HttpServletResponse response) {
		List<Suggest> suggests = suggestService.searchAll();
		//没有关联外键，通过user_id查出部门联系方式
		List<Integer> uidlist=new ArrayList<Integer>();
		Iterator<Suggest> sulist = suggests.iterator();
		while(sulist.hasNext()){
			Suggest suggest = sulist.next();
			uidlist.add(suggest.getUserId());
		}
		List<User> ulist = suggestService.findSuggestUser(uidlist);
		//让list集合倒序输出
		Collections.reverse(suggests);
		getUserNameList(request, suggests);
		request.setAttribute("ulist", ulist);
		request.setAttribute("suggests", suggests);
		return "/WEB-INF/views/work/showsuggest";

	}
	
	/**
	 * 删除留言
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String deleteSuggest(HttpServletRequest request,
			HttpServletResponse response) {

		suggestService.deletesuggest(Integer.parseInt(request
				.getParameter("suggestId")));
		return "redirect:/suggest/toshowsuggest";

	}

	/**
	 * 批量删除留言
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/batchdelete")
	public String deleteLotSuggest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int suggestId = 0;

		String[] suggestIdArray = request.getParameterValues("suggestId");
		for (int i = 0; i < suggestIdArray.length; i++) {
			System.out.println(suggestIdArray[i] + "---------------");
			suggestId = Integer.parseInt(suggestIdArray[i]);
			suggestService.deletesuggest(suggestId);
		}
		return "redirect:/suggest/toshowsuggest";

	}
	//没有关联外键，通过user_id查询用户的名字
	public void getUserNameList(HttpServletRequest request,
			List<Suggest> suggesttList) {
		for (int i = 0; i < suggesttList.size(); i++) {
			
			userList.add(userService.findById(suggesttList.get(i).getUserId())
					.getUserName());

		}
		Collections.reverse(userList);
		request.setAttribute("user", userList);
	}

}
