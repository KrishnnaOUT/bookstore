package com.krishnna.dao.cart;

import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 *���ﳵ��dao�㣬��������ݿ�Ľ���
 * @author Ningkui
 *
 */
public class CartDaoImpl implements CartDao {

	/**
	 * ���hibernateTemplate����
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
