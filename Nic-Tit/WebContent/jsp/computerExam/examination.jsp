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
    <title>计算机等级考试报名</title>
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
/* .point{
    padding: ;
} */
.content form input:not(:nth-child(6)){
    /* border: 0;
    border-bottom: 1px solid #c3c3c5; */
}
.content form{
    width: 100%;
    height: 35.21%;
}
.message{
    background: #ffffff;
    padding:2% 5% 2% 5%;
    position: relative;
}
.message input{
    width: 100%;
     margin-top:3%;
    margin-bottom:3%;
    padding: 2% 0 2% 0%;
   
    font-size: 0.875em;
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
    font-size: 0.875em;
    font-family: "Microsoft YaHei";
}
select{
    width: 100%;
     padding: 2% 0 2% 0%;
    margin: 2% 0%;
    font-size: 1em;
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
    font-size: 1em;
    font-family: "Microsoft YaHei";
}
form .agree input[type="checkbox"] :default{
    outline: 5px solid #21a9f5;
}

</style>

</head>
<body  style="font-size: 60px;">
    <div class="register">
        <div>
        	<img  alt="用户须完善信息以激活微校卡" src="${pageContext.request.contextPath }/img/user.jpg" width="100%" height="439px" >
        </div>
        <div class="content">
            <div class="point" >
                <span style="font-size:40px; margin-left: 50px;">用户须正确完善信息以激活微校卡</span>
            </div>
            <form action="${pageContext.request.contextPath }/student/identify" method="post">
                <div class="message">
                    <table style="width: 100%; font-size: 40px;">
                    	<tr style="display: none;">
							<td style="width: 25%; "><input type="text" value="${state}" name="state" /></td>
							<td></td>
						</tr>
	                	<tr>
	                		<td style="width:25%;"><label>姓名：</label></td>
	                	</tr>
	                	<tr>
	                		<td>
	                				<input type="text" placeholder="请输入姓名"  name="name" style="margin-top:2%;border:1px solid #535353;"/>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td style="width:25%;"><label>学号/工号：</label></td>
	                		<!-- <select name="type" id="sex">
								<option value="男">学生</option>
								<option value="女">教职工</option>
							</select> -->
	                	</tr>
	                	
	                	<tr>
	                		<td>
	                				<input type="text" placeholder="请输入学号/工号"  name="number" style="margin-top:2%;border:1px solid #535353;"/>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td> <label>联系方式：</label></td>
	                	</tr>
	                	<tr>
	                		<td>
	                			<input type=tel placeholder="请输入联系方式" name="telephone" style="border:1px solid #535353;"/>
	                		</td>
	                	</tr>
	                	
                		<!-- <tr>
                		<td> <label>身份证号：</label></td>
                		</tr>
                	<tr>
						<td >
								<input type=tel placeholder="请输入身份证号" name="ID" style="border:1px solid #535353;"/>
                    	</td>
					</tr> -->
					
                  </table>
                </div>
                <button id=btn-submit class="submit" type="submit">提交信息</button>
            </form>
        </div>
    </div>
</body>
</html>