package cn.edu.zhku.jsj.Model;

public class Good {
	private int id;
	private String name;
	private double price;
	private int totalNum;
	private int remainNum;
	private String pic;
	private String introduction;
	private int type;//类型
	private int state;//状态：0上架，1下架
	private User user;//店铺
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public Good(String name, double price, int totalNum, int remainNum, String pic, String introduction, int type,
			int state) {
		super();
		this.name = name;
		this.price = price;
		this.totalNum = totalNum;
		this.remainNum = remainNum;
		this.pic = pic;
		this.introduction = introduction;
		this.type = type;
		this.state = state;
	}
	public Good() {
		super();
	}
	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + name + ", price=" + price + ", totalNum=" + totalNum + ", remainNum="
				+ remainNum + ", pic=" + pic + ", introduction=" + introduction + ", type=" + type + ", state=" + state
				+ "]";
	}
	
	
}
