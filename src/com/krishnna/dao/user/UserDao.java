package com.krishnna.dao.user;

import java.util.List;

import com.krishnna.entity.User;

public interface UserDao {
	
	//д���µ����ݡ���ע�᷽��
	public String  update(User user);
	
	//��֤�����Ƿ�Ψһ����
	public String confirm(User user);
	
	//��ѯ���ݡ�
	public List<User> find(User user);
}
