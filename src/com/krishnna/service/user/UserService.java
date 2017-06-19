package com.krishnna.service.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.krishnna.dao.user.UserDao;
import com.krishnna.dao.user.UserDaoImpl;
import com.krishnna.entity.User;
import com.krishnna.util.commonUtil;

/**
 * �û�ģ��-->ҵ���߼���
 * @author Ningkui
 *
 */
@Transactional
public class UserService {

	private User user;
	
	/**
	 * �õ�UserDao����
	 */
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 *  ��ȫUser
	 */
	public void userSupplement(User user){

		/**
		 *  ��ȫ�û�ID
		 *  ʹ���Զ���������32λ��֤�루commonUtil��
		 */
		String uid = new commonUtil().getCode();
		user.setUid(uid);
		
		/**
		 *  ��ȫ��֤��
		 *  ʹ���Զ���������32λ��֤�루commonUtil��
		 */
		if(user.getCode() == null ){
			String code = new commonUtil().getCode() + new commonUtil().getCode();
			user.setCode(code);
		}
		
		/**
		 *  ��ȫ����״̬��Ĭ��ע��ʱΪδ����״̬
		 */
		if("".equals(user.isActive())){
			user.setActive(false);
		}
	}

	/**
	 * �û���¼��֤
	 */
	public User login(User user){
		
		//����UserDaoImpl�㷽��,�������ݿ�
		List<User> userList = userDao.find(user);
		//��userList��Ϊ�ա��򷵻�ֵΪtrue
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}

		//Ĭ�Ϸ���false���������ڴ��û�
		return null;
	}
	
	/**
	 * ע�ᣨ���û��������ݿ⣩
	 * ����ֵΪ��֤��
	 */
	public String regist(User user){

		System.out.println("����service����regist����......");
		//���ñ��ط�������ȫUser����
		this.userSupplement(user);
		//����UserDaoImpl�㷽��,�������ݿ�
		System.out.println(user);
		userDao.update(user);
		
		return user.getCode();
	}
	
	/**
	 *  ע��֮ǰ����Ajax��ֵ֤�Ƿ�Ψһ��
	 */
	public String registConfirm(User user){
		
		/*
		 *  ��֤�û��������䡣�ֻ����Ƿ��ѱ�ע��
		 *  ��Ϊ��,�򷵻�
		 */
		String flag = userDao.confirm(user);
		//��flag��Ϊ�ա��򷵻�flag
		if(flag != null){
			System.out.println("service������");
			return flag;
		}
		
		return null;
	}
}
