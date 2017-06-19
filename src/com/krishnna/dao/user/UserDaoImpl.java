package com.krishnna.dao.user;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.krishnna.entity.User;

/**
 * �û�ģ��-->dao��
 * @author Ningkui
 *
 */
public class UserDaoImpl implements UserDao {

	/**
	 * �õ�hibernateTemplate����
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * ��ѯ���ݿ⡣��ɵ�¼��֤
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> find(User user) {
		
		List<User> userList = null;
		
		try {
			String hql = "from User user where user.username=? and user.password=?";
			userList = (List<User>) hibernateTemplate.find(hql, user.getUsername(),user.getPassword());
		} catch (Exception e) {
			System.out.println("��¼��ѯ�û����Ƿ�ע��Dao�����"+e);
		}

		return userList;
	}

	/*
	 * �����û�ע�������ݿ���
	 */
	@Override
	public String update(User user) {
		
		System.out.println("����dao���µ�update����......");
		hibernateTemplate.save(user);
		return null;
	}

	/**
	 *  ע��֮ǰ��֤�����Ƿ�Ψһ
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String confirm(User user) {
		
		/*
		 *  ��֤�û��������䡣�ֻ����Ƿ��ѱ�ע��
		 */
		//��֤�û���
		System.out.println("������֤����");
		if (user.getUsername() != null) {
			System.out.println("������֤��������֤username");
			List<User> userlist1 = (List<User>) hibernateTemplate.find("from User user where user.username=?",
					user.getUsername());
			System.out.println("��֤username�Ĳ�ѯ�����" + userlist1);
			// ��userList��Ϊ�ա��򷵻���
			if (!userlist1.isEmpty()) {
				return "1";
			}
		}
		// ��֤����
		if (user.getEmail() != null) {
			System.out.println("������֤��������֤email");
			List<User> userlist2 = (List<User>) hibernateTemplate.find("from User user where user.email=?",
					user.getEmail());
			System.out.println("��֤email�Ĳ�ѯ�����" + userlist2);
			// ��userList��Ϊ�ա��򷵻���
			if (!userlist2.isEmpty()) {
				return "1";
			}
		}
		// ��֤�ֻ���
		if (user.getPhone() != null) {
			System.out.println("������֤��������֤username");
			List<User> userlist3 = (List<User>) hibernateTemplate.find("from User user where user.phone=?",
					user.getPhone());
			System.out.println("��֤phone�Ĳ�ѯ�����" + userlist3);
			// ��userList��Ϊ�ա��򷵻���
			if (!userlist3.isEmpty()) {
				System.out.println("dao������");
				return "1";
			}
		}
		
		return null;
	}
}
