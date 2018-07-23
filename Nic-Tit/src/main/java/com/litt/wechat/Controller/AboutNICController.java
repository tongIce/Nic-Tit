package com.litt.wechat.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Splider.ExtractUtil;
import com.litt.wechat.Util.Splider.RuleUtil;

@Controller
@RequestMapping(value = "/aboutNIC")
public class AboutNICController {
	@RequestMapping(value = "/extract")
	public String extractInformation(HttpServletRequest request,
			HttpServletResponse response) {
		RuleUtil rule = new RuleUtil(
				PropertiesReadUtils.getWechatString("aboutNIC"),
				new String[] {}, new String[] {},
				PropertiesReadUtils.getWechatString("aboutNICclass"),
				RuleUtil.CLASS, RuleUtil.GET);
		try {
			String extracts = ExtractUtil.extract(rule);
			request.setAttribute("str", extracts);
			System.out.println(extracts);
			// return extracts;
		} catch (Exception e) {
			System.out.println("参数异常");
		}
		// return null;
		return "/jsp/aboutme";
	}
}
