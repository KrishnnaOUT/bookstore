package com.krishnna.service.user;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.krishnna.dao.user.UserDao;
import com.krishnna.dao.user.UserDaoImpl;
import com.krishnna.entity.User;
import com.krishnna.util.commonUtil;

/**
 * 用户模块-->业务逻辑层
 * @author Ningkui
 *
 */
@Transactional
public class UserService {

	private User user;
	
	/**
	 * 得到UserDao对象
	 */
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 *  补全User
	 */
	public void userSupplement(User user){

		/**
		 *  补全用户ID
		 *  使用自定义类生成32位验证码（commonUtil）
		 */
		String uid = new commonUtil().getCode();
		user.setUid(uid);
		
		/**
		 *  补全验证码
		 *  使用自定义类生成32位验证码（commonUtil）
		 */
		if(user.getCode() == null ){
			String code = new commonUtil().getCode() + new commonUtil().getCode();
			user.setCode(code);
		}
		
		/**
		 *  补全激活状态，默认注册时为未激活状态
		 */
		if("".equals(user.isActive())){
			user.setActive(false);
		}
	}

	/**
	 * 用户登录验证
	 */
	public User login(User user){
		
		//调用UserDaoImpl层方法,访问数据库
		List<User> userList = userDao.find(user);
		//若userList不为空。则返回值为true
		if(userList != null && userList.size() > 0){
			return userList.get(0);
		}

		//默认返回false，即不存在此用户
		return null;
	}
	
	/**
	 * 注册（新用户存入数据库）
	 * 返回值为验证码
	 */
	public String regist(User user){

		System.out.println("进入service层下regist方法......");
		//调用本地方法，补全User对象
		this.userSupplement(user);
		//调用UserDaoImpl层方法,访问数据库
		System.out.println(user);
		userDao.update(user);
		
		return user.getCode();
	}
	
	/**
	 *  注册之前进行Ajax验证值是否唯一。
	 */
	public String registConfirm(User user){
		
		/*
		 *  验证用户名，邮箱。手机号是否已被注册
		 *  若为真,则返回
		 */
		String flag = userDao.confirm(user);
		//若flag不为空。则返回flag
		if(flag != null){
			System.out.println("service返回了");
			return flag;
		}
		
		return null;
	}
}
