package com.dao;

import com.model.User;

public abstract class AbstractUserDAO {
	
	public abstract User findUserByUid(Integer uid);
	public abstract User findUserByUname(String uname);
	public abstract void createUser(User user);
	public abstract void updateUser(User user);
	
}
