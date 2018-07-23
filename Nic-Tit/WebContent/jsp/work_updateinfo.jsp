<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>提交业务信息</title>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
window.onload = function() {
    //     if (isWeiXin5() == false) {
    //           alert("您的微信版本低于5.0，无法使用微信支付功能，请先升级！");
    //         }
    config();
};

function config() {
	$.ajax({
		type : 'post',
		url : "http://xxzx.tit.edu.cn/Nic-Tit/work/config",
		data : {'url' :location.href.split('#')[0]},
		dataType : 'json',
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			var obj = eval(data[0]);
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : obj.appId, // 必填，公众号的唯一标识
				timestamp : obj.timestamp, // 必填，生成签名的时间戳
				nonceStr : obj.nonceStr, // 必填，生成签名的随机串
				signature : obj.signature,// 必填，签名，见附录1
				jsApiList : [ 'chooseImage', 'uploadImage',
						'downloadImage','checkJsApi' ]
			});
		}
	});
}
function isWeiXin5() {
    var ua = window.navigator.userAgent.toLowerCase();
    var reg = /MicroMessenger\/[5-9]/i;
    return reg.test(ua);
}
 
function update() {
	
	document.getElementById("submitform").action="${pageContext.request.contextPath }/handle/update";
	document.getElementById("submitform").submit();
}
function withdraw() {
	document.getElementById("submitform").action="${pageContext.request.contextPath }/handle/withdraw";
	document.getElementById("submitform").submit();
}


//异步提交上传照片；
function takePicture(){
    wx.chooseImage({
        count: 1, // 默认9
        sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
        success: function (res) {
            var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片

            wx.uploadImage({
                localId: localIds.toString(), // 需要上传的图片的本地ID，由chooseImage接口获得
                isShowProgressTips: 1, // 默认为1，显示进度提示
                success: function (res) {
                    var mediaId = res.serverId; // 返回图片的服务器端ID，即mediaId
                    
                    //将获取到的 mediaId 传入后台 方法savePicture
                    $.ajax({
						   type: "POST",
						   url: "<%=request.getContextPath()%>/work/savePicture",//请求的后台地址
						   data: {"mediaId":mediaId},//前台传给后台的参数
						   success: function(filename){//filename:返回值
							  	$("#fileName").val(filename);
							  	var name=$("[id='fileName']").val(); 
						   },
						   error:function(XMLHttpRequest, textStatus, errorThrown) {
						   alert("图片过大，超出限制");  
		                       /* alert(XMLHttpRequest.status);  
		                       alert(XMLHttpRequest.readyState);  
		                       alert(textStatus);   */
		                   }  
						});
						alert("上传成功");
                   // alert(ll);
                   <%--  $.post("<%=request.getContextPath()%>/work/savePicture",{mediaId:mediaId},
                    	function(filename){
                    	alert(filename);
	                        if(filename == 'success'){
	                        	alert('上传成功')
	                        }else{
	                            alert(res.msg)
	                        }
                    }) --%>
                },
                fail: function (res) {
                    alertModal('上传图片失败，请重试')
                }
            }); 
        }
    });
}
//${pageContext.request.contextPath }/work/addwork
</script>
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
    width: 100%;
    padding: 3% 2%;
    margin: 4% 0;
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
    font-size: 20px;
    font-family: "Microsoft YaHei";
}
form .agree input[type="checkbox"] :default{
    outline: 5px solid #21a9f5;
}

