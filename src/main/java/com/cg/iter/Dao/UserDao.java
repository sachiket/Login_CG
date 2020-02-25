package com.cg.iter.Dao;

import java.util.Map;

import com.cg.iter.Exception.UserCreateException;
import com.cg.iter.Exception.userNotFoundException;
import com.cg.iter.main.User;

public interface UserDao {
	boolean addUser(User user) throws UserCreateException;
	boolean checkUserEmail(String userID ,String email);
	boolean checkUserPassword(String userID,String password);
	User getUser(String userID) throws userNotFoundException;
	String changePassword(String userID, String newPassword);
	Map<String,User> getDao();
	boolean checkUserID(String userID);
}
