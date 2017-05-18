package cn.edu.zhku.jsj.Operator.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Operator.Service.CustomerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@WebServlet(name="CustomerManagerCtrl",urlPatterns="/CustomerManagerCtrl")
@SuppressWarnings("serial")
public class CustomerManagerCtrl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		if("list".equals(method))
			list(request,response);
		else if("load".equals(method))
			load(request,response);
		else if("get".equals(method))
			get(request,response);
		else if("delete".equals(method))
			delete(request,response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	/*
	 * 该方法根据提交的条件查询用户列表
	 */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Pager pager=new Pager();
		CustomerService service=new CustomerService();
		//获取参数
		String order=request.getParameter("order");
		String choose=request.getParameter("choose");
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		int eachRecord=Integer.parseInt(request.getParameter("eachRecord"));
		int totalRecord=Integer.parseInt(request.getParameter("totalRecord"));
		if(totalRecord!=0)
			//totalPage不为0则存进pager
			pager.setTotalRecord(totalRecord);
		

		//参数设置
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("type",0);
		
		pager.setCurrentPage(currentPage);
		pager.setEachRecord(eachRecord);

		List<User> list=service.list(params, pager, order, choose);

		
		JSONObject resultJson = new JSONObject();// 创建最后结果的json
        JSONArray jsonArray = new JSONArray();// json数组
        JSONObject o1=new JSONObject();
        //总记录数
        o1.put("totalRecord",pager.getTotalRecord());
        JSONObject o2=new JSONObject();
        o2.put("totalPage",pager.getTotalPage());
        jsonArray.add(o1);
        jsonArray.add(o2);
    	 for(User u:list)
         {
         	JSONObject obj=new JSONObject();
         	obj.put("username", u.getUsername());
         	obj.put("id", u.getId());
         	obj.put("sex",u.getSex());
         	jsonArray.add(obj);
         }
         resultJson.put("users",jsonArray);
       
		PrintWriter out=response.getWriter();
		out.println(resultJson);
        out.flush();
        out.close();
	}
	/*
	 * 该方法根据提交的搜索条件查询用户
	 * 搜索条件只限用户名
	 */
	public void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String username=new String(request.getParameter("username").getBytes("ISO-8859-1"),"utf-8");
		CustomerService service=new CustomerService();
		User u=service.load(username);

		JSONObject obj=new JSONObject();

		if(null==u)
		{
			obj.put("msg","没有该用户");
		}
		else
		{
			obj.put("username", u.getUsername());
	    	obj.put("id", u.getId());
	    	obj.put("sex",u.getSex());
			
		}
    	PrintWriter out=response.getWriter();
		out.println(obj);
        out.flush();
        out.close();
	}
	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		CustomerService service=new CustomerService();
		User user=service.get(id);
		request.setAttribute("user", user);
		request.getRequestDispatcher("Operator/Manager/SingleCustomer.jsp").forward(request,response);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id=Integer.parseInt(request.getParameter("id"));
		CustomerService service=new CustomerService();
		boolean flag=service.delete(id);
		JSONObject obj=new JSONObject();
		obj.put("flag",flag);
		PrintWriter out=response.getWriter();
		out.println(obj);
        out.flush();
        out.close();
	}
}
