package cn.edu.zhku.jsj.Operator.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Dao.UserDao;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;

public class UserService {
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
	public User load(String value,int type)
	{
		User user=new User();
		UserDao dao=new UserDao();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("type",type);
		map.put("username",value);
		user=dao.load(map);
		return user;
	}
	public int countCustomerOrStore(int type)
	{
		Pager pager=new Pager();
		UserDao dao=new UserDao();
		pager.setTotalRecord(dao.countUser(type));
		return pager.getTotalRecord();
	}
	public User get(int id)
	{
		User user=new User();
		UserDao dao=new UserDao();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("id",id);
		user=dao.load(map);
		return user;
	}
	public boolean delete(int id)
	{
		UserDao dao=new UserDao();
		boolean flag=dao.delete(id);
		return flag;
	}
}
