package com.krishnna.entity;

import java.util.Date;

/**
 * 商品实体类
 * @author Ningkui
 *
 */
public class Good {

	private String gid; //商品id
	private String gname;//商品名称
	private String storeName;//商店名称
	private float price; //商品价格
	private String briefing;//商品简介
	private int salesNum; //销售量
	private String picture;//封面图片地址
	private String bookBigClass;//图书大分类
	private String bookSmallClass;//图书细类
	private Date bookDate;//图书入库时间
	
	//多对一。关联商家。Store
	Store store;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getBriefing() {
		return briefing;
	}

	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}

	public int getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getBookBigClass() {
		return bookBigClass;
	}

	public void setBookBigClass(String bookBigClass) {
		this.bookBigClass = bookBigClass;
	}

	public String getBookSmallClass() {
		return bookSmallClass;
	}

	public void setBookSmallClass(String bookSmallClass) {
		this.bookSmallClass = bookSmallClass;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public String toString() {
		return "Good [gid=" + gid + ", gname=" + gname + ", storeName=" + storeName + ", price=" + price + ", briefing="
				+ briefing + ", salesNum=" + salesNum + ", picture=" + picture + ", bookBigClass=" + bookBigClass
				+ ", bookSmallClass=" + bookSmallClass + ", bookDate=" + bookDate + ", store=" + store + "]";
	}

	public Good() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Good(String gid, String gname, String storeName, float price, String briefing, int salesNum, String picture,
			String bookBigClass, String bookSmallClass, Date bookDate, Store store) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.storeName = storeName;
		this.price = price;
		this.briefing = briefing;
		this.salesNum = salesNum;
		this.picture = picture;
		this.bookBigClass = bookBigClass;
		this.bookSmallClass = bookSmallClass;
		this.bookDate = bookDate;
		this.store = store;
	}

	
	
}
