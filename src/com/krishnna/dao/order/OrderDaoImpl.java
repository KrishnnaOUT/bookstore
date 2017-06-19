package com.krishnna.dao.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.krishnna.entity.myOrder;
import com.krishnna.entity.OrderItem;
import com.krishnna.entity.User;

/**
 * 订单的dao层，处理和数据库的交互
 * @author Ningkui
 *
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * 获得hibernateTemplate对象
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 提交订单
	 */
	@Override
	public void commitOrder(myOrder order) {
		
		//保存订单
		hibernateTemplate.save(order);
		
	}

	/**
	 * 提交订单条目
	 */
	@Override
	public void commitOrderItem(Set<OrderItem> set) {
		//保存订单条目
		System.out.println("保存订单条目......");
		System.out.println(set);
		for (OrderItem orderItem : set) {
			System.out.println("进入for循环中.........");
			hibernateTemplate.save(orderItem);
		}
		
	}

	/**
	 * 根据当前用户找出其所属的所有Order
	 */
	@Override
	public List<myOrder> findOrderList(User owner) {
		
		String sql = "from myOrder orders where orders.owner.uid=?";
		@SuppressWarnings("unchecked")
		List<myOrder> orderList = (List<myOrder>) hibernateTemplate.find(sql, owner.getUid());
		return orderList;
	}

	/**
	 * 根据oid查询对应订单
	 */
	@Override
	public myOrder findOrder(String oid) {
		
		String sql = "from myOrder orders where orders.oid=?";
		
		@SuppressWarnings("unchecked")
		List<myOrder> orderList = (List<myOrder>) hibernateTemplate.find(sql, oid);
		return orderList.get(0);
	}

	/**
	 * 根据oid更新对应订单的内容
	 */
	@Override
	public void updateOrderByOid(String hql,Object[] params) {
		hibernateTemplate.bulkUpdate(hql, params);
	}
	
}
