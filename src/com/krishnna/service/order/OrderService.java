package com.krishnna.service.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.krishnna.dao.order.OrderDao;
import com.krishnna.entity.myOrder;
import com.krishnna.entity.User;

/**
 * 订单的业务逻辑层
 * @author Ningkui
 *
 */
@Transactional
public class OrderService {

	/**
	 * 获得OrderDao对象	
	 */
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 *	提交订单 
	 */
	public void commitOrder(myOrder order) {
		
		/*
		 * 调用dao层方法。更新数据库
		 * 保存订单
		 */
		orderDao.commitOrder(order);
		/*
		 * 调用dao层方法。更新数据库
		 * 保存订单条目
		 */
		orderDao.commitOrderItem(order.getOrderItemList());
		
		
	}

	/**
	 * 根据当前登录用户获取其所有订单
	 * @param owner
	 * @return
	 */
	public List<myOrder> findAllOrderByUser(User owner) {
		
		/**
		 * 查询数据库，找出当前用户的所有订单
		 */
		List<myOrder> orderList = orderDao.findOrderList(owner);
		return orderList;
		
	}
	
	/**
	 * 根据oid查询对应编号的订单。
	 */
	public myOrder findOrderByOid(String oid) {
		/*
		 * 查询数据库。找出当前oid的订单
		 */
		myOrder order = orderDao.findOrder(oid);
		return order;
	}
	
	/**
	 * 根据oid更新订单当前状态
	 * oid  当前订单编号
	 * state 需要修改成的值。
	 */
	public void updateOrderState(String oid,int state){
		
		String hql = "update myOrder orders set orders.state=? where orders.oid=?";
		
		orderDao.updateOrderByOid(hql, new Object[]{state,oid});
	}
	
}
