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
	<script type="text/javascript">
	   function loadInfo()
	   {
	   		$.ajax({
	   			type:"get",
	   			url:"CustomerManagerCtrl",
	   			dataType:"json",
	   			data:{
	   				method:"load",
	   				username:$("input[name='username']").val()
	   			},
	   			success:function(data)
	   			{
	   				//var obj=eval(data);
	   				var table = $("#UserTable");  
	   				table.empty(); 
            		table.append('<tr><th>编号</th><th>姓名</th><th>性别</th><th>操作</th></tr>');  
					if(data.msg)
					{
						table.append('<tr><td colspan="4">'+data.msg+'</td></tr>');
					}
					else
					{
						var id = data.id;
               			var username = data.username;
               			var sex = data.sex;
               			table.append('<tr><td>'+id+'</td><td>'+username+'</td><td>'+sex+'</td>'
               			+'<td><a href="CustomerManagerCtrl?method=get&id='+id+'"'+'>查看</a>'
               			+'<a href="CustomerManagerCtrl?method=delete&id='+id+'"'+'>删除</a></td></tr>');
						
					}

	   			}
	   			
	   		})
	   }
	   
	   function listInfo(){  
             $.ajax({  
               type:"post",//请求方式  
               url:"CustomerManagerCtrl",//发送请求地址  
               timeout:30000,//超时时间：30秒  
               dataType:"json",//设置返回数据的格式  
               data:{
               		method:"list",
               		order:$("select[name='order']").val(),
               		choose:$("select[name='choose']").val(),
               		currentPage:$("input[name='currentPage']").val(),
               		eachRecord:$("input[name='eachRecord']").val(),
               		totalRecord:$("input[name='totalRecord']").val()
               },
               //请求成功后的回调函数 data为json格式  
               success:function(data){  
               	     var table = $("#UserTable");  
	            	 table.empty();  
	            	 table.append('<tr><th>编号</th><th>姓名</th><th>性别</th><th>操作</th></tr>');  

	              		 var objs=eval(data);
		                 var obj=objs.users;
		                 var ul=$(".Pager");
		                 var totalRecord=$("input[name='totalRecord']");
		                 var totalPage=$(".totalPage");
		                 ul.empty();
		                 ul.append('<li><a class="prevPage">《上一页</a></li>');
		                 
		           		 for(var i=0;i< obj.length;i++){
		           		 	if(i==0)
		           		 	{
		           		 		totalRecord.val(obj[i].totalRecord);		
		                 		
		           		 	}
		           		 	else if(i==1)
		           		 	{
		           		 		totalPage.html(obj[i].totalPage);
		           		 	}
		           		 	
		           		 	else
		           		 	{
			           		 	 var id = obj[i].id;
			                	 var username = obj[i].username;
			                	 var sex = obj[i].sex;
			                	 table.append('<tr><td>'+id+'</td><td>'+username+'</td><td>'+sex+'</td>'
			               			+'<td><a href="CustomerManagerCtrl?method=get&id='+id+'"'+'>查看</a>'
			               			+'<a class="del" href="CustomerManagerCtrl?method=delete&id='+id+'"'+'>删除</a></td></tr>');	
		           		 	}
		           		 
		            	}  
		            	for(var j=0;j<totalPage.html();j++)
                 		{
                 			var temp=j+1;
                 			ul.append('<li><a class="c_page">'+temp+'</a></li>');
                 			
                 		}
						ul.append('<li><a class="nextPage">下一页 》</a></li>');
						$("a").css("cursor","pointer");
						$(".del").mousedown(function(){
							comfirm("确认删除");
						});
						$(".c_page").mousedown(function(){
					       		$("input[name='currentPage']").val($(this).html());
					       		return listInfo();
        					});
        				$(".nextPage").mousedown(function(){
        					var currentPage=$("input[name='currentPage']");
        					
        					if(parseInt(currentPage.val())==parseInt(totalPage.html()))
        					{
        						alert("已经是最后一页了");
        						return;
        					}
        					else
        					{
        						currentPage.val(parseInt(currentPage.val())+1);
        						return listInfo();
        					}
        				});
        				$(".prevPage").mousedown(function(){
        					var currentPage=$("input[name='currentPage']");
        					
        					if(parseInt(currentPage.val())==1)
        					{
        						alert("已经是第一页了");
        						return;
        					}
        					else
        					{
        						currentPage.val(parseInt(currentPage.val())-1);
        						return listInfo();
        					}
        				})
              },  
              //请求出错的处理  
              //error:function(){  
               //         alert("请求出错");  
              //}  
           });  
          } 
        function emptyRecord()
        {
        	$("input[name='currentPage']").val(1);
            $("input[name='totalRecord']").val(0);
            

        }
        $(function(){
        	
        	
        	
        })
  </script>
  </head>
  
  <body>
  	<form>
  		<input type="text" name="username"/>
  		<input type="button" value="搜索用户名" onclick="loadInfo()"/>
  		<span>按</span>
  		<select name="order" onchange='emptyRecord()'>
  			<option value="id" selected="selected">编号</option>
  			<option value="username">姓名</option>
  		</select>
  		<select name="choose" onchange='empty()'>
  			<option value="asc" selected="selected">升序</option>
  			<option value="desc">降序</option>
  		</select>
  		<input type="hidden" name="currentPage" value="1">
  		<input type="hidden" name="eachRecord" value="5">

  		<input type="button" value="确定" onclick="listInfo()">
  	</form>
  	<table id="UserTable" border="1"></table>
  	
  	<form action="">
  			<ul class="Pager"></ul>
		   	<ul>
		   		<li>
		   			<input type="hidden" name="totalRecord" value="0">
		   			共<span class="totalPage">0</span>页
		   			<span>到第<input type="text" size="2">页</span>
		   		</li>
		   		<li><input type="button" value="确定" onclick="pager()"></li>
		   	</ul>
    	</form>
  	
  </body>
</html>
