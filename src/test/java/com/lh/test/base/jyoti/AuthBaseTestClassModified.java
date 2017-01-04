package com.lh.test.base.jyoti;

import static com.lh.helper.DriverFactory.openURL;

import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.pages.unauthenticated.LoginPage;
import com.lh.test.base.BaseTestClass;
import com.lh.test.base.CurrentContext;

public class AuthBaseTestClassModified extends BaseTestClass  {

	public static CurrentContext currentContext= new CurrentContext(); 
	public void doLogin(String... substitutionValue){
		String userName 	= substitutionValue[0];
		String password 	= substitutionValue[1];
		String userType		= substitutionValue[2];
		String portalType 	= substitutionValue[3];
		
		if(substitutionValue.length>4){
			String url = substitutionValue[4];
			openURL(url);
		}
		LoginPage loginPageObj = new LoginPage(portalType);
		LogOutPage logOut = new LogOutPage();
		if (currentContext.isUserLogin()) {
			if (!currentContext.getUserName().equals(userName)) {
				logOut.clickLogoutLink(portalType);
				loginPageObj.loginAs(userName, password);
				setcurrentContext(userName,userType,true);	
			}
		} else {
			loginPageObj.loginAs(userName,password);
			setcurrentContext(userName,userType,true);
		}
	}

	public void setcurrentContext(String userName, String userType, boolean isLoggedIn) {
		currentContext.setUserName(userName);
		currentContext.setUserType(userType);
		currentContext.setUserLogin(isLoggedIn);	
	}

}


