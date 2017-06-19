package com.krishnna.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车类
 * @author Ningkui
 *
 */
public class Cart {
	/*
	 * 存储条目的集合
	 */
	private Map<String,CartItem> cartItemMap = new LinkedHashMap<String,CartItem>();
	
	public Map<String, CartItem> getCartItemMap() {
		return cartItemMap;
	}

	public void setCartItemMap(Map<String, CartItem> cartItemMap) {
		this.cartItemMap = cartItemMap;
	}

	/**
	 * 清空购物车
	 */
	public void clear(){
		cartItemMap.clear();
	}

	/**
	 * 计算合计
	 * @return
	 */
	public double getTotal() {
		// 合计=所有条目的小计之和
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : cartItemMap.values()) {
			BigDecimal subtotal = new BigDecimal("" + cartItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	
	/**
	 * 清除购物车中指定商品条目
	 */
	public void delete(CartItem cartItem){
		cartItemMap.remove(cartItem);
	}
	
	/**
	 * 添加商品到购物车
	 */
	public void add(CartItem cartItem) {
		if(cartItemMap.containsKey(cartItem.getGood().getGid())) {//判断原来车中是否存在该条目
			System.out.println("若存在重复的条目。。。");
			CartItem _cartItem = cartItemMap.get(cartItem.getGood().getGid());//返回原条目
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());//设置老条目的数量为，其自己数量+新条目的数量
			cartItemMap.put(cartItem.getGood().getGid(), _cartItem);
		} else {
			System.out.println("直接添加条目。。。");
			cartItemMap.put(cartItem.getGood().getGid(), cartItem);
		}
	}
	
	/**
	 * 获取所有条目
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return cartItemMap.values();
	}
	
}
