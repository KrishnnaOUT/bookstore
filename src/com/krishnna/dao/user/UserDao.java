package com.krishnna.dao.user;

import java.util.List;

import com.krishnna.entity.User;

public interface UserDao {
	
	//写入新的数据。即注册方法
	public String  update(User user);
	
	//验证数据是否唯一方法
	public String confirm(User user);
	
	//查询数据。
	public List<User> find(User user);
}
