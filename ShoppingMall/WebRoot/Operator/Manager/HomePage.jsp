<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HomePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/homepage.css">
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$(".add").hide();
			$(".updatePsw").hide();
			$(".result").hide();
			$(".result .btn").css({
				"marginLeft":"10px"
			})
		})
		function addUser()
		{
			$(".add").show();
			$(".left-content a:eq(0)").css("color","#004CDB");
			$(".left-content a:eq(0)").css("textDecoration","none");
			$(".left-content a:eq(1)").css("color","#003399");
			$(".left-content a:eq(1)").css("textDecoration","underline");
			$(".updatePsw").hide();
		}
		function updatePsw()
		{
			$(".add").hide();
			$(".updatePsw").show();
			$(".left-content a:eq(1)").css("color","#004CDB");
			$(".left-content a:eq(1)").css("textDecoration","none");
			$(".left-content a:eq(0)").css("color","#003399");
			$(".left-content a:eq(0)").css("textDecoration","underline");
			
		}
		
		
		
		$(function(){
			$("input[name='username']").focus(function(){
				$("span:eq(0)").html("");
				$("span:eq(1)").html("");
			})
			$("input[name='password']").focus(function(){
				if($("input[name='username']").val()=="")
					$("span:eq(0)").html("用户名不能为空!");
				else
				{
					$.ajax({
						type:"post",
						url:"PersonalCenterCtrl",
						dataType:"json",
						data:{
							method:"verifyUser",
							username:$("input[name='username']").val()
						},
						success:function(data)
						{
							if(data.flag==true)
								$("span:eq(0)").html("用户名已存在!");
						}
					
					})
				}
			})
			$("input[name='password']").focus(function(){
				$("span:eq(1)").html("");	
			})
			//$("input[name='password']").blur(function(){
			//	if($(this).val()==""&&$("input[name='username']").val()!="")
			//		$("span:eq(1)").html("密码不能为空!");
			//})
			$("input[name='secondPsw']").focus(function(){
				if($("input[name='username']").val()!=""&&$("input[name='password']").val()=="")
					$("span:eq(1)").html("密码不能为空!");
				$("span:eq(2)").html("");	
			})
			//$("input[name='secondPsw']").blur(function(){
			//	if($(this).val()!=$("input[name='password']").val())
			//		$("span:eq(2)").html("密码与确认密码不一致");
			//})
			$("input[name='nickname']").focus(function(){
				if($("input[name='password']").val()!=""&&$("input[name='username']").val()!="")
					if($("input[name='secondPsw']").val()!=$("input[name='password']").val())
						$("span:eq(2)").html("密码与确认密码不一致");
				$("span:eq(3)").html("");	
			})
			$("input[name='nickname']").blur(function(){
				if($(this).val()!="")
					if($("input[name='nickname']").val().length<2||$("input[name='nickname']").val().length>10)
						$("span:eq(3)").html("昵称只能在2到10之间");
			})
			$("input[name='tel']").focus(function(){						
				$("span:eq(4)").html("");	
			})
			$("input[name='tel']").blur(function(){
				if($(this).val()!="")
					if($("input[name='tel']").val().length<8||$("input[name='tel']").val().length>11)
						$("span:eq(4)").html("联系电话只能在8到11之间");
			})
			$("input[name='year'] input[name='month'] input[name='day']").focus(function(){
				$("span:eq(5)").html("");	
			})
			$("input[name='year'] input[name='month'] input[name='day']").blur(function(){
				if($("input[name='year']").val()!=""||$("input[name='year']").val()!=""||$("input[name='month']").val()!="")
					if(parseInt($("input[name='year']").val())>2017||parseInt($("input[name='month']")).val()<0||parseInt($("input[name='month']").val())>12||parseInt($("input[name='day']").val())>31)
						$("span:eq(5)").html("填写的时间不符合实际");
			})
	
			//修改密码区
			$("input[name='oldPsw']").focus(function(){
				$(".updatePsw span:eq(0)").html("");
			})
			$("input[name='oldPsw']").blur(function(){
				if($("input[name='oldPsw']").val()=="")
					$(".updatePsw span:eq(0)").html("原密码不为空！");
			})
			$("input[name='secondOldPsw']").focus(function(){
				$(".updatePsw span:eq(1)").html("");
			})
			$("input[name='secondOldPsw']").blur(function(){
				if($(this).val()!=$("input[name='oldPsw']").val())
					$(".updatePsw span:eq(1)").html("原密码与确认密码不一致！");
				else
				{
					$.ajax({
						type:"post",
						url:"PersonalCenterCtrl",
						dataType:"json",
						data:{
							method:"verifyPsw",
							password:$("input[name='oldPsw']").val()
						},
						success:function(data)
						{
							if(data.flag==true)
								$(".updatePsw span:eq(0)").html("密码正确!");
						}
					})
				}
			})
			$("input[name='newPsw']").focus(function(){
				$(".updatePsw span:eq(2)").html("");
			})
			$("input[name='newPsw']").blur(function(){
				if($(this).val()=="") 
					$(".updatePsw span:eq(2)").html("新密码不为空！");
				else if(parseInt($(this).val())<4||parseInt($(this).val())>12)
					$(".updatePsw span:eq(2)").html("密码长度必须在4到12之间");
			})
			$("input[name='secondNewPsw']").focus(function(){
				$(".updatePsw span:eq(3)").html("");
			})
			$("input[name='secondNewPsw']").blur(function(){
				if($("input[name='newPsw']").val()!=$(this).val())
					$(".updatePsw span:eq(3)").html("新密码与确认密码不一致！");
			})
			
		
			$("span").css({"paddingLeft":"125px","color":"#FA8350"});
		})
		
		
		function register()
		{
			$("input[name='type']").val(2);
			var year=$("input[name='year']").val();
			var month=$("input[name='month']").val();
			var day=$("input[name='day']").val();
			var birth=year+'-'+month+'-'+day;
			var username=$("input[name='username']").val();
			var password=$("input[name='password']").val();

			if(username=="") $("span:eq(0)").html("用户名不能为空!");
			else if(password=="") $("span:eq(1)").html("密码不能为空!");
			else
			{
				$.ajax({
					type:"post",
					dataType:"json",
					url:"PersonalCenterCtrl",
					data:{
						method:"save",
						username:username,
						password:password,
						nickname:$("input[name='nickname']").val(),
						tel:$("input[name='tel']").val(),
						sex:$("input[name='sex']").val(),
						birth:birth,
						type:$("input[name='type']").val()
						
					},
					success:function(data)
					{
						if(data.flag==true)
						{
							$(".add").hide();
							$(".result").show();
							$(".result span").html("注册成功!");
							$(".result .btn").val("返回继续注册");
							
							$(".result .btn").click(function(){
								$(".add").show();
								$(".result").hide();
								$("input").val("");
								$("input[name='type']").val(2);
							})
							
						}
					}
				})
			}
			
		}
		function update()
		{
			var password=$("input[name='newPsw']").val();
			var oldPsw=$("input[name='oldPsw']").val();
			if(oldPsw=="") $(".updatePsw span:eq(0)").html("原密码不能为空！");
			else if(password=="") $(".updatePsw span:eq(2)").html("新密码不能为空！");
			else
			{
				$.ajax({
					type:"post",
					dataType:"json",
					url:"PersonalCenterCtrl",
					data:{
						method:"updatePsw",
						password:password
					},
					success:function(data)
					{
						if(data.flag==true)
						{
							$(".updatePsw").hide();
							$(".result").show();
							$(".result span").html("密码修改成功!");
							$(".result .btn").val("点击返回修改页面");
							
							$(".result .btn").click(function(){
								$(".updatePsw").show();
								$(".result").hide();
								$("input").val("");
								$("span").val("");
							})
							
						}
				}
				})
			}
			
		}
	
	</script>
	
  </head>
  
  <body>
  <div class="bg">
  	<jsp:include page="/Operator/public/publicHeader.jsp">
    	<jsp:param value="1" name="index"/>
    </jsp:include>
    <div class="left-content">
    	<ul>
    		<li><a onclick="addUser()">新增用户</a></li>
    		<li><a onclick="updatePsw()">修改密码</a></li>
    		<li><a>个人信息</a></li>
    		<li><a>查看用户</a></li>
    	</ul>
    </div>
    <div class="right-content">
    	<div class="add">
    		<label>用户名：</label><input type="text" name="username"><span></span><br>
    		<label>密码：</label><input type="password" name="password"><span></span><br>
    		<label>确认密码：</label><input type="password" name="secondPsw"><span></span><br>
    		<label>昵称：</label><input type="text" name="nickname"><span></span><br>
    		<label>联系电话：</label><input type="text" name="tel"><span></span><br>
    		<label>性别：</label>
    			<input type="radio" name="sex" value="男" class="sex"> 男
    			<input type="radio" name="sex" value="女" class="sex"> 女<br>
    		<label>出生年月：</label>
	    		<input type="text" size="2" name="year" class="date first"> -
	    		<input type="text" size="2" name="month" class="date"> -
	    		<input type="text" size="2" name="day" class="date"><span></span><br>
    		<input type="hidden" name="type" value="2">
    		<input class="btn" type="button" value="注册" onclick="register()">
    		
    		
    	</div>
    	<div class="updatePsw">
    		<!-- session中的user比较 -->

    		<label>原密码：</label><input type="password" name="oldPsw"><span></span><br>
    		<label>原密码确认：</label><input type="password" name="secondOldPsw"><span></span><br>
    		<label>新密码：</label><input type="password" name="newPsw"><span></span><br>
    		<label>新密码确认：</label><input type="password" name="secondNewPsw"><span></span><br>
    		<input class="btn" type="button" value="确认修改" onclick="update()">
    	</div>
    	<div class="result">
    		<span>注册成功</span><br>
    		<input class="btn" type="button">
    	</div>
    </div>
  </div>
    
  </body>
</html>
