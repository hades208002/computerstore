package com.computerstore.domain;

public class Products {
	
	private String pid;
	private String pname;
	private double price;
	private String category;
	private String cputype;
	private String gputype;
	private String memtype;
	private int pnum;
	private String imgurl;
	private String description;
	private int tag;
	
	public Products() {}
	public Products(String pid, String pname, double price, String category,String cputype,String gputype,String memtype,
			int pnum, String imgurl, String description) {
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.category = category;
		this.cputype = cputype;
		this.gputype = gputype;
		this.memtype = memtype;
		this.pnum = pnum;
		this.imgurl = imgurl;
		this.description = description;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCputype() {
		return cputype;
	}
	public void setCputype(String cputype) {
		this.cputype = cputype;
	}
	public String getGputype() {
		return gputype;
	}
	public void setGputype(String gputype) {
		this.gputype = gputype;
	}
	public String getMemtype() {
		return memtype;
	}
	public void setMemtype(String memtype) {
		this.memtype = memtype;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Products [pid=" + pid + ", pname=" + pname + ", price=" + price
				+ ", category=" + category + ", cputype=" + cputype + ",gputype=" + gputype + ", memtype=" + memtype + ", pnum=" + pnum + ", imgurl="
				+ imgurl + ", description=" + description + "]";
	}
	
}
