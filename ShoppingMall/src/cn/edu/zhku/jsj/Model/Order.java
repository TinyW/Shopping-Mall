package cn.edu.zhku.jsj.Model;
import java.sql.Date;


public class Order {
	private int id;
	private int user_id;
	private int address_id;
	private Date createdate;
	private String ostable;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getOstable() {
		return ostable;
	}
	public void setOstable(String ostable) {
		this.ostable = ostable;
	}	
}