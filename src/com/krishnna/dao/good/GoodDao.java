package com.krishnna.dao.good;

import java.util.List;

import com.krishnna.entity.Good;

/**
 * 商品模块dao层接口
 * @author Ningkui
 *
 */
public interface GoodDao {

	/**
	 * 商品查询方法
	 * @param hql :: 传入hql语句，为了查询
	 * @param params :: 传入对应于hql语句中占位符的参数值
	 * @return
	 */
	List<Good> find(String hql, Object[] params);

	/**
	 * 使用hibernateTemplate.findByCriteria方法查询
	 * @param obj :: 主要操作的类
	 * @param params :: 参数集合
	 * @return
	 */
	List<Good> findBycriteria(Object obj, Object[] params);
}
