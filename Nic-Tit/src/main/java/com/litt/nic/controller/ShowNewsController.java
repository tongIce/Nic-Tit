package com.litt.nic.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import com.litt.nic.entity.Article;
import com.litt.nic.service.IArticleService;

@Controller
@RequestMapping(value = "/shownews")
public class ShowNewsController {
	// private static Logger logger = Logger.getLogger(SendToAll.class);

	@Autowired
	private IArticleService articleService;

	/**
	 * 跳转到发布新消息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toshownews")
	public String toshownews() {

		return "/WEB-INF/views/work/shownews";

	}

	/**
	 * 添加最新消息
	 * @param FilePath
	 * @throws Exception
	 * @throws ParseException
	 */
	@RequestMapping(value = "/addnews")
	public String addnews(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") MultipartFile file) throws ParseException,
			Exception {

		InputStream is = file.getInputStream();
		// 保存图片路径
		String targetFile = System.getProperty("catalina.home") + "/webapps"
				+ "/download" + "/ManageUpload/" + UUID.randomUUID().toString()
				+ ".jpg";
		System.out.println("targetFile:" + targetFile);

		try {
			OutputStream out = new FileOutputStream(targetFile);
			byte[] bytes = new byte[1024];
			int len = -1;
			while ((len = is.read(bytes)) != -1) {
				out.write(bytes, 0, len);
			}
			is.close();
			out.close();
		} catch (Exception e) {
			System.out.println("保存图片失败");
		}
		System.out.println("保存图片成功");
		String title = request.getParameter("title");
		//
		String content = request.getParameter("content");
		// digest 图文消息的描述，如本字段为空，则默认抓取正文前64个字
		String desc = request.getParameter("description");
		System.out.println(title + ",   " + content + " ," + desc);
		Article articlePojo = new Article();
		articlePojo.setAuthor("太原工业网络与信息中心处");
		articlePojo.setTitle(title);
		articlePojo.setContent(content);
		articlePojo.setDigest(desc);
		String pathpic = GetImageStr(targetFile);
		System.out.println("========" + pathpic);
		articlePojo.setPicurl(pathpic);
		articleService.save(articlePojo);

		// 进入微信群发图文消息controller
		return "redirect:/shownews/toshownews";
	}

	// 图片转化成base64字符串
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;

		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

}
