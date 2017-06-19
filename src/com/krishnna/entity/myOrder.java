package com.krishnna.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ������
 * @author Ningkui
 *
 */
public class myOrder {

	private String oid;//�������
	private Date ordertime;//���ɶ���ʱ��
	private double total; //�����ܼ�
	private int state;//����״̬��1Ϊδ���2Ϊ�Ѹ��δ������3Ϊ�Է�����δȷ���ջ���4Ϊȷ���ջ����׳ɹ�
	private String address;//������ַ
	
	/*
	 * ����������
	 */
	private User owner;
	
	/*
	 * ���ϣ�װ������ָ�����������ж�����Ŀ
	 */
	private Set<OrderItem> orderItemList = new HashSet<OrderItem>();
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setTotal() {
		for (OrderItem orderItem : orderItemList) {
			this.total = this.total + orderItem.getSubtotal();
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(Set<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	
}
