<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=0.7">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>全国计算机等级考试</title>
		<style>
			.conTr{
				height: 22px;
			}
		</style>	
</head>
<body>
	<div style="height: 100%;width: 100%;">
		<div>
			<img alt="" style="width:100%;height: 150px;" src="${pageContext.request.contextPath }/img/exam/exam.jpg">
		</div>
		<c:if test="${  empty count }">
			<h1 align="center">您暂时还没有报计算机等级考试</h1>
			<h1 align="center">请在报名后在查询</h1>
		</c:if>
		<c:if test="${not empty count }">
		<c:if test="${not empty list1 }">
		<!-- 继续判断list1，list2等是否为空，从而进行遍历集合 -->
		<c:forEach items="${list1 }" var="item" varStatus="status">
	    	<table border="2px;"  cellpadding="10px;" cellspacing="0 px;" bordercolor="black" align="center">
	    		<caption style="margin-bottom: 5px; font-size: 20px; color: orange;" >
	    			考生报名登记表
	    		</caption>
	    		<tr class="conTr" align="left" style="margin-top: 10px;">
	    			<th >考试名称</th>
	    			<td colspan="3">(140027)太原工业学院   </td>
	    		</tr>
	    		<tr  class="conTr" align="left">
		    		<th>姓名</th>
		    		<td>  
						${item.exName }	    		
		    	    </td>
		    		<th>报名号</th>
		    		<td>  ${item.exEnterNumber }  </td>
	    		</tr >
		    	<tr  class="conTr" align="left">
		    		<th>性别</th>
		    		<td>   ${item.exSex }   </td>
		    		<th>证件号</th>
		    		<td>  ${item.exIDcard }    </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>民族</th>
		    		<td>   ${item.exNation }  </td>
		    		<th>出生日期</th>
		    		<td>  ${item.bornTostring }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>文化程度</th>
		    		<td>  ${item.exDegree }  </td>
		    		<th>联系电话</th>
		    		<td>    ${item.exTele } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>报考等级及语言</th>
	    			<td colspan="3">  ${item.exType }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>职业</th>
	    			<td colspan="3">  ${item.exProfession } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>地址</th>
	    			<td colspan="3">     </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>备注</th>
	    			<td colspan="3"> ${item.exNote }   </td>
	    		</tr>
	    		
	    	</table>
    	</c:forEach>	
    	</c:if>	
    	
    	<!-- 判断第二个集合 -->
    	<c:if test="${not empty list2 }">
		<!-- 继续判断list1，list2等是否为空，从而进行遍历集合 -->
		<c:forEach items="${list2 }" var="item" varStatus="status">
	    	<table border="2px;"  cellpadding="10px;" cellspacing="0 px;" bordercolor="black" align="center">
	    		<caption style="margin-bottom: 5px; font-size: 20px; color: orange;" >
	    			考生报名登记表
	    		</caption>
	    		<tr class="conTr" align="left" style="margin-top: 10px;">
	    			<th >考试名称</th>
	    			<td colspan="3">(140027)太原工业学院   </td>
	    		</tr>
	    		<tr  class="conTr" align="left">
		    		<th>姓名</th>
		    		<td>  
						${item.exName }	    		
		    	    </td>
		    		<th>报名号</th>
		    		<td>  ${item.exEnterNumber }  </td>
	    		</tr >
		    	<tr  class="conTr" align="left">
		    		<th>性别</th>
		    		<td>   ${item.exSex }   </td>
		    		<th>证件号</th>
		    		<td>  ${item.exIDcard }    </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>民族</th>
		    		<td>   ${item.exNation }  </td>
		    		<th>出生日期</th>
		    		<td>  ${item.bornTostring }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>文化程度</th>
		    		<td>  ${item.exDegree }  </td>
		    		<th>联系电话</th>
		    		<td>    ${item.exTele } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>报考等级及语言</th>
	    			<td colspan="3">  ${item.exType }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>职业</th>
	    			<td colspan="3">  ${item.exProfession } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>地址</th>
	    			<td colspan="3">     </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>备注</th>
	    			<td colspan="3"> ${item.exNote }   </td>
	    		</tr>
	    		
	    	</table>
    	</c:forEach>	
    	</c:if>	
    		<!-- 判断第三个集合 -->
    	<c:if test="${not empty list3 }">
		<!-- 继续判断list1，list2等是否为空，从而进行遍历集合 -->
		<c:forEach items="${list3 }" var="item" varStatus="status">
	    	<table border="2px;"  cellpadding="10px;" cellspacing="0 px;" bordercolor="black" align="center">
	    		<caption style="margin-bottom: 5px; font-size: 20px; color: orange;" >
	    			考生报名登记表
	    		</caption>
	    		<tr class="conTr" align="left" style="margin-top: 10px;">
	    			<th >考试名称</th>
	    			<td colspan="3">(140027)太原工业学院   </td>
	    		</tr>
	    		<tr  class="conTr" align="left">
		    		<th>姓名</th>
		    		<td>  
						${item.exName }	    		
		    	    </td>
		    		<th>报名号</th>
		    		<td>  ${item.exEnterNumber }  </td>
	    		</tr >
		    	<tr  class="conTr" align="left">
		    		<th>性别</th>
		    		<td>   ${item.exSex }   </td>
		    		<th>证件号</th>
		    		<td>  ${item.exIDcard }    </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>民族</th>
		    		<td>   ${item.exNation }  </td>
		    		<th>出生日期</th>
		    		<td>  ${item.bornTostring }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
		    		<th>文化程度</th>
		    		<td>  ${item.exDegree }  </td>
		    		<th>联系电话</th>
		    		<td>    ${item.exTele } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>报考等级及语言</th>
	    			<td colspan="3">  ${item.exType }   </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>职业</th>
	    			<td colspan="3">  ${item.exProfession } </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>地址</th>
	    			<td colspan="3">     </td>
	    		</tr>
	    		<tr class="conTr" align="left">
	    			<th>备注</th>
	    			<td colspan="3"> ${item.exNote }   </td>
	    		</tr>
	    		
	    	</table>
    	</c:forEach>	
    	</c:if>	
    	
    	
    	
    	</c:if>
    </div>	
    	
</body>
</html>