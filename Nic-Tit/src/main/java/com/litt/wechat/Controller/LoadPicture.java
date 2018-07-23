package com.litt.wechat.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/load")
public class LoadPicture {

	@RequestMapping("/pictur1e")
	public void loadpicture(HttpServletRequest request,
			HttpServletResponse response, String pName) throws IOException {
		// String
		// pictureNmae="150210970713612799761-1350-47fc-9988-b6d7e1ba9968.jpg";
		String pictureNmae = pName;
		String path = System.getProperty("catalina.home") + "/webapps"
				+ "/download/" + "downloadFromWeixin/";
		// 读取本地图片输入流
		FileInputStream inputStream = new FileInputStream(path + pictureNmae);
		int i = inputStream.available();
		// byte数组用于存放图片字节数据
		byte[] buff = new byte[i];
		inputStream.read(buff);
		// 记得关闭输入流
		inputStream.close();
		// 设置发送到客户端的响应内容类型
		// response.setContentType("image/*");
		response.setHeader("Content-type", "image/png");
		OutputStream out = response.getOutputStream();
		out.write(buff);
		// 关闭响应输出流
		out.close();
		// return "/jsp/loadpicture";
	}
}
