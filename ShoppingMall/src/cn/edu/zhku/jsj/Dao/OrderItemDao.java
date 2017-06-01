package cn.edu.zhku.jsj.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.Model.Good;
import cn.edu.zhku.jsj.Model.Order;
import cn.edu.zhku.jsj.Model.OrderItem;
import cn.edu.zhku.jsj.Model.Pager;
import cn.edu.zhku.jsj.Util.BaseUtil;
import cn.edu.zhku.jsj.Util.DBUtil;

public class OrderItemDao {
	/*
	 * 该方法用于保存一个订单明细
	 * 成功返回true，失败返回false
	 * 输入：OrderItem对象和隶属的订单号
	 * 注：OrderItem对象里的user和good不能为null
	 */
	public boolean save(OrderItem item,int order_id)
	{
		boolean flag=false;
		String sql="insert into order_item(good_id,num,order_id)value(?,?,?)";
		Object []params={item.getGood().getId(),item.getNum(),order_id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);
		return flag;
	}
	/*
	 * 该方法用于删除某个订单明细
	 * 成功返回true，失败返回false
	 * 输入：订单明细id
	 */
	public boolean delete(int id)
	{
		boolean flag=false;
		String sql="delete from order_item where id=?";
		Object []params={id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);	
		return flag;
	}
	/*
	 * 该方法用于修改某个订单明细
	 * 成功返回true，失败返回false
	 * 输入：订单明细id和订单明细购买数量
	 * 
	 */
	//TODO:OrderItem update方法只允许更新购买数量
	public boolean update(int id,int num)
	{
		boolean flag=false;
		String sql="update order_item set num=? where id=?";
		Object []params={num,id};
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		flag=util.update(sql, params);
		return flag;
	}
	public OrderItem load(Map<String,Object> map)
	{
		OrderItem item=new OrderItem();
		String sql="select * from order_item where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<OrderItem>util=new BaseUtil<OrderItem>();
		item=(OrderItem) util.QueryOne(OrderItem.class,sql,params);
		return item;
	}
	public List<OrderItem> list(Map<String,Object>map,Pager pager,String order,String choose)
	{
		List<OrderItem> orderitem=new ArrayList<OrderItem>();
		String sql="select * from order_item where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		if("desc".equals(choose))
			sql=sql+" order by ? desc limit ?,?";
		else sql=sql+" order by ? asc limit ?,?";
		Object params=map.values().toArray();
		BaseUtil<OrderItem> util=new BaseUtil<OrderItem>();
		orderitem=util.QueryList(OrderItem.class, sql, params,order,pager.getCurrent(),pager.getEachRecord());
		return orderitem;
	}
	
	//通过订单ID查询订单项
	public List<OrderItem> getOrderItems(int order_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select * from order_item where order_id=?";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_id);
			rs = pstmt.executeQuery();
			List<OrderItem> orderitems = new ArrayList<OrderItem>();
			OrderItem orderitem = null;
			Good good = null;
			while(rs.next()){
				orderitem = new OrderItem();
				good = new Good();
				good = getGoodById(rs.getInt("good_id"));//通过good_id取出good信息
				orderitem.setId(rs.getInt("id"));
				orderitem.setNum(rs.getInt("num"));
				orderitem.setGood(good);
				orderitems.add(orderitem);
			}
				return orderitems;
	}
	
	//通过good_id查询good表中的信息
	public Good getGoodById(int good_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select * from good where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, good_id);
		rs = pstmt.executeQuery();
		Good good = new Good();
		while(rs.next()){
			good.setGname(rs.getString("gname"));
			good.setGprice(rs.getDouble("gprice"));
			good.setId(good_id);
			good.setIntroduction(rs.getString("introduction"));
		}
		return good;
	}
}
