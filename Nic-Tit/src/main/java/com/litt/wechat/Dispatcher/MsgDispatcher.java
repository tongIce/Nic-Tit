package com.litt.wechat.Dispatcher;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;

import com.litt.nic.entity.User;
import com.litt.wechat.Message.resp.Article;
import com.litt.wechat.Message.resp.NewsMessage;
import com.litt.wechat.Message.resp.TextMessage;
import com.litt.wechat.Util.MessageUtil;
import com.litt.wechat.Util.GetUserInfo.GetUserInfo;
import com.litt.wechat.Util.Properties.PropertiesReadUtils;
import com.litt.wechat.Util.Token.WeixinUtil;

/**
 * ClassName: MsgDispatcher
 * 
 * @Description: 消息业务处理分发器
 * @date 2016年3月7日 下午4:04:21
 */
public class MsgDispatcher {
	public static String processMessage(Map<String, String> map)
			throws ParseException, IOException {
		String openid = map.get("FromUserName"); // 用户openid
		String mpid = map.get("ToUserName"); // 公众号原始ID

		// 普通文本消息
		TextMessage txtmsg = new TextMessage();
		txtmsg.setToUserName(openid);
		txtmsg.setFromUserName(mpid);
		txtmsg.setCreateTime(new Date().getTime());
		txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		System.out.println("来了哦");

		NewsMessage newmsg = new NewsMessage();
		newmsg.setToUserName(openid);
		newmsg.setFromUserName(mpid);
		newmsg.setCreateTime(new Date().getTime());
		newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		User userinfo = GetUserInfo.getUserInfo(WeixinUtil.getAccessToken()
				.getAccessToken(), openid);
		
		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
			if (map.get("Content").equals("1")) {

				Article article = new Article();
				article.setDigest("设置机静态ip"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/problem.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里，查看设置静态IP详细步骤。");
				// 图文消息标题
				article.setUrl("https://jingyan.baidu.com/article/b907e627e12ceb46e7891c88.html"); // 图文url链接

				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("2")) {
				Article article = new Article();
				article.setDigest("设置路由"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/problem.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里，查看路由器设置的详细步骤。");
				// 图文消息标题
				article.setUrl("https://jingyan.baidu.com/article/a3761b2b6e931c1576f9aabe.html"); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("3")) {
				Article article = new Article();
				article.setDigest("开机无显示"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/problem.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里，查看解决开机无显示的详细步骤。");
				// 图文消息标题
				article.setUrl("https://jingyan.baidu.com/article/fedf073774433735ac897702.html"); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("4")) {
				Article article = new Article();
				article.setDigest("电脑连不上网"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/problem.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里，查看解决电脑连不上网的详细步骤。");
				// 图文消息标题
				article.setUrl("https://jingyan.baidu.com/article/a501d80cf47cc2ec630f5eed.html"); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			} else if (map.get("Content").equals("5")) {
				Article article = new Article();
				article.setDigest("防火墙设置"); // 图文消息的描述
				article.setPicUrl(PropertiesReadUtils
						.getWechatString("rootdirectory")
						+ "/images/problem.jpg"); // 图文消息图片地址
				article.setTitle("尊敬的：" + userinfo.getUserNickname()
						+ ",你好！请点击这里，查看设置防火墙的详细步骤。");
				// 图文消息标题
				article.setUrl("https://jingyan.baidu.com/article/ca41422fe9462c1eae99edb5.html"); // 图文url链接
				List<Article> list = new ArrayList<Article>();
				list.add(article); // 这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				newmsg.setArticleCount(list.size());
				newmsg.setArticles(list);
				return MessageUtil.newsMessageToXml(newmsg);
			}
			System.out.println("==============这是文本消息！" + openid);
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                    "
					+ "回复  '1'  设置静态IP                        "
					+ "回复  '2'  路由器设置                        "
					+ "回复  '3'  开机无显示                        "
					+ "回复  '4'  电脑连不上网                    "
					+ "回复  '5'  防火墙设置                        ");

			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
			System.out.println("==============这是图片消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                    "
					+ "回复  '1'  设置静态IP                        "
					+ "回复  '2'  路由器设置                        "
					+ "回复  '3'  开机无显示                        "
					+ "回复  '4'  电脑连不上网                    "
					+ "回复  '5'  防火墙设置                        ");

			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
			System.out.println("==============这是链接消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                    "
					+ "回复  '1'  设置静态IP                        "
					+ "回复  '2'  路由器设置                        "
					+ "回复  '3'  开机无显示                        "
					+ "回复  '4'  电脑连不上网                    "
					+ "回复  '5'  防火墙设置                        ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
			System.out.println("==============这是位置消息！");
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                    "
					+ "回复  '1'  设置静态IP                        "
					+ "回复  '2'  路由器设置                        "
					+ "回复  '3'  开机无显示                        "
					+ "回复  '4'  电脑连不上网                    "
					+ "回复  '5'  防火墙设置                        ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
			System.out.println("==============这是语音消息！" + openid);
			txtmsg.setContent("你好，这里是太原工业学院网络信息中心微信公众平台！                    "
					+ "回复  '1'  设置静态IP                        "
					+ "回复  '2'  路由器设置                        "
					+ "回复  '3'  开机无显示                        "
					+ "回复  '4'  电脑连不上网                    "
					+ "回复  '5'  防火墙设置                        ");
			return MessageUtil.textMessageToXml(txtmsg);
		}

		return null;
	}
}
