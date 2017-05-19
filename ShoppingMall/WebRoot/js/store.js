
	   function listInfo(){  
             $.ajax({  
               type:"post",//请求方式  
               url:"StoreManagerCtrl",//发送请求地址  
               timeout:30000,//超时时间：30秒  
               dataType:"json",//设置返回数据的格式  
               data:{
               		method:"list",
               		username:$("input[name='username']").val(),
               		order:$("select[name='order']").val(),
               		choose:$("select[name='choose']").val(),
               		currentPage:$("input[name='currentPage']").val(),
               		eachRecord:$("input[name='eachRecord']").val(),
               		totalRecord:$("input[name='totalRecord']").val()
               },
               //请求成功后的回调函数 data为json格式  
               success:function(data){  
               	     var table = $("#UserTable"); 
               	     var ul=$(".Pager");
	            	 table.empty();  
	            	 ul.empty();
	            	 table.append('<tr><th>编号</th><th>姓名</th><th>性别</th><th>类型</th><th>操作</th></tr>');  

              		 var objs=eval(data);
	                 var obj=objs.users;
	                 if(obj.length==1)
                	 {
	                	 var msg=obj[0].msg;
	                	 table.append('<tr><td colspan="5">'+msg+'</td></tr>');
                	 }
	                 else
	                {
	                	 var totalRecord=$("input[name='totalRecord']");
		                 var totalPage=$(".totalPage");
		                 
		                 ul.append('<li><a class="prevPage">《上一页</a></li>');
		                 
		           		 for(var i=0;i< obj.length;i++)
		           		 {
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
			                	 var type=grant(obj[i].type);
			                	 table.append('<tr><td>'+id+'</td><td>'+username+'</td><td>'+sex+'</td><td>'+type+'</td>'
			               			+'<td><a href="StoreManagerCtrl?method=get&id='+id+'"'+'>查看</a>'
			               			+'<a class="del">删除</a></td></tr>');	
			               		 
		           		 	}
		           		 
		            	}  
		            	for(var j=0;j<totalPage.html();j++)
	             		{
	             			var temp=j+1;
	             			ul.append('<li><a class="c_page">'+temp+'</a></li>');
	             		}
						ul.append('<li><a class="nextPage">下一页 》</a></li>');
						$("a").css("cursor","pointer");
						//点击请求某一页数据
						$(".c_page").mousedown(function(){
					       		$("input[name='currentPage']").val($(this).html());
					       		return listInfo();
	    				});
						//点击请求下一页数据
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
	    				//点击请求上一页数据
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
	    				});
	    				//请求删除某一列数据
	    				$(".del").mousedown(function(){
							var flag=confirm("是否确认删除");
							var attr=$(this).parent().parent();
							if(flag)
							{
								$.ajax({
									type:"get",
									url:"StoreManagerCtrl?method=delete&id="+id,
									dataType:"json",
									success:function(data)
									{
										var flag=data.flag;
										if(flag) $(attr).remove();
										else alert("删除失败");
										return true;
									} 
								})
							}
							else return false;								
						}) 
	                }
	                
               },  
             
           });  
          } 
	    
        function grant(type)
        {
        	if(type==0) return '顾客'
        	else if(type==1) return '商家'
        	else return '运营商'
        }
         //清空还原当前页数和总记录默认值以及搜索框的值
        function emptyRecord()
        {
        	$("input[name='currentPage']").val(1);
            $("input[name='totalRecord']").val(0);
            $("input[name='username']").val("");
        }
        //随机跳转页数赋值给当前页数
        function change()
        {
        	$("input[name='currentPage']").val($("input[name='randomPage']").val());
        }