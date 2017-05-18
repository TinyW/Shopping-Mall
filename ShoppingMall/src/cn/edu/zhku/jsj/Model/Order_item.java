package cn.edu.zhku.jsj.Model;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;

public class Order_item {
	private int id;
	private int user_id;
	private int order_id;
	private int good_id;
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
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
	}
	public DecimalDV getGprice() {
		return gprice;
	}
	public void setGprice(DecimalDV gprice) {
		this.gprice = gprice;
	}
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	private DecimalDV gprice;
	private int gnum;
}
