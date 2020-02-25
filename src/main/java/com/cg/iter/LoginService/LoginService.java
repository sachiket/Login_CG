package com.cg.iter.LoginService;

import java.util.Map;

import com.cg.iter.Exception.UserCreateException;
import com.cg.iter.main.User;

public interface LoginService {
//	//check if user enters the valid email
//	boolean checkMail(String email);
//	//check if the length if password is greater than 7
//	//check if the password contains number,special symbol,and alphabets or not
//	boolean checkPassword(String password);
//	//check if the number contains 10 digit numeric value or not
//	boolean checkNumber(String number);
	//check if the email already exists 
	boolean validateUseridAndPassword(String userID, String password);
	//create a new user with again validation check
	boolean addUser(User user) throws UserCreateException;
	Map<String,User> getDao();
	
	
	
	
	
	//for forget password
	boolean checkEmailExist(String userID,String email);
	boolean checkCorrectName(String userID,String name);
	boolean checkCorrectMobileNumber(String userID,String phoneno);
	String forgetPassword(String userID,String newPassword);
	boolean checkUseridExist(String userID);
	
	
	
}
