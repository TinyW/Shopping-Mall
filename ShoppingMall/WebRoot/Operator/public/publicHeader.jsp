<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>My JSP 'CustomerManager.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

	<link rel="stylesheet" type="text/css" href="css/public.css">

	<style type="text/css">
		body{
			width:1000px;
			margin:0 auto;
		}
		h1{
			text-align:center;
			font-size:40px;
			color:#FF9966;
			margin:30px 0 20px 0;
			
		}
		h1:hover{
			color:#003399;
		}
		h2{
			text-align:center;
			color:#999999;
			margin-bottom:20px;
		}
		.header ul{
			float:left;
			width:1000px;
			background-color:#f96;
		}
		.header ul li{
			float:left;
			padding-left:52px;
			
		}
		.header ul li a{
			display:inline-block;
			text-decoration:none;
			color:#fff;
			padding:15px 10px;
		}
		.header ul li a:hover{
			background-color:#FF6633;
			
		}
		
		
	</style>
  </head>
  
  <body>
  	
  	<div class="header">
  		<h1>购物管理系统</h1>
  		<h2>${sessionScope.user.username },欢迎进入</h2>
  		<ul>
  			<li><a href="Operator/Manager/HomePage.jsp" id="homepage">首页</a></li>
	    	<li><a href="Operator/Manager/CustomerManager.jsp" id="customer">顾客管理</a></li>
	    	<li><a href="Operator/Manager/StoreManager.jsp" id="store">店铺管理</a></li>
	    	<li><a href="Operator/Manager/SingleUser.jsp" id="self">个人中心</a></li>
	    	<li><a href="#">举报处理</a></li>
    	</ul>
  	</div>
    
  </body>
</html>
