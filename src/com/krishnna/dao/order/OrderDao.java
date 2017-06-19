package com.krishnna.dao.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.krishnna.entity.myOrder;
import com.krishnna.entity.OrderItem;
import com.krishnna.entity.User;

/**
 * dao层接口
 * @author Ningkui
 *
 */
public interface OrderDao {

	//提交订单
	void commitOrder(myOrder order);
	
	//提交订单条目
	void commitOrderItem(Set<OrderItem> set);

	//找出所有order
	List<myOrder> findOrderList(User owner);

	//根据oid找出对应order
	myOrder findOrder(String oid);
	
	/**
	 * 根据oid修改对应订单状态
	 * @param oid 订单编号
	 * @param hql 更新的hql语句
	 * @param params hql语句中的参数
	 */
	void updateOrderByOid(String hql,Object[] params);
	

}
