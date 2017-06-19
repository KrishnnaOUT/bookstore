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
	 * 获得hibernateTemplate对象
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
	 * 商品的条件查询方法
	 * 参数：商品对象。-->即条件
	 */
	@SuppressWarnings({ "unchecked"})
	@Override
	public List<Good> findBycriteria(Object obj, Object[] params) {
		List<Good> goodlist = null;
		
		//创建DetachedCriteria
		DetachedCriteria criteria = DetachedCriteria.forClass(obj.getClass());
		//添加排序条件
		criteria.addOrder(Order.desc((params[0].toString())));
		/*
		 * 条件查询。添加参数
		 * criteria
		 * firstResult ：起始位置
		 * MaxResult：最大条数
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
