<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=0.4">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/searchResults/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/searchResults/template.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/searchResults/searchResults.css">
<title>计算机等级考试报名查询</title>
</head>
<body>
		<div class="container-fluid" id="container">
		<c:if test="${empty count}">
	        <div style="margin-top: 100px;" >
	        	<h1 style="text-align: center;">
	        		您没有进行计算机等级考试报名
	        	</h1>
				<br><br>
				<h1 style="text-align: center;">
					请在报名后再次查询
	        	</h1>
	        </div>
        </c:if>
        <!--报价汇总表-->
        <c:if test="${not empty list1 }">
         <div class="layout-table total">
            <div class="layout-table-header blue" style="vertical-align: center;">
                <span class="span-7"></span>
                <span class="span-10" style="display: table-cell; ">
                    	计算机二级
                </span>
                <span class="span-7"></span>
            </div>
            <div class="layout-table-content tbl tbl-simple tbl-striped">
                <div class="tbl-header blue total-header">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                            <tr class="head-tr">
                                <td class="blue">
                                    <span>报考号</span>
                                </td>
                                <td class="blue">
                                    <span>准考证号</span>
                                </td>
                                <td class="blue">
                                    <span>姓名</span>
                                </td>
                                <td class="blue">
                                    <span>学号</span>
                                </td>
                                <td class="blue">
                                    <span>身份证号</span>
                                </td>
                                <td class="blue">
                                    <span>性别</span>
                                </td>
                                <td class="blue">
                                    <span>民族</span>
                                </td>
                                <td class="blue">
                                    <span>电话</span>
                                </td>
                                <td class="blue">
                                    <span>报考类型</span>
                                </td>
                                <td class="blue">
									<span>考场</span>
                                </td>
                                <td class="blue">
									<span>批次号（对应的具体时间）</span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl-content total-content">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                        	<c:forEach items="${list1 }" var="item" varStatus="status" >
                            <tr>
                                <td class="bf first" style="font-size: 20px;font-weight: bold;">${item.exEnterNumber }</td>
                                <td class="bf"  style="font-size: 20px;font-weight: bold;">${item.exAdmissionTicket }</td>
                                <td class="input-bf input-box" style="font-size: 20px;font-weight: bold;">${item.exName }</td>
                                <td class="bf lf" style="font-size: 20px;font-weight: bold;text-align: center;">${item.exNumber }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exIDcard }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exSex }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exNation }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exTele }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exType}</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exExamRoom }</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exBatchNum }</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
       </c:if>
        
    <!-- 第二个总报表 -->
    	<c:if test="${empty list2 }">
        </c:if>
        <c:if test="${not empty list2 }">
         <div class="layout-table total">
            <div class="layout-table-header blue" style="vertical-align: center;">
                <span class="span-7"></span>
                <span class="span-10" style="display: table-cell; ">
                    	计算机三级
                </span>
                <span class="span-7"></span>
            </div>
            <div class="layout-table-content tbl tbl-simple tbl-striped">
                <div class="tbl-header blue total-header">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                            <tr class="head-tr">
                                <td class="blue">
                                    <span>报考号</span>
                                </td>
                                <td class="blue">
                                    <span>准考证号</span>
                                </td>
                                <td class="blue">
                                    <span>姓名</span>
                                </td>
                                <td class="blue">
                                    <span>学号</span>
                                </td>
                                <td class="blue">
                                    <span>身份证号</span>
                                </td>
                                <td class="blue">
                                    <span>性别</span>
                                </td>
                                <td class="blue">
                                    <span>民族</span>
                                </td>
                                <td class="blue">
                                    <span>电话</span>
                                </td>
                                <td class="blue">
                                    <span>报考类型</span>
                                </td>
                                <td class="blue">
									<span>考场</span>
                                </td>
                                <td class="blue">
									<span>批次号（对应的具体时间）</span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl-content total-content">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                        	<c:forEach items="${list2 }" var="item" varStatus="status" >
                            <tr>
                                <td class="bf first" style="font-size: 20px;font-weight: bold;">${item.exEnterNumber }</td>
                                <td class="bf"  style="font-size: 20px;font-weight: bold;">${item.exAdmissionTicket }</td>
                                <td class="input-bf input-box" style="font-size: 20px;font-weight: bold;">${item.exName }</td>
                                <td class="bf lf" style="font-size: 20px;font-weight: bold; text-align: center" >${item.exNumber }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exIDcard }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exSex }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exNation }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exTele }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exType }</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exExamRoom }</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exBatchNum }</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
       </c:if>
    <!-- 第三个总报表 -->
    	<c:if test="${empty list3 }">
        </c:if>
        <c:if test="${not empty list3 }">
         <div class="layout-table total">
            <div class="layout-table-header blue" style="vertical-align: center;">
                <span class="span-7"></span>
                <span class="span-10" style="display: table-cell; ">
                    	计算机四级
                </span>
                <span class="span-7"></span>
            </div>
            <div class="layout-table-content tbl tbl-simple tbl-striped">
                <div class="tbl-header blue total-header">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                            <tr class="head-tr">
                                <td class="blue">
                                    <span>报考号</span>
                                </td>
                                <td class="blue">
                                    <span>准考证号</span>
                                </td>
                                <td class="blue">
                                    <span>姓名</span>
                                </td>
                                <td class="blue">
                                    <span>学号</span>
                                </td>
                                <td class="blue">
                                    <span>身份证号</span>
                                </td>
                                <td class="blue">
                                    <span>性别</span>
                                </td>
                                <td class="blue">
                                    <span>民族</span>
                                </td>
                                <td class="blue">
                                    <span>电话</span>
                                </td>
                                <td class="blue">
                                    <span>报考类型</span>
                                </td>
                                <td class="blue">
									<span>考场</span>
                                </td>
                                <td class="blue">
									<span>批次号（对应的具体时间）</span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl-content total-content">
                    <table>
                        <colgroup>
                            <col style="width: 190px;">
                            <col style="width: 220px;">
                            <col style="width: 140px;">
                            <col style="width: 160px;">
                            <col style="width: 250px;">
                            <col style="width: 120px;">
                            <col style="width: 150px;">
                            <col style="width: 200px;">
                            <col style="width: 300px;">
                            <col style="width: 100px;">
                            <col style="width: 300px;">
                        </colgroup>
                        <tbody>
                        	<c:forEach items="${list3 }" var="item" varStatus="status" >
                            <tr>
                                <td class="bf first" style="font-size: 20px;font-weight: bold;">${item.exEnterNumber }</td>
                                <td class="bf"  style="font-size: 20px;font-weight: bold;">${item.exAdmissionTicket }</td>
                                <td class="input-bf input-box" style="font-size: 20px;font-weight: bold;">${item.exName }</td>
                                <td class="bf lf" style="font-size: 20px;font-weight: bold;text-align: center;">${item.exNumber }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exIDcard }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exSex }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exNation }</td>
                                <td class="input-bf" style="font-size: 20px;font-weight: bold;">${item.exTele }</td>
                                <td class="bf" style="font-size: 20px;font-weight: bold;">${item.exType }</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exExamRoom }</td>
                                <td class="remove-bf" style="font-size: 20px;font-weight: bold;">${item.exBatchNum }</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
       </c:if>
    </div>
</body>
</html>