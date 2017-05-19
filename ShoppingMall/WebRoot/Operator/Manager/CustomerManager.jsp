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
    
    <title>My JSP 'CustomerManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="js/jquery-1.11.1.js"></script> 
	<script type="text/javascript" src="js/customer.js"></script>
  </head>
  
  <body>
  	<form>
  		
  		<span>排序方式：</span>
  		<select name="order" onchange='emptyRecord()'>
  			<option value="id" selected="selected">编号</option>
  			<option value="username">姓名</option>
  		</select>
  		<select name="choose" onchange='empty()'>
  			<option value="asc" selected="selected">升序</option>
  			<option value="desc">降序</option>
  		</select>
  		<input type="hidden" name="currentPage" value="1">
  		<input type="hidden" name="eachRecord" value="10">

  		<input type="button" value="显示所有顾客" onclick="emptyRecord(),listInfo()">
  		<input type="text" name="username" onfocus="emptyRecord()"/>
  		<input type="button" value="搜索用户名" onclick="listInfo()"/>
  	</form>
  	<table id="UserTable" border="1"></table>
  	
  	<div>
  			<ul class="Pager"></ul>
		   	<ul>
		   		<li>
		   			<input type="hidden" name="totalRecord" value="0">
		   			共<span class="totalPage">0</span>页
		   			<span>到第<input name="randomPage" type="text" size="2">页</span>
		   		</li>
		   		<li><input type="button" value="确定" onclick="change(),listInfo()"></li>
		   	</ul>
    </div>
  	
  </body>
</html>
