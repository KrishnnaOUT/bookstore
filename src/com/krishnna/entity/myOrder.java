package com.krishnna.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 订单类
 * @author Ningkui
 *
 */
public class myOrder {

	private String oid;//订单编号
	private Date ordertime;//生成订单时间
	private double total; //订单总计
	private int state;//订单状态，1为未付款，2为已付款但未发货，3为以发货但未确认收货，4为确认收货交易成功
	private String address;//发货地址
	
	/*
	 * 订单所有人
	 */
	private User owner;
	
	/*
	 * 集合，装着隶属指定订单的所有订单条目
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
