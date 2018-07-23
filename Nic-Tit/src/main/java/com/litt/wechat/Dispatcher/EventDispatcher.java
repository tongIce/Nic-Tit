package com.litt.wechat.Dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import com.litt.nic.entity.User;
import com.litt.wechat.Menu.Menu;
import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.ImageMessage;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Message.resp.TextMessage;
import com.litt.wechat.Util.MessageUtil;
import com.litt.wechat.Util.GetUserInfo.GetUserInfo;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

/**
 *	事件业务分发器
 * @author Lenovo
 *
 */
public class EventDispatcher {
	private static Logger logger = Logger.getLogger(EventDispatcher.class);

	public static String openid;

	public static String processEvent(Map<String, String> map) throws ParseException, IOException {
		// 用户openid
		openid = map.get("FromUserName"); 
		System.out.println("openid5:" + openid);
		// 公众号原始ID
		String mpid = map.get("ToUserName"); 
		// 图片消息
		ImageMessage imgmsg = new ImageMessage();
		imgmsg.setToUserName(openid);
		imgmsg.setFromUserName(mpid);
		imgmsg.setCreateTime(new Date().getTime());
		imgmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_Image);

		// 普通文本消息
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

		// 对图文消息
		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		//主动发送http请求获取微信用户信息,接口访问凭证有时间限制。
		User userinfo = GetUserInfo.getUserInfo(WeixinUtil.getAccessToken().getAccessToken(), openid);
		
		System.out.println("-----------判断这是什么类型的请求："+map.get("Event"));
		if(map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)){
			//扫码推动事件
			return MessageUtil.newsMessageToXml(null);
		}
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			// 关注事件
			Article article = new Article();
			article.setDigest("欢迎使用太原工业学院网络信息管理中心业务对接系统"); // 图文消息的描述
			article.setPicUrl(userinfo.getUserHeadimgurl()); // 图文消息图片地址
			article.setTitle("尊敬的：" + userinfo.getUserNickname()+ ",您好！请您先完善您的个人信息，方便提交业务信息。");
			// 图文消息标题
			article.setUrl(PropertiesReadUtils.getWechatString("rootdirectory")
					+ "/user/loadInfo?openid=" + openid); // 图文url链接

			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			return MessageUtil.newsMessageToXml(newmsg);
			
		}
		//if(map.get("Event").equals(MessageUtil.))
		if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消关注事件
			System.out.println("==============这是取消关注事件！");
		}
		if ((map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK))&&map.get("EventKey").equals("12")) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
			
			Article article = new Article();
			// 图文消息的描述
			article.setDigest("版权所有：太原工业学院网络与信息中心");
			// 图文url链接
			article.setPicUrl(PropertiesReadUtils
					.getWechatString("rootdirectory")
					+ PropertiesReadUtils.getWechatString("picurl01"));
			// 图文消息标题
			article.setTitle("信息化服务热线"); 
			article.setContent_source_url("owaenfcweifciaweojfc");
			article.setContent("为提高信息化办公室、现代教育技术中心服务响应速度，提升服务水平，树立热情服务、周到服务、优质服务的形象，"
					+ "网络与信息中心开通服务热线：0351-3567618，为全校师生提供统一、便捷的服务通道。");
			// 图文消息图片地址
			article.setUrl("http://url.cn/55vLmEM");
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			//将图文消息转换为XML返回给微信服务器。
			return MessageUtil.newsMessageToXml(newmsg);
			
		}
		if ((map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK))) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
			
			Article article = new Article();
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(0);
			newmsg.setArticles(null);
			//将图文消息转换为XML返回给微信服务器。
			return MessageUtil.newsMessageToXml(null);
			
		}
		if ((map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK))&&map.get("EventKey").equals("14")) { // 自定义菜单点击事件
			System.out.println("==============这是自定义菜单点击事件！");
			
			Article article = new Article();
			// 图文消息的描述
			article.setDigest("版权所有：太原工业学院网络与信息中心");
			// 图文url链接
			article.setPicUrl(PropertiesReadUtils
					.getWechatString("rootdirectory")
					+ PropertiesReadUtils.getWechatString("picurl03"));
			// 图文消息标题
			article.setTitle("报修帮助");
			article.setContent("为进一步高效处理校园网用户的网络报修故障，网络与信息中心微信公众平台于2018年4月2日正式启用，欢迎大家关注使用！");
			// 图文消息图片地址
			article.setUrl("http://url.cn/50wOXZ4");
			List<Article> list = new ArrayList<Article>();
			list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
			newmsg.setArticleCount(list.size());
			newmsg.setArticles(list);
			//将图文消息转换为XML返回给微信服务器。
			return MessageUtil.newsMessageToXml(newmsg);
			
		}
		return null;
	}
	
}
