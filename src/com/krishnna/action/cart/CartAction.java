package com.krishnna.action.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.krishnna.entity.Cart;
import com.krishnna.entity.CartItem;
import com.krishnna.entity.Good;
import com.krishnna.service.cart.CartService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CartAction extends ActionSupport implements ModelDriven<Good>{
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���Է�װ
	 */
	private Good good = new Good();
	public Good getModel() {
		return good;
	}

	/**
	 * ���CartService����
	 */
	private CartService cartService;
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	/**
	 * ���request����
	 */
	HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * �޲�����ֱ����ת�����ﳵҳ��
	 */
	public String runToCart() throws Exception {
		return "SUCCESS";
	}



	/**
	 * ���빺�ﳵ
	 * -->��ת�����ﳵҳ��
	 * @return
	 */
	public String addToCart(){
		
		//��ù�������
		String count = request.getParameter("count");
		//����һ�����ﳵ��Ŀ
		CartItem cartItem = new CartItem();
		cartItem.setGood(good);
		cartItem.setCount(Integer.parseInt(count));
		
		//��ù��ﳵ
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		/*
		 * ��ӹ��ﳵ��Ŀ
		 */
		cart.add(cartItem);
		//�ٴα���cart����session��
		request.getSession().setAttribute("cart", cart);
		
		return "SUCCESS";
	}

}
