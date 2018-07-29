<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>身份验证.....</title>
		<style type="text/css">
			body{ margin:0; padding:80px; background:#efefef; font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif; }
			h1{ font-size:35px; margin-bottom:35px; }
		</style>
		<script language='javascript'>
		 document.location = 'https://weixiao.qq.com/apps/identity/login-callback?state=${state}' 
		</script> 
	</head>
	<body>
				<a href="http://weixiao.qq.com/apps/identity/login-callback?state=${state}">
				<font size="40px">进行身份验证</font>
				</a>
				<div id="">
				</div>
	</body>
</html>