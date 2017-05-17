<%@ page language="java" import="java.util.*,cn.edu.zhku.jsj.Model.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CustomerManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script> 
	<script type="text/javascript">
	
	   

  </script>
  </head>
  
  <body>
 	<div>
 		<input type="hidden" name="id" value="${requestScope.user.id}">
 		<span>用户名</span><input type="text" name="username" value="${requestScope.user.username }">
 		<span>昵称</span><input type="text" name="nickname" value="${requestScope.user.nickname }">
 		<span>电话</span><input type="text" name="tel" value="${requestScope.user.tel }">
 		<span>性别</span>
 		<c:choose>
 			<c:when test="${fn:contains(requestScope.user.sex,'男')}">
 				<input type="radio" name="sex" checked="checked">男
 				<input type="radio" name="sex">女
 			</c:when>
 			<c:otherwise>
 				<input type="radio" name="sex">男
 				<input type="radio" name="sex" checked="checked">女
 			</c:otherwise>
 		</c:choose>
 		<span>生日</span><input type="text" name="birth" value="${requestScope.user.birth }">
 		<span>注册日期</span><input type="text" name="register" value="${requestScope.user.register }" readonly>
 		<c:forEach items="${requestScope.user.addresses }" var="a">
 			<div>
 				<span>收货人</span><span>${a.receiver}</span>
 				<span>收货人电话</span><span>${a.receivertel}</span>
 				<span>收货地址</span><span>${a.location}</span>
 			</div>
 		</c:forEach>
 		<input type="button" value="提交修改">
 	
 			
 		<span></span>
 	</div>
  </body>
</html>
