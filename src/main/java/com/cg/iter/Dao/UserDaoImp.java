package com.cg.iter.Dao;

import java.util.HashMap;
import java.util.Map;

import com.cg.iter.Exception.UserCreateException;
import com.cg.iter.Exception.userNotFoundException;
import com.cg.iter.main.User;

public class UserDaoImp implements UserDao {
	private Map<String,User> userDao = new HashMap<>();
	

	public UserDaoImp() {
		userDao.put("test1",new User("test1","9865321475","test1@gmail.com","test1","test1"));
		userDao.put("test2",new User("test2","8955321475","test2@gmail.com","test2","test2"));
	}

	@Override
	public boolean addUser(User user) throws UserCreateException {
		User newUser = userDao.put(user.getUserID(), user);
		if(newUser==null) {
			throw new UserCreateException("Failed to create an account!");
		}
		return true;
	}

	@Override
	public boolean checkUserEmail(String userID,String email) {
		User user = userDao.get(userID);
		if(user.getEmail().equals(email)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserPassword(String userID, String password) {
		User user = userDao.get(userID);
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String userID) throws userNotFoundException {
		if(userDao.get(userID)==null) {
			throw new userNotFoundException("User no found!");
		}
		return userDao.get(userID);
	}

	@Override
	public String changePassword(String userID, String newPassword) {
		User user = userDao.get(userID);
		//System.out.println("this is in userDao imp and password is "+newPassword);
		user.setPassword(newPassword);
		User newUser = userDao.put(userID, user);
		return "Password changed successfully";
	}

	@Override
	public Map<String, User> getDao() {
		return userDao;
	}

	@Override
	public boolean checkUserID(String userID) {
		if(userDao.get(userID)==null) {
			return false;
		}
		return true;
	}

	


	
	

}
