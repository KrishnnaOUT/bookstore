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
	 * 属性封装
	 */
	private Good good = new Good();
	public Good getModel() {
		return good;
	}

	/**
	 * 获得CartService对象
	 */
	private CartService cartService;
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	/**
	 * 获得request对象
	 */
	HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * 无操作。直接跳转到购物车页面
	 */
	public String runToCart() throws Exception {
		return "SUCCESS";
	}



	/**
	 * 加入购物车
	 * -->跳转到购物车页面
	 * @return
	 */
	public String addToCart(){
		
		//获得购买数量
		String count = request.getParameter("count");
		//创建一个购物车条目
		CartItem cartItem = new CartItem();
		cartItem.setGood(good);
		cartItem.setCount(Integer.parseInt(count));
		
		//获得购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		/*
		 * 添加购物车条目
		 */
		cart.add(cartItem);
		//再次保存cart进入session中
		request.getSession().setAttribute("cart", cart);
		
		return "SUCCESS";
	}

}
