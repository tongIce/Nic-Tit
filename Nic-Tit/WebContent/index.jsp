<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>网络信息管理中心--首页</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath }/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />
    网络信息管理中心维保业务集中管理平台</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="${pageContext.request.contextPath }/manager/toin" target="_blank">
  <span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;
  <a class="button button-little bg-blue" href="${pageContext.request.contextPath }/login.jsp">
  <span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-pencil-square-o"></span>业务办理</h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath }/feedback/unfinishedlist" target="right"><span class="icon-caret-right"></span>待处理业务</a></li>
    <li><a href="${pageContext.request.contextPath }/feedback/finishedlist" target="right"><span class="icon-caret-right"></span>已完成业务</a></li>
    <li><a href="${pageContext.request.contextPath }/shownews/toshownews" target="right"><span class="icon-caret-right"></span>发布消息</a></li>
  	<li><a href="${pageContext.request.contextPath }/suggest/toshowsuggest" target="right"><span class="icon-caret-right"></span>留言管理</a></li> 
 
  </ul>   
  <h2><span class="icon-user"></span>相关报表</h2>
  <ul>
   <%--  <li><a href="${pageContext.request.contextPath}/serviceDock/showList" target="right"><span class="icon-caret-right"></span>查看历史记录</a></li> --%>
  </ul>  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="{:U('Index/info')}" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/feedback/unfinishedlist" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>