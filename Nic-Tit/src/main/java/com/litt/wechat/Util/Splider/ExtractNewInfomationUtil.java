package com.litt.wechat.Util.Splider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.litt.nic.entity.Information;

public class ExtractNewInfomationUtil {
	public static List<Information> extract(RuleUtil ruleUtil) throws Exception {
		// 进行对rule的必要校验
		validateRule(ruleUtil);

		List<Information> datas = new ArrayList<Information>();
		Information data = null;
		try {
			/**
			 * 解析ruleUtil
			 */
			String url = ruleUtil.getUrl();
			String[] params = ruleUtil.getParams();
			String[] values = ruleUtil.getValues();
			String resultTagName = ruleUtil.getResultTagName();
			int type = ruleUtil.getType();
			int requestType = ruleUtil.getRequestMoethod();

			Connection conn = Jsoup.connect(url);
			// 设置查询参数

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			switch (requestType) {
			case RuleUtil.GET:
				doc = conn.timeout(100000).get();
				break;
			case RuleUtil.POST:
				doc = conn.timeout(100000).post();
				break;
			}

			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
			case RuleUtil.CLASS:
				results = doc.getElementsByClass(resultTagName);
				break;
			case RuleUtil.ID:
				Element result = doc.getElementById(resultTagName);
				results.add(result);
				break;
			case RuleUtil.SELECTION:
				results = doc.select(resultTagName);
				break;
			default:
				// 当resultTagName为空时默认去body标签
				if (RuleUtil.isEmpty(resultTagName)) {
					results = doc.getElementsByTag("body");
				}
			}
			// 打印页面元素
			// System.out.println(results);

			for (Element result : results) {
				Elements times = result
						.getElementsByClass("timestyle930566043_18579");
				Elements links = result.getElementsByTag("a");
				for (int i = 0; i < 3; i++) {
					links.remove(links.size() - 1);
				}
				int i = 0;
				for (Element link : links) {
					// 必要的筛选
					// 连接
					String linkHref = "http://wlzx.tit.edu.cn"
							+ link.attr("href");
					// 标题
					String linkText = link.text();

					data = new Information();
					data.setInformationLink(linkHref);
					data.setInformationTitle(linkText);
					String time = times.get(i).text();
					// System.out.println(time.replaceAll("/", "-"));
					// String转Date(英文时间如：Jul 23 00:00:00 CST 2017)
					Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
							.parse(time.replaceAll("/", "-"));
					data.setInformationTime(date);

					datas.add(data);
					i++;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datas;
	}

	public static void validateRule(RuleUtil ruleUtil) throws Exception {
		String url = ruleUtil.getUrl();
		if (RuleUtil.isEmpty(url)) {
			throw new Exception("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new Exception("url的格式不正确！");
		}

		if (ruleUtil.getParams() != null && ruleUtil.getValues() != null) {
			if (ruleUtil.getParams().length != ruleUtil.getValues().length) {
				throw new Exception("参数的键值对个数不匹配！");
			}
		}

	}
}
