package cn.edu.zhku.jsj.Operator.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;

public class CustomerService {
	/*
	 * pager 提供当前页数，每页记录
	 */
	public List<User> list(Map<String,Object> params,Pager pager,String order,String choose)
	{
		List<User> list=new ArrayList<User>();
		UserDao dao=new UserDao();
		list=dao.list(params, pager, order, choose);
		return list;
	}
	public User load(String value)
	{
		User user=new User();
		UserDao dao=new UserDao();
		user=dao.load("username", value);
		return user;
	}
	public Pager countCustomer()
	{
		Pager pager=new Pager();
		UserDao dao=new UserDao();
		pager.setTotalRecord(dao.countUser());
		return pager;
	}
	public User get(int id)
	{
		User user=new User();
		UserDao dao=new UserDao();
		user=dao.load("id", id);
		return user;
	}
}
