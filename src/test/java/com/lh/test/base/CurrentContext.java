package com.lh.test.base;

public class CurrentContext {
	
	private String userName = null;
	private String userType = null;
	private boolean isUserLogedin = false;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isUserLogin() {
		return isUserLogedin;
	}
	public void setUserLogin(boolean isUserLogin) {
		this.isUserLogedin = isUserLogin;
	}
}
