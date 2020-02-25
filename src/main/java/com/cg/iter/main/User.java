package com.cg.iter.main;

public class User {
	
	
	private String name;
	private String phoneno;
	private String email;
	private String password;
	private String userID;
	
	
	
	public User(String name, String phoneno, String email, String password,String userID) {
		this.name = name;
		this.phoneno = phoneno;
		this.email = email;
		this.password = password;
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		return "newUser [name=" + name + ", phoneno=" + phoneno + ", email=" + email + ", password=" + password + "]";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
