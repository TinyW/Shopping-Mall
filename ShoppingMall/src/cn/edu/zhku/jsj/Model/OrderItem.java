package cn.edu.zhku.jsj.Model;

public class OrderItem {
	private int id;
	private double price;//总金额
	private int num;//购买数量
	private Good good;//购买商品
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", price=" + price + ", num=" + num + ", good=" + good  + "]";
	}
	public OrderItem(double price, int num, Good good) {
		super();
		this.price = price;
		this.num = num;
		this.good = good;

	}
	public OrderItem() {
		super();
	}
	
}
