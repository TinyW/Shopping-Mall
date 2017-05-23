package cn.edu.zhku.jsj.Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int id;
	private Date createDate;//订单创建日期
	private int state;//状态
	private User user;//顾客
	private Address address;//地址
	private List<OrderItem> items=new ArrayList<OrderItem>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Order(Date createDate, int state, User user, Address address) {
		super();
		this.createDate = createDate;
		this.state = state;
		this.user = user;
		this.address = address;
	}
	public Order(int id, Date createDate, int state) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.state = state;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", createDate=" + createDate + ", state=" + state + ", user=" + user + ", address="
				+ address + "]";
	}
	
}
