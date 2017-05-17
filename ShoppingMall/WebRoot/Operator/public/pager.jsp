<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
    <div>
    	<form action="">
		   	<ul>
		   		<li><a href="#">《上一页</a></li>
		   		<c:forEach items="">
		   			<li></li>
		   		</c:forEach>
		   		<li><a href="#">下一页 》</a></li>
		   		<li>
		   			<span>共页</span>
		   			<span>到第<input type="text" size="2">页</span>
		   		</li>
		   		<li><input type="submit" value="确定"></li>
		   		
		   	</ul>
    	
    	</form>
    </div>
  </body>
</html>
