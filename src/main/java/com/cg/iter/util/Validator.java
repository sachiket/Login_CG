package com.cg.iter.util;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	Scanner scan = new Scanner(System.in);
	
	public boolean checkMail(String email) {
		//check if the email entered is a valid email or not
		String len1[]=email.split("@");
		if(len1.length==2) {
			String len2[]=len1[1].split("\\.");
			if(len2.length==2) {
				return true;
			}
		}
		System.out.println("Please enter a valid email!");
		return false;
		
	}
	@SuppressWarnings("finally")
	public int checkChoice() {
		String choice = "";
		int choiceInt = 0;
		while((choice=scan.nextLine().trim()).length()==0 ){
			System.out.println("Please enter your choice!");
		}
		try {
			choiceInt = Integer.parseInt(choice);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input!! Please enter numbers only!");
		}finally {
			return choiceInt;
		}
		
	}
	
	public boolean checkPassword(String password) {
		String passCheck[]= new String[4];
		boolean check = true;
		if(password.length()<8)check=false;
		passCheck[0] = ".*[a-z]+.*";
		passCheck[1] = ".*[A-Z]+.*";
		passCheck[2] = ".*[0-9]+.*";
		passCheck[3] = ".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*";
		for (String regex : passCheck) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(password);
			if(!matcher.matches())check=false;
			
		}
		if(check==false) {
			System.out.println("*****************************  NOTE  *******************************");
			System.out.println("+------------------------------------------------------------------+");
			System.out.println("|     --> Entered password should be alphanumeric.                 |");
			System.out.println("|     --> Entered password should have an upper case character.    |");
			System.out.println("|     --> Entered password should have a special character         |");
			System.out.println("|     --> Entered password should be greater than 7 characters     |");
			System.out.println("+------------------------------------------------------------------+");
		}
		return check;
	}
	
	
	
	public boolean checkNumber(String number) {
		String numberCheck = "\\d+";
		if(number.matches(numberCheck) && number.length()==10) {
			return true;
		}
		System.out.println("--> Entered number should be of 10 numeric digits.");
		return false;
	}
}
