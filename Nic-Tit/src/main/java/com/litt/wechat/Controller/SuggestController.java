package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.Suggest;
import com.litt.nic.entity.User;
import com.litt.nic.service.ISuggestService;
import com.litt.nic.service.IUserService;
import com.litt.wechat.Util.Token.WeixinUtil;

@Controller
@RequestMapping(value = "/suggest")
public class SuggestController {

	private User user;

	@Autowired
	private IUserService userService;
	@Autowired
	private ISuggestService suggestService;

	@RequestMapping(value = "/toadd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String openid = request.getParameter("openid");
		User DataBaseUser = userService.findByOpenid(openid);
		// 数据库不存在此人
		if (DataBaseUser == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请回复 ‘1’ ，完善个人信息后再提交相关业务信息！');");
			// out.println("history.back();");
			out.println("</script>");
			return "redirect:/user/loadInfo?openid="+openid;
		} else {
			request.setAttribute("openid", openid);

			return "/jsp/suggest_info";
		}
	}

	@RequestMapping(value = "/addsuggest")
	public String addSuggest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		Suggest suggest = new Suggest();
		String openid = request.getParameter("openid");
		user = userService.findByOpenid(openid);
		if (user == null) {
			System.out.println("user时空的");
		}
		// System.out.println(user.getuserName() + "===================");
		System.out.println("openid==========================" + openid);
		suggest.setSuggestTitle(request.getParameter("title"));
		suggest.setSuggestContent(request.getParameter("content"));
		String nowtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		suggest.setSuggestTime(nowtime);
		suggest.setUserId(user.getUserId());

		suggestService.addsuggest(suggest);
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('提交成功！');");
		out.println("</script>");

		return null;
	}

	@RequestMapping("/loadsuggest")
	public String loadWokeJsp(HttpServletRequest request,
			HttpServletResponse response, String code) throws IOException, ServletException {
		String openid = WeixinUtil.getOpenid(code);
		System.out.println("--------");
		request.setAttribute("openid", openid);
		User DataBaseUser = userService.findByOpenid(openid);
		// return "redirect:/work/showmsg?openid="+openid;
		if (DataBaseUser == null) {
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('请完善个人信息后再提交相关业务信息！');");
			// out.println("history.back();");
			out.println("</script>");
			System.out.println("跳转完善信息页面");
			
			
			return "forward:/user/loadInfo?openid="+openid;
		} else {
			request.setAttribute("openid", openid);
			return "/jsp/suggest_info";
		}
	}
}
