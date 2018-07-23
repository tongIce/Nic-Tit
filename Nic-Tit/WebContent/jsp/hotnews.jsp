<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>最新消息</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/register.css"/>

</head>
<body  >
    
    <div style=" border:3px solid #96c2f1;background:#eff7ff; margin: 50px; padding: 45px;">${str}</div>
    
    
    
</body>
</html>