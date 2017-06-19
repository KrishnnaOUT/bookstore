package com.krishnna.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 * @author Ningkui
 *
 */
/**
 * @author Ningkui
 *
 */
public class User {

	private String uid;//用户Id
	private String username;//用户名	
	private String password;//用户密码
	private String code;//发送到用户邮箱的验证码
	private String email;//用户邮箱
	private String phone;//用户手机联系方式
	private boolean active;//用户是否激活]
	
	//一对多。与地址建立关联
	private Set<Address> addressSet = new HashSet<Address>();//收货地址集合
	
	//一对多。与订单建立关联
	private Set<myOrder> orderSet = new HashSet<myOrder>();
	
	//一对一
	Store store;//关联商店
	
	public Set<Address> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Set<myOrder> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<myOrder> orderSet) {
		this.orderSet = orderSet;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public User(String uid, String username, String password, String code, String email, String phone, boolean active) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.code = code;
		this.email = email;
		this.phone = phone;
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", code=" + code + ", email="
				+ email + ", phone=" + phone + ", active=" + active + ", addressSet=" + addressSet + ", orderSet="
				+ orderSet + ", store=" + store + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}
