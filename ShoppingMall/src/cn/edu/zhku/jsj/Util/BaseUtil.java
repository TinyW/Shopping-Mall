package cn.edu.zhku.jsj.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;

public class BaseUtil<T> {
	private  Connection ct;
	private  PreparedStatement ps;
	private  ResultSet rs;
	/*
	 * 该方法实现根据不同条件查询用户，包括Where语句，order by,排序方式desc,asc以及分页limit
	 * sql语句由具体的对象Dao编写
	 * 成功返回list
	 */
	@SuppressWarnings("unchecked")
	public List<T> QueryList(Class<T> clz,String sql,Object... params)
	{
//		pager.setTotalRecord(count(clz));
		List<T>list=null;
		ct=DBUtil.getConnection();
		try {	
			ps=ct.prepareStatement(sql);
			//TODO:升降序问题，没有效果，出现的效果为数据库默认的asc
			if(params!=null)
			{
				for(int i=0;i<params.length;i++)
				{
					ps.setObject(i+1,params[i]);
				}
			}

			rs=ps.executeQuery();

			ResultSetMetaData data=rs.getMetaData();
			Method[] methods=clz.getMethods();
			list=new ArrayList<T>();
			while(rs.next()){
				
				Object obj=clz.newInstance();
				for(int i=1;i<=data.getColumnCount();i++)
				{
					
					String str=data.getColumnName(i);
					for(Method m:methods)
					{
						
						if(m.getName().equalsIgnoreCase("set"+str))
						{
							Object s=rs.getObject(str);
							if(null!=s)
								m.invoke(obj,s);
							break;
						}
					}
				}
				list.add((T) obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(ct, ps, rs);
		}
		return list;
	}
	public Object QueryOne(Class<T> clz,String sql,Object params[])
	{
		Object obj=null;
		ct=DBUtil.getConnection();
		try {
			ps=ct.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				ps.setObject(i+1,params[i]);
			}
			rs=ps.executeQuery();
			ResultSetMetaData data=rs.getMetaData();
			Method[] methods=clz.getMethods();
			if(rs.next())
			{
				obj=clz.newInstance();
				for(int i=1;i<=data.getColumnCount();i++)
				{
					String str=data.getColumnName(i);
					for(Method m:methods)
					{
						if(m.getName().equalsIgnoreCase("set"+str))
						{
							Object s=rs.getObject(str);
							if(null!=s)
								m.invoke(obj,s);
							break;
						}
					}
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(ct, ps, rs);
		}
		return obj;
	}
	
	/*
	 * 该方法实现根据不同条件执行删除、修改、插入操作
	 * sql语句由具体的对象Dao编写
	 * 成功返回true,失败返回false
	 *
	 */
	public boolean update(String sql,Object params[])
	{
		boolean flag=false;
		ct=DBUtil.getConnection();
		try {
			ps=ct.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				ps.setObject(i+1,params[i]);
			}
			int result=ps.executeUpdate();
			if(result!=-1)
				flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(ct, ps, rs);
		}
		
		return flag;
	}
	public int count(Class<T>clz,int type)
	{
		int count =0;
		ct=DBUtil.getConnection();
		try {
			String name=clz.getName().substring(clz.getName().lastIndexOf(".")+1);
			System.out.println(name);
			String sql="select count(*) from "+name+" where type=?";
			System.out.println(sql);
			ps=ct.prepareStatement(sql);
			ps.setInt(1,type);
			rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(ct, ps, rs);
		}
		return count;
	}

	
}