package com.krishnna.dao.good;

import java.sql.SQLException;
import java.util.List;

import javax.management.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.krishnna.entity.Good;

public class GoodDaoImpl implements GoodDao {

	/**
	 * ���hibernateTemplate����
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
	 * ��Ʒ��������ѯ����
	 * ��������Ʒ����-->������
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Good> findBycriteria(Object obj, Object[] params) {
		List<Good> goodlist = null;
		
		//����DetachedCriteria
		DetachedCriteria criteria = DetachedCriteria.forClass(obj.getClass());
		//�����������
		criteria.addOrder(Order.desc((params[0].toString())));
		/*
		 * ������ѯ����Ӳ���
		 * criteria
		 * firstResult ����ʼλ��
		 * MaxResult���������
		 */
		
		goodlist = (List<Good>) hibernateTemplate.findByCriteria(criteria, Integer.parseInt(params[1].toString()), Integer.parseInt(params[2].toString()));
		
		return goodlist;
	}


	@Override
	public List<Good> find(String hql, Object[] params) {
		
		List<Good> goodList = null;
		
		goodList = (List<Good>) hibernateTemplate.find(hql, params);
		
		return goodList;
	}

}
