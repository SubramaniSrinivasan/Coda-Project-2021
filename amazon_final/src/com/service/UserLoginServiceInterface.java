package com.service;

import com.model.User;

public interface UserLoginServiceInterface {
	
	public boolean checkUserExists(String uname, String upass);
	public void registerUser(User user);
	public void loginUser(String uname);
	public void logoutUser(String uname);
	public boolean checkIfLoggedIn(String uname);
	public User getUserByUname(String uname);
	
}