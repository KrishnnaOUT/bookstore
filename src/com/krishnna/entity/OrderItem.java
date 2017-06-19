package com.krishnna.entity;

import java.math.BigDecimal;

/**
 * ������Ŀ��
 * @author Ningkui
 *
 */
public class OrderItem {

	private String iid;//������Ŀ���
	private int count;//��Ʒ����
	private double subtotal;//С��
	
	private myOrder order;//����Ŀ�����Ķ���
	private Good good;//������Ŀ�е���Ʒ
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public myOrder getOrder() {
		return order;
	}
	public void setOrder(myOrder order) {
		this.order = order;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void setSubtotal() {
		BigDecimal d1 = new BigDecimal(good.getPrice() + "");
		BigDecimal d2 = new BigDecimal(count + "");
		this.subtotal = d1.multiply(d2).doubleValue();
	}
	
	
}
