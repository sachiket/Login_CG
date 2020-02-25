package com.cg.iter.LoginService;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.iter.Dao.UserDao;
import com.cg.iter.Dao.UserDaoImp;
import com.cg.iter.Exception.UserCreateException;
import com.cg.iter.Exception.userNotFoundException;
import com.cg.iter.main.User;
import com.cg.iter.util.Validator;


public class LoginServiceImp implements LoginService {
	private UserDao dao;
	Validator valid = new Validator(); 


	public LoginServiceImp(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean validateUseridAndPassword(String userID,String password) {
		
		if(dao.checkUserID(userID) && dao.checkUserPassword(userID, password)) {
			return true;
		}
		System.out.println("Incorrect UserID or Password!");
		
		return false;
	}


	@Override
	public boolean addUser(User user) throws UserCreateException {
		dao = new UserDaoImp();
		return dao.addUser(user);
	}


	@Override
	public String forgetPassword(String userID,String newPassword) {
		return dao.changePassword(userID, newPassword);
	}
	
	
	
	/*
	 * this method are to check the existence of email , name , and mobile number in database 
	 */

	@Override
	public boolean checkEmailExist(String userID,String email) {
		return dao.checkUserEmail(userID,email);
		
	}

	@Override
	public boolean checkCorrectName(String email, String name) {
		//User user;
		try {
			User user = dao.getUser(email);
			if(user.getName().equals(name)) {
				return true;
			}
		} catch (userNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean checkCorrectMobileNumber(String userID, String phoneno) {
		//User user;
		try {
			User user = dao.getUser(userID);
			if(user.getPhoneno().equals(phoneno)) {
				return true;
			}
		} catch (userNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Map<String, User> getDao() {
		return dao.getDao();
	}

	@Override
	public boolean checkUseridExist(String userID) {
		// TODO Auto-generated method stub
		return dao.checkUserID(userID);
	}


}