</style>
</head>
<body style="font-size: 25px;">
								
	 <div class="register">
		<div class="regTop">
			<span>提交业务信息</span>
		</div>
		<div class="content" style="padding-bottom: 5%;">
			<div class="point">
				<span style="font-size: 16px;">用户须完善信息之后才能提交业务信息！</span>
			</div>
			<form id="submitform" action="${pageContext.request.contextPath }/work/updatework" method="post">
				<div class="message" >
				  <table style="width: 100%; margin-left: 0%;  font-size: 40px;padding-bottom: -20px;">
						<tr style="display: none;">
								<td style="width: 100%;">
									<input  type="text" value="${openid}" name="openid" />
								</td>
						</tr>
						<tr style="display: none;">
								<td style="width: 100%;">
									<input  type="text" value="${support.techsupportId}" name="techsupportId" />
								</td>
						</tr>
						<tr style="margin-top: 4%;">
							<td style="width: 100%;font-size: 14px; ">业务类型 ：</td>
						</tr>
						<tr>
							<td style="font-size: 14px;">
								<select name="worktype">
								<option  value="0">选择业务类型</option>
								<option <c:if test="${worktype=='技术支持'}">selected</c:if>  value="技术支持">技术支持</option>
								<option <c:if test="${worktype=='设备报修'}">selected</c:if>  value="设备报修">设备报修</option>
								<option <c:if test="${worktype=='日常运维'}">selected</c:if>  value="日常运维">日常运维</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><label style="font-size: 14px;">设备名称：</label></td>
						</tr>
						<tr>
							<td>
	                			<c:if test="${empty support}">
	                				<input type="text" placeholder="请输入设备名称"  name="devicename" style="width: 100%;border:1px solid #708090;font-size: 12px;"/>
	                			</c:if>
	                			<c:if test="${!empty support}">
	                				<input name="devicename" style="width: 100%;border:1px solid #708090;font-size: 12px;" type="text" value="${support.techsupportDevicename }" placeholder="${support.techsupportDevicename }"   />
	                			</c:if>
	                		</td>
						</tr>
						<tr>
							<td><label style="font-size: 14px;">具体地点：</label></td>
						</tr>
						<tr>
							<td>
	                			<c:if test="${empty support}">
	                				<input type="text" placeholder="请输入具体地点"  name="location" style="width: 100%;border:1px solid #708090;font-size: 12px;" />
	                			</c:if>
	                			<c:if test="${!empty support}">
	                				<input name="location" style="width: 100%;border:1px solid #708090;font-size: 12px;" type="text" value="${support.techsupportLocation }" placeholder="${support.techsupportLocation }"   />
	                			</c:if>
	                		</td>
							<!-- <td>
								<input class="input" style="width: 100%;border:1px solid #708090;font-size: 12px;" class="input" type=text placeholder="请输入具体地点" name="location" />
							</td> -->
						</tr>
						<tr>
							<td><label style="font-size: 14px;">具体描述：</label></td>
						</tr>
						<tr>
							<td style="font-size: 1em;">
	                			<c:if test="${empty support}">
	                				<div style="margin-top: 3%;"></div>
									<textarea  type="text" placeholder="请输入具体描述"
								name="description" style="height: 60px; border:1px solid #708090;font-weight:bold; font-size: 13px; width: 100%;padding-top: 2%;"></textarea>
	                			</c:if>
	                			<c:if test="${!empty support}">
									<div style="margin-top: 3%;"></div>
									<textarea style="height: 60px; border:1px solid #708090; font-size: 13px; width: 100%;padding-top: 2%;" type="text"   placeholder="${support.techsupportDescribe }"
								name="description" > ${support.techsupportDescribe }</textarea>
	                			</c:if>
	                		</td>
						</tr>
						<tr><td style="display: none;"><input  name="openid" value="${openid }"/></td></tr>
						<tr align="center" style="margin-top: 14%;">
							<td>
								<div style=" margin-top: 4%;margin：0 auto; height: 80px; ">
									<input  type="button" value="点击上传图片" style="width:50%; margin-bottom: 2%;  background-color:#21a9f5; padding: 2% 0 2% 0%;font-size: 14px;color:#ffffff; font-family: 'Microsoft YaHei'; "
										onclick="takePicture()" /></br>
										<span style="color:red;margin-top: -2%;font-size: 14px;">(请选择小于2M的图片)</span>
								</div>	
							</td>
						</tr>
						<tr style="padding-bottom: 2%;">
							<td><input type="hidden" id="fileName"  name="filename" /></td>
						</tr> 
				  </table>
				</div>
				<button class="submit" type="submit">提交信息</button>
			</form>
		</div>
	</div> 
	
</body>
</html>