package com.krishnna.dao.cart;

import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 *购物车的dao层，处理和数据库的交互
 * @author Ningkui
 *
 */
public class CartDaoImpl implements CartDao {

	/**
	 * 获得hibernateTemplate对象
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
