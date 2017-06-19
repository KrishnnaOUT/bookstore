package com.krishnna.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * �û�ʵ����
 * @author Ningkui
 *
 */
/**
 * @author Ningkui
 *
 */
public class User {

	private String uid;//�û�Id
	private String username;//�û���	
	private String password;//�û�����
	private String code;//���͵��û��������֤��
	private String email;//�û�����
	private String phone;//�û��ֻ���ϵ��ʽ
	private boolean active;//�û��Ƿ񼤻�]
	
	//һ�Զࡣ���ַ��������
	private Set<Address> addressSet = new HashSet<Address>();//�ջ���ַ����
	
	//һ�Զࡣ�붩����������
	private Set<myOrder> orderSet = new HashSet<myOrder>();
	
	//һ��һ
	Store store;//�����̵�
	
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
