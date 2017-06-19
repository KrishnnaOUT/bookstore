package com.krishnna.dao.good;

import java.util.List;

import com.krishnna.entity.Good;

/**
 * ��Ʒģ��dao��ӿ�
 * @author Ningkui
 *
 */
public interface GoodDao {

	/**
	 * ��Ʒ��ѯ����
	 * @param hql :: ����hql��䣬Ϊ�˲�ѯ
	 * @param params :: �����Ӧ��hql�����ռλ���Ĳ���ֵ
	 * @return
	 */
	List<Good> find(String hql, Object[] params);

	/**
	 * ʹ��hibernateTemplate.findByCriteria������ѯ
	 * @param obj :: ��Ҫ��������
	 * @param params :: ��������
	 * @return
	 */
	List<Good> findBycriteria(Object obj, Object[] params);
}
