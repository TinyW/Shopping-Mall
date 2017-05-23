package cn.edu.zhku.jsj.Model;

public class Good {
	private int id;
	private String gname;
	private double gprice;
	private int gtotalNum;
	private int gremainNum;
	private String gpic;
	private String introduction;
	private String gtype;//类型
	private int gstate;//状态：0上架，1下架
	private User user;//店铺
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return gname;
	}
	public void setName(String name) {
		this.gname = name;
	}
	public double getPrice() {
		return gprice;
	}
	public void setPrice(double price) {
		this.gprice = price;
	}
	public int getTotalNum() {
		return gtotalNum;
	}
	public void setTotalNum(int totalNum) {
		this.gtotalNum = totalNum;
	}
	public int getRemainNum() {
		return gremainNum;
	}
	public void setRemainNum(int remainNum) {
		this.gremainNum = remainNum;
	}
	public String getPic() {
		return gpic;
	}
	public void setPic(String pic) {
		this.gpic = pic;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getType() {
		return gtype;
	}
	public void setType(String type) {
		this.gtype = type;
	}
	public int getState() {
		return gstate;
	}
	public void setState(int state) {
		this.gstate = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Good(String name, double price, int totalNum, int remainNum, String pic, String introduction, String type,
			int state) {
		super();
		this.gname = name;
		this.gprice = price;
		this.gtotalNum = totalNum;
		this.gremainNum = remainNum;
		this.gpic = pic;
		this.introduction = introduction;
		this.gtype = type;
		this.gstate = state;
	}
	public Good() {
		super();
	}
	@Override
	public String toString() {
		return "Good [id=" + id + ", name=" + gname + ", price=" + gprice + ", totalNum=" + gtotalNum + ", remainNum="
				+ gremainNum + ", pic=" + gpic + ", introduction=" + introduction + ", type=" + gtype + ", state=" + gstate
				+ "]";
	}
	
	
}
