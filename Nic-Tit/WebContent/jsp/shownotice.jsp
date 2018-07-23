<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>最新公告</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/common.css" />
	<link href="${pageContext.request.contextPath }/css/main.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/register.css" />
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>

</head>
<body>
		<div class="content">
			<div class="news_t" align="center">${article.title}</div>
			<div style="font-size: 20px; margin-top:5%;"> 
				<span>${article.author}</span>
			</div>
			<hr/>
	<div id="content" class="news" style="margin:10px;font-size:40px;line-height:80px;padding-bottom:55px;">
		<p style="text-indent:2em;">${article.digest}　</p>
		<p align="center" style="margin: 8% auto;">
			<img alt="暂时没有图片" src="data:image/jpeg;base64,${article.picurl}" width="100%"
			 onerror="this.src='${pageContext.request.contextPath }/images/newsnull.jpg;this.onerror=null'"
			/>
			
		<p style="text-indent:2em;word-wrap:break-word; word-break:break-all;">${article.content}　</p>
		
		</div>
	</div> 
</body>
</html>