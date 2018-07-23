package com.litt.wechat.Util.Menu;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.http.ParseException;

import com.litt.wechat.Menu.Button;
import com.litt.wechat.Menu.ClickButton;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Menu.ViewButton;
import com.litt.wechat.Util.HttpUtils;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 监听，向微信服务器发送代码，初始化按钮。
 * @author Lenovo
 *
 */
public class WebContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent paramServletContextEvent) {
		//建立初始化菜单
		Menu menu = new Menu();
		ClickButton button12 = new ClickButton();
		button12.setName("服务热线");
		button12.setType("click");
		button12.setKey("12");
		
		ClickButton button13 = new ClickButton();
		button13.setName("上网指南");
		button13.setType("click");
		button13.setKey("13");
		
		ClickButton button14 = new ClickButton();
		button14.setName("报修帮助");
		button14.setType("click");
		button14.setKey("14");
		
		
		ViewButton button11 = new ViewButton();
		button11.setName("最新公告");
		button11.setType("view");
		button11.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/news/shownotice");
		
		ViewButton button21 = new ViewButton();
		button21.setName("完善个人信息");
		button21.setType("view");
		String Info_URL = PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/user/load";
		//授权限制....................
		button21.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL", Info_URL));
		
		
		ViewButton button22 = new ViewButton();
		button22.setName("提交业务");
		button22.setType("view");
		String commitUrl = PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/work/loadWork";
		button22.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL",
				commitUrl));
		
		ViewButton button23 = new ViewButton();
		button23.setName("查看反馈信息");
		button23.setType("view");
		String checkUrl = PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/work/loadCheck";
		button23.setUrl(WeixinUtil.getOAuth2()
				.replace("REDIRECT_URL", checkUrl));
		
		/*ViewButton button25 = new ViewButton();
		button25.setName("共享反馈信息");
		button25.setType("view");
		String shareCheckUrl = PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/handle/shareCheck";
		button25.setUrl(WeixinUtil.getOAuth2()
				.replace("REDIRECT_URL", shareCheckUrl));*/
		
		
		ViewButton button24 = new ViewButton();
		button24.setName("我要留言");
		button24.setType("view");
		String suggestUrl = PropertiesReadUtils
				.getWechatString("rootdirectory") + "/suggest/loadsuggest";
		button24.setUrl(WeixinUtil.getOAuth2().replace("REDIRECT_URL",
				suggestUrl));
		ViewButton button3 = new ViewButton();
		button3.setName("关于我们");
		button3.setType("view");
		button3.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
				+ "/jsp/newaboutme.jsp");

		Button button2 = new Button();
		button2.setName("业务对接");
		button2.setSub_button(new Button[] { button21, button22, button23,
				button24 });

		Button button1 = new Button();
		button1.setName("数字校园");
		button1.setSub_button(new Button[] { button11, button12,button13,button14 });
		//为菜单，添加button按钮。
		menu.setButton(new Button[] { button2, button3 });
		String jsonMenu = JSONObject.fromObject(menu).toString();
		//初始化菜单栏
		String url;
		try {
			url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
					+ WeixinUtil.getAccessToken().getAccessToken();
			String rs = HttpUtils.sendPostBuffer(url, jsonMenu.toString());
			System.out.println(rs);
		} catch (ParseException | IOException e1) {
			System.out.println("初始化微信菜单栏失败");
			System.out.println(e1.getMessage());
		}
		
	}
	@Override
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		// TODO Auto-generated method stub
		
	}
}
