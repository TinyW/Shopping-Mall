package cn.edu.zhku.jsj.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Model.User;
import cn.edu.zhku.jsj.Util.BaseUtil;
import cn.edu.zhku.jsj.Util.DBUtil;

public class OrderDao {
	public boolean save(Order order)
	{
		boolean flag=false;
		String sql="insert into `order`(createdate,ostate,user_id,address_id)value(?,?,?,?)";
		Object []params={order.getCreateDate(),order.getOstate(),order.getUser().getId(),order.getAddress().getId()};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	public boolean update(Order order)
	{
		boolean flag=false;
		String sql="update `order` set ostate=?,createdate=?,address_id=?,user_id=? where id=?";
		Object []params={order.getOstate(),order.getCreateDate(),order.getAddress().getId(),order.getUser().getId(),order.getId()};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from `order` where id=?";
		Object[]params={id};
		BaseUtil<Order> util=new BaseUtil<Order>();
		flag=util.update(sql, params);
		return flag;
	}
	public Order load(Map<String,Object> map,boolean flag1,boolean flag2,boolean flag3)
	{
		Order order=new Order();
		String sql="select * from `order` where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<Order> util=new BaseUtil<Order>();
		order=(Order) util.QueryOne(Order.class, sql, params);
		if(flag1)
		{
			String sql1="select * from address where id=(select id from `order` where id=?)";
			BaseUtil<Address> addressUtil=new BaseUtil<Address>();
			Object[]p={order.getId()};
			order.setAddress((Address)addressUtil.QueryOne(Address.class, sql1, p));
			
		}
		if(flag2)
		{
			String sql1="select * from user where id=(select id from `order` where id=?)";
			BaseUtil<User> addressUtil=new BaseUtil<User>();
			Object[]p={order.getId()};
			order.setUser((User)addressUtil.QueryOne(User.class, sql1, p));
		}
		if(flag3)
		{
			String sql3="select * from order_item where id=?";
			BaseUtil<OrderItem> itemUtil=new BaseUtil<OrderItem>();
			Object []param={order.getId()};
			order.setItems(itemUtil.QueryList(OrderItem.class, sql3, param));
		}
		return order;
	}
	public List<Order> list(Map<String,Object>map,Pager pager,String order,String choose)
	{
		List<Order>orders=new ArrayList<Order>();
		String sql="select * from `order` where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else sql=sql+" order by ? asc limit ?,?";
		
		Object params=map.values().toArray();
		System.out.println(params);
		BaseUtil<Order> util=new BaseUtil<Order>();
		orders=util.QueryList(Order.class, sql, params,order,pager.getCurrent(),pager.getEachRecord());
		return orders;
	}
	public int countOrder(Map<String,Object>map)
	{
		int result=0;
		String sql="select count(*) from `order` where 1=1";
		BaseUtil<Order> util=new BaseUtil<Order>();
		if(null!=map)
		{
			for(String key:map.keySet())
				sql=sql+" and "+key+"=?";
			Object params=map.values().toArray();
			result=util.count(Order.class,sql,params);
		}
		else
			util.count(Order.class,sql);
		return result;
	}
	public List<OrderItem> saveOrder(int item_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select * from order_item where id=?";
		List<OrderItem> items = new ArrayList<OrderItem>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_id);
			rs = pstmt.executeQuery();
			OrderItem orderitem = null;
			Good good = null;
			while(rs.next()){
				orderitem = new OrderItem();
				good = new OrderItemDao().getGoodById(rs.getInt("good_id"));
				orderitem.setId(item_id);
				orderitem.setNum(rs.getInt("num"));
				orderitem.setGood(good);
				items.add(orderitem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
}
