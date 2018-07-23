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
<title>关于我们</title>
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
			<div class="news_t" align="center">太原工业学院网络与信息中心维保业务集中管理平台</div>
			<div style="font-size: 16px;margin-top:5%;"> 
				<span>2017-10-16</span></br>
				<span>太原工业学院网络与信息中心</span>
			</div>
			<hr/>
		<div id="content" class="news" style="margin:10px;margin-top5%; font-size:35px;line-height:80px;padding-bottom:55px;">
			<p align="center" style="margin: 8% auto;">
				<img alt="暂时没有图片" src="${pageContext.request.contextPath }/images/aboutme.jpg" width="100%">
			
			<p style="text-indent:2em;">太原工业学院网络与信息中心微信公众平台基于中心主要职能工作，
				采用信息化手段完成中心与各个相关职能部门、系部的对接。从而实现信息上传下达的高效畅通，为以服务为核心的中心建设提供平台支持。
			</p>
			
		</div>
		<hr>
		<div style="margin-top: 5%;">
			<p style="text-indent:2em;font-size:20px; word-wrap:break-word; word-break:break-all;">扫描二维码，为你提供方便快捷的报修方式</p>
			<p align="center" style="margin: 8% auto;">
				<img alt="暂时没有图片" src="${pageContext.request.contextPath }/images/QRcode.jpg" style="height: 550px; width: 550px;">
			
			
		</div>
	</div> 
</body>
</html>