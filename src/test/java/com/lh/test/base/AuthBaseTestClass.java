package com.lh.test.base;

import com.lh.pages.unauthenticated.LogOutPage;
import com.lh.pages.unauthenticated.LoginPage;
import static com.lh.helper.DriverFactory.openURL;

public class AuthBaseTestClass extends BaseTestClass  {

	public static CurrentContext currentContext= new CurrentContext();
	
	/**
	 * This method will take multiple parameters and it is especially added for handling the case 
	 * where we want to open client url other than expedia. Below are the parameter details:
	 * userName: User which is used for login into portal
	 * password: Password for the user
	 * userType: Whether user is a Portal or an Admin user
	 * portalType: Whether we are accessing LHMTv2 portal or Ontarget. In case of LHMTv2, pass parameter as "Portal" 
	 * 				and for onTarget, pass "OnTarget". Both are case insensitive.
	 * url: Client url other than expedia. 
	 * 
	 * @param substitutionValue
	 */
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
