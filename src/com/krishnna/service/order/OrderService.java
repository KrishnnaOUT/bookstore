package com.krishnna.service.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.krishnna.dao.order.OrderDao;
import com.krishnna.entity.myOrder;
import com.krishnna.entity.User;

/**
 * ������ҵ���߼���
 * @author Ningkui
 *
 */
@Transactional
public class OrderService {

	/**
	 * ���OrderDao����	
	 */
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 *	�ύ���� 
	 */
	public void commitOrder(myOrder order) {
		
		/*
		 * ����dao�㷽�����������ݿ�
		 * ���涩��
		 */
		orderDao.commitOrder(order);
		/*
		 * ����dao�㷽�����������ݿ�
		 * ���涩����Ŀ
		 */
		orderDao.commitOrderItem(order.getOrderItemList());
		
		
	}

	/**
	 * ���ݵ�ǰ��¼�û���ȡ�����ж���
	 * @param owner
	 * @return
	 */
	public List<myOrder> findAllOrderByUser(User owner) {
		
		/**
		 * ��ѯ���ݿ⣬�ҳ���ǰ�û������ж���
		 */
		List<myOrder> orderList = orderDao.findOrderList(owner);
		return orderList;
		
	}
	
	/**
	 * ����oid��ѯ��Ӧ��ŵĶ�����
	 */
	public myOrder findOrderByOid(String oid) {
		/*
		 * ��ѯ���ݿ⡣�ҳ���ǰoid�Ķ���
		 */
		myOrder order = orderDao.findOrder(oid);
		return order;
	}
	
	/**
	 * ����oid���¶�����ǰ״̬
	 * oid  ��ǰ�������
	 * state ��Ҫ�޸ĳɵ�ֵ��
	 */
	public void updateOrderState(String oid,int state){
		
		String hql = "update myOrder orders set orders.state=? where orders.oid=?";
		
		orderDao.updateOrderByOid(hql, new Object[]{state,oid});
	}
	
}
