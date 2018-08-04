<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课表查询</title>
</head>
<body>
       <form  action="/register" method="post">
    用户名<input type="text" name="username" />
    <br/>
    密码<input type="password" name="password"/>
    <br/>
    性别:  男<input type="radio" checked="checked" name="sex" value="M"/>
    女<input type="radio" name="sex"  value="F" /><br>
    <input type="submit" name="注册" value="注册">
</form>
</body>
</html>