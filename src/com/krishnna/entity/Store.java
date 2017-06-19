package com.krishnna.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * �̵�ʵ����
 * @author Ningkui
 *
 */
public class Store {

	private String sid;//�̵���
	private String storeName;//�̵�����
	private String storeLogo;//�̵�logo��ַ
	private int shopClass;//�̵�ȼ���10Ϊ��ߣ����һ��
	private Date registDate;//ע��ʱ��
	
	//һ�Զࡣ��myOrder���ù���
	Set<myOrder> storeOrderSet = new HashSet<myOrder>();//�̵궩��
	
	//һ�Զࡣ��Good���ù���
	Set<Good> goodSet = new HashSet<Good>();
	
	//���һ����User�������ù���
	private User user;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public int getShopClass() {
		return shopClass;
	}

	public void setShopClass(int shopClass) {
		this.shopClass = shopClass;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Set<myOrder> getStoreOrderSet() {
		return storeOrderSet;
	}

	public void setStoreOrderSet(Set<myOrder> storeOrderSet) {
		this.storeOrderSet = storeOrderSet;
	}

	public Set<Good> getGoodSet() {
		return goodSet;
	}

	public void setGoodSet(Set<Good> goodSet) {
		this.goodSet = goodSet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Store [sid=" + sid + ", storeName=" + storeName + ", storeLogo=" + storeLogo + ", shopClass="
				+ shopClass + ", registDate=" + registDate + ", storeOrderSet=" + storeOrderSet + ", goodSet=" + goodSet
				+ ", user=" + user + "]";
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(String sid, String storeName, String storeLogo, int shopClass, Date registDate,
			Set<myOrder> storeOrderSet, Set<Good> goodSet, User user) {
		super();
		this.sid = sid;
		this.storeName = storeName;
		this.storeLogo = storeLogo;
		this.shopClass = shopClass;
		this.registDate = registDate;
		this.storeOrderSet = storeOrderSet;
		this.goodSet = goodSet;
		this.user = user;
	}

	
}
