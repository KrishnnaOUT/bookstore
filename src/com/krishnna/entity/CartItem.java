package com.krishnna.entity;

import java.math.BigDecimal;

/**
 * ���ﳵ��Ŀ��
 * @author Ningkui
 *
 */
public class CartItem {

	private Good good;//ͼ��
	private int count;//����
	
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
	
	/**
	 * С�Ʒ���
	 * @return
	 * �����˶����������������
	 */
	public double getSubtotal() {//С�Ʒ���������û�ж�Ӧ�ĳ�Ա��
		BigDecimal d1 = new BigDecimal(good.getPrice() + "");
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}
}
