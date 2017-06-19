package com.krishnna.dao.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.krishnna.entity.myOrder;
import com.krishnna.entity.OrderItem;
import com.krishnna.entity.User;

/**
 * dao��ӿ�
 * @author Ningkui
 *
 */
public interface OrderDao {

	//�ύ����
	void commitOrder(myOrder order);
	
	//�ύ������Ŀ
	void commitOrderItem(Set<OrderItem> set);

	//�ҳ�����order
	List<myOrder> findOrderList(User owner);

	//����oid�ҳ���Ӧorder
	myOrder findOrder(String oid);
	
	/**
	 * ����oid�޸Ķ�Ӧ����״̬
	 * @param oid �������
	 * @param hql ���µ�hql���
	 * @param params hql����еĲ���
	 */
	void updateOrderByOid(String hql,Object[] params);
	

}
