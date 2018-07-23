<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
    <title>关于我们</title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/pintuer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/admin.css">

</head>
<body  >
   
   <div style="float: left;">
   <img alt="" src="${pageContext.request.contextPath }/images/download/downloadFromWeixin/${pic}" style="width: 400px;height: 500px;">  
   </div>
   <div class="label" style="float: left; margin-left: 5%;margin-top: 1%;width: 500px;height:550px;">
   		  
          <label style="font-size: 30px;">描述：</label>
         <div style="width: 100%;border: 1px solid #03b6fd; margin-top: 3%;margin-bottom: 5%;" >
          <p style=" height:100px;width: 500px; margin: 15px;">${note}</p>
         </div>
         <label style="font-size: 30px; margin-top: 4%; ">联系人：</label>
         <div style="width: 30%;border: 1px solid #03b6fd; margin-top: 3%;margin-bottom: 5%;" >
          <p style="margin: 5%;text-align: center;" readonly="readonly">${person}</p>
         </div>
         <label style="font-size: 30px; margin-top: 4%; ">所属部门：</label>
         <div style="width: 30%;border: 1px solid #03b6fd; margin-top: 3%;margin-bottom: 5%;" >
          <p style="margin: 5%;text-align: center;" readonly="readonly">${department}</p>
         </div>
         <label style="font-size: 30px; margin-top: 4%; margin-bottom: 5%;">联系方式：</label>
         <div style="width: 30%;border: 1px solid #03b6fd; margin-top: 3%; " >
          <p style="margin: 5%;text-align: center;" readonly="readonly">${phone}</p>
         </div>
         
    </div>
        
</body>
</html>