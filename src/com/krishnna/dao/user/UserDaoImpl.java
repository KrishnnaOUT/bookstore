package com.krishnna.dao.user;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.krishnna.entity.User;

/**
 * 用户模块-->dao层
 * @author Ningkui
 *
 */
public class UserDaoImpl implements UserDao {

	/**
	 * 得到hibernateTemplate对象
	 */
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 查询数据库。完成登录验证
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> find(User user) {
		
		List<User> userList = null;
		
		try {
			String hql = "from User user where user.username=? and user.password=?";
			userList = (List<User>) hibernateTemplate.find(hql, user.getUsername(),user.getPassword());
		} catch (Exception e) {
			System.out.println("登录查询用户名是否注册Dao层错误："+e);
		}

		return userList;
	}

	/*
	 * 将新用户注册存进数据库中
	 */
	@Override
	public String update(User user) {
		
		System.out.println("进入dao层下的update函数......");
		hibernateTemplate.save(user);
		return null;
	}

	/**
	 *  注册之前验证数据是否唯一
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String confirm(User user) {
		
		/*
		 *  验证用户名，邮箱。手机号是否已被注册
		 */
		//验证用户名
		System.out.println("进入验证方法");
		if (user.getUsername() != null) {
			System.out.println("进入验证方法，验证username");
			List<User> userlist1 = (List<User>) hibernateTemplate.find("from User user where user.username=?",
					user.getUsername());
			System.out.println("验证username的查询结果：" + userlist1);
			// 若userList不为空。则返回真
			if (!userlist1.isEmpty()) {
				return "1";
			}
		}
		// 验证邮箱
		if (user.getEmail() != null) {
			System.out.println("进入验证方法，验证email");
			List<User> userlist2 = (List<User>) hibernateTemplate.find("from User user where user.email=?",
					user.getEmail());
			System.out.println("验证email的查询结果：" + userlist2);
			// 若userList不为空。则返回真
			if (!userlist2.isEmpty()) {
				return "1";
			}
		}
		// 验证手机号
		if (user.getPhone() != null) {
			System.out.println("进入验证方法，验证username");
			List<User> userlist3 = (List<User>) hibernateTemplate.find("from User user where user.phone=?",
					user.getPhone());
			System.out.println("验证phone的查询结果：" + userlist3);
			// 若userList不为空。则返回真
			if (!userlist3.isEmpty()) {
				System.out.println("dao返回了");
				return "1";
			}
		}
		
		return null;
	}
}
