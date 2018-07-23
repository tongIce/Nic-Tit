package com.litt.wechat.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.nic.entity.Article;
import com.litt.nic.entity.Information;
import com.litt.nic.service.impl.ArticleServiceImpl;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Splider.ExtractNewInfomationUtil;
import com.litt.wechat.Util.Splider.ExtractUtil;
import com.litt.wechat.Util.Splider.RuleUtil;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

	@Autowired
	private ArticleServiceImpl articleService;

	private Article article;

	@RequestMapping(value = "/extract")
	public String extractInformation(HttpServletRequest request,
			HttpServletResponse response) {
		List<Information> extracts = null;

		RuleUtil rule = new RuleUtil(
				PropertiesReadUtils.getWechatString("news"), new String[] {},
				new String[] {}, "winstyle930566043_18579", RuleUtil.CLASS,
				RuleUtil.GET);
		try {
			extracts = ExtractNewInfomationUtil.extract(rule);
			// rule.print(extracts);//后台打印测试
			System.out.println("连接地址：" + extracts.get(0).getInformationLink());
			System.out.println("标题:" + extracts.get(0).getInformationTitle());
			System.out.println("时间：" + extracts.get(0).getInformationTime());

		} catch (Exception e) {
			System.out.println("参数异常");
			return "/jsp/error/newsnull";
		}

		RuleUtil ruleArtical = new RuleUtil(extracts.get(0)
				.getInformationLink(), new String[] {}, new String[] {},
				"winstyle930566043_18580", RuleUtil.CLASS, RuleUtil.GET);
		try {
			String extractsnews = ExtractUtil.extract(ruleArtical);
			request.setAttribute("str", extractsnews);
			System.out.println(extractsnews);
			// return extracts;
		} catch (Exception e) {
			System.out.println("参数异常");
		}

		return "/jsp/hotnews";
	}

	@RequestMapping(value = "/shownotice")
	public String showNotice(HttpServletRequest request,
			HttpServletResponse response) {
		int maxid = 0;
		try {
			maxid = articleService.findMaxId();
			article = articleService.findById(maxid);
			request.setAttribute("article", article);
		} catch (Exception e) {
			System.out.println("maxid为空");
			response.setContentType("text/html; charset=UTF-8"); // 转码
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			out.flush();
			out.println("<script>");
			out.println("alert('暂无公告');");
			out.println("history.back();");
			out.println("</script>");
			return "/jsp/error/feedbacknull.jsp";
		}

		return "/jsp/shownotice";

	}
}
