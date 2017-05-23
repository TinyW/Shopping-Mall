package cn.edu.zhku.jsj.Dao;

import java.util.Map;

import cn.edu.zhku.jsj.Model.Address;
import cn.edu.zhku.jsj.Util.BaseUtil;

public class AddressDao {
	public boolean save(Address address)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address> ();
		String sql="insert into address(location,receiver,receivertel)"
				+ "value(?,?,?)";
		Object params[]={address.getLocation(),address.getReceiver(),address.getReceivertel()};
		flag=util.update(sql, params);
		return flag;
	}
	public boolean delete(int id)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address>();
		String sql="delete from address where id=?";
		Object[] params={id};
		flag=util.update(sql,params);
		return flag;
		
	}
	public boolean update(Address address)
	{
		boolean flag=false;
		BaseUtil<Address> util=new BaseUtil<Address> ();
		String sql="update address set location=?,receiver=?,receivertel=? where id=?";
		Object params[]={address.getLocation(),address.getReceiver(),address.getReceivertel(),address.getId()};
		flag=util.update(sql, params);
		return flag;
	}
	public Address load(Map<String,Object> map)
	{
		Address address=new Address();
		String sql="select * from address where 1=1";
		for(String key:map.keySet())
			sql=sql+" and "+key+"=?";
		Object []params=map.values().toArray();
		BaseUtil<Address>util=new BaseUtil<Address>();
		address=(Address) util.QueryOne(Address.class, sql, params);
		return address;
	}
}
