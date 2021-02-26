package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AbstractUserDAO;
import com.model.User;

@Service("userLoginService")
@Transactional
public class UserLoginService implements UserLoginServiceInterface {
	
	@Autowired
	private AbstractUserDAO userDAO;

	public AbstractUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(AbstractUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void registerUser(User user) {
		
		this.userDAO.createUser(user);
		
	}
	
	@Override
	public boolean checkUserExists(String uname, String upass) {
		
		User user = this.userDAO.findUserByUname(uname);
		
		return user != null;
		
	}
	
	@Override
	public void loginUser(String uname) {
		
		User loginUser = this.userDAO.findUserByUname(uname);
		loginUser.setFlag(1);
		this.userDAO.updateUser(loginUser);
		
	}
	
	@Override
	public void logoutUser(String uname) {
		
		User loginUser = this.userDAO.findUserByUname(uname);
		loginUser.setFlag(0);
		this.userDAO.updateUser(loginUser);
		
	}
	
	@Override
	public boolean checkIfLoggedIn(String uname) {
		
		User user = this.userDAO.findUserByUname(uname);
		
		return user.getFlag() == 1;
		
	}
	
	@Override
	public User getUserByUname(String uname) {
		
		return this.userDAO.findUserByUname(uname);
		
	}

}
