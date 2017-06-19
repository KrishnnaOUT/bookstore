package com.krishnna.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ��
 * @author Ningkui
 *
 */
public class Cart {
	/*
	 * �洢��Ŀ�ļ���
	 */
	private Map<String,CartItem> cartItemMap = new LinkedHashMap<String,CartItem>();
	
	public Map<String, CartItem> getCartItemMap() {
		return cartItemMap;
	}

	public void setCartItemMap(Map<String, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}

	/**
	 * ��չ��ﳵ
	 */
	public void clear(){
		cartItemMap.clear();
	}

	/**
	 * ����ϼ�
	 * @return
	 */
	public double getTotal() {
		// �ϼ�=������Ŀ��С��֮��
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : cartItemMap.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	/**
	 * ������ﳵ��ָ����Ʒ��Ŀ
	 */
	public void delete(CartItem cartItem){
		cartItemMap.remove(cartItem);
	}
	
	/**
	 * �����Ʒ�����ﳵ
	 */
	public void add(CartItem cartItem) {
		if(cartItemMap.containsKey(cartItem.getGood().getGid())) {//�ж�ԭ�������Ƿ���ڸ���Ŀ
			System.out.println("�������ظ�����Ŀ������");
			CartItem _cartItem = cartItemMap.get(cartItem.getGood().getGid());//����ԭ��Ŀ
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());//��������Ŀ������Ϊ�����Լ�����+����Ŀ������
			cartItemMap.put(cartItem.getGood().getGid(), _cartItem);
		} else {
			System.out.println("ֱ�������Ŀ������");
			cartItemMap.put(cartItem.getGood().getGid(), cartItem);
		}
	}
	
	/**
	 * ��ȡ������Ŀ
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return cartItemMap.values();
	}
	
}
