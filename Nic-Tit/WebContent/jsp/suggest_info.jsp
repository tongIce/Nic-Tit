<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta charset="UTF-8"> 
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
 <meta content="telephone=no" name="format-detection"> 
<html>
<head>
<base href="<%=basePath%>">
<title>我要留言</title>
<style type="text/css">
body{
    font-size: 1em;
    font-family: "Microsoft YaHei";
    color: #535353;
    box-sizing: border-box;
}
*{
    margin: 0;
    padding: 0;
}
a{
    text-decoration: none;
    color:#374782;
}
input{
    outline: none;
}


body{
    background: #efeff4;
    width: 100%;
    height: 100%;
}
.regTop{
    width: 100%;
    padding:8% 0 6%;
    background: #50b4f9;
    text-align: center;
    color: #ffffff;
    position: relative;
}
.back{
    position: absolute;
    left: 5%;
    top: 50%;
    color: #ffffff;
}
.point{
    padding: 6% 5%;
}
.content form input:not(:nth-child(6)){
  /*   border: 0; */
   /*  border-bottom: 1px solid #c3c3c5; */
}
.content form{
    width: 100%;
    height: 35.21%;
}
.message{
    background: #ffffff;
    padding:2% 5% 0 5%;
    position: relative;
}
.message input{
    width: 100%;
    padding: 2% 0 2% 0%;
    margin-top:4%;
    margin-bottom:4%;
    font-size: 40px;
    font-family: "Microsoft YaHei";
}
.message .icons b{
    position: absolute;
    width: 18%;
    height: 4%;
    top: 7%;
    left: 7%;
}
.message .icons b img{
    width: 100%;
}
.message .icons b:nth-child(2){
    width: 5%;
    top: 26%;
    left: 6%;
}
.message .icons b:nth-child(3){
    top: 43%;
}
.message .icons b:nth-child(4){
    top: 61%;
}
.code{
    position: absolute;
    top: 60%;
    right:10%;
    color: #21a9f5;
    font-size: 40px;;
    font-family: "Microsoft YaHei";
}
select{
    width: 55%;
    padding: 3% 2%;
    margin: 8% 5%;
    font-size: 50px;
    color: #909093;
    border: solid 1px #909093;
    font-family: "Microsoft YaHei";
}
.submit{
    width: 84%;
    margin: 4% 8%;
    background: #21a9f5;
    color: #ffffff;
    border: 0;
    padding: 2% 0;
    font-size: 20px;
    font-family: "Microsoft YaHei";
}
form .agree input[type="checkbox"] :default{
    outline: 5px solid #21a9f5;
}

</style>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body style="font-size: 25px;">
	 <div class="register">
		<div class="regTop">
			<span>发布留言信息</span>
		</div>
		<div class="content">
			<div class="point">
				<span style="font-size: 16px;">用户须完善信息之后才能发布留言信息。</span>
			</div>
			<form action="${pageContext.request.contextPath }/suggest/addsuggest"
				method="post">
				<div class="message" style="height: 180px;">
					<table style="width: 100%; font-size: 40px;">
					<tr style="display:none;">
							<td style="width: 30%; "><input type="text" value="${openid}"
								name="openid" /></td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 30%;font-size: 14px"><label>留言主题：</label></td></tr>
							<tr>
							<td><div><input type="text" placeholder="请输入留言主题"
								name="title" style="border:1px solid #535353;font-size:12px"/></div></td>
						</tr>
						<tr>
							<td><label style="font-size: 14px;">具体内容：</label></td></tr>
							<tr>
							<td style="padding-top: 5%;">
								<textarea class="" type="text" placeholder="请输入具体内容"
								name="content" style="height: 60px;width: 100%;  font-size:12px;  margin-bottom: 10%;border:1px solid #535353; padding-top: 2%;  "></textarea>
							</td>
						</tr>
					</table>
				</div>
				<button class="submit" type="submit">发布</button>
			</form>
		</div>
	</div> 
</body>																				
</html>