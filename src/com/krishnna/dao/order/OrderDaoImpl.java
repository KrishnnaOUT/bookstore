package com.krishnna.dao.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.krishnna.entity.myOrder;
import com.krishnna.entity.OrderItem;
import com.krishnna.entity.User;

/**
 * ������dao�㣬��������ݿ�Ľ���
 * @author Ningkui
 *
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * ���hibernateTemplate����
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * �ύ����
	 */
	@Override
	public void commitOrder(myOrder order) {
		
		//���涩��
		hibernateTemplate.save(order);
		
	}

	/**
	 * �ύ������Ŀ
	 */
	@Override
	public void commitOrderItem(Set<OrderItem> set) {
		//���涩����Ŀ
		System.out.println("���涩����Ŀ......");
		System.out.println(set);
		for (OrderItem orderItem : set) {
			System.out.println("����forѭ����.........");
			hibernateTemplate.save(orderItem);
		}
		
	}

	/**
	 * ���ݵ�ǰ�û��ҳ�������������Order
	 */
	@Override
	public List<myOrder> findOrderList(User owner) {
		
		String sql = "from myOrder orders where orders.owner.uid=?";
		@SuppressWarnings("unchecked")
		List<myOrder> orderList = (List<myOrder>) hibernateTemplate.find(sql, owner.getUid());
		return orderList;
	}

	/**
	 * ����oid��ѯ��Ӧ����
	 */
	@Override
	public myOrder findOrder(String oid) {
		
		String sql = "from myOrder orders where orders.oid=?";
		
		@SuppressWarnings("unchecked")
		List<myOrder> orderList = (List<myOrder>) hibernateTemplate.find(sql, oid);
		return orderList.get(0);
	}

	/**
	 * ����oid���¶�Ӧ����������
	 */
	@Override
	public void updateOrderByOid(String hql,Object[] params) {
		hibernateTemplate.bulkUpdate(hql, params);
	}
	
}
