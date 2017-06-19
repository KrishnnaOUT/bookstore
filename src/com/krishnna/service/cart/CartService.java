package com.krishnna.service.cart;

import com.krishnna.dao.cart.CartDao;

/**
 * 购物车的业务逻辑层
 * @author Ningkui
 *
 */
public class CartService {

	/**
	 * 获得CartDao对象	
	 */
	private CartDao cartDao;
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
}
