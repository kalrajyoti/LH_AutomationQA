package com.lh.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name="onTrack")
	public static Object[][] getDataSuite(Method m){
		Xls_Reader xls1 = new Xls_Reader(System.getProperty("user.dir")+ "\\src\\test\\resources\\LH.xls");
		System.out.println(System.getProperty("user.dir")+ "\\src\\test\\resources\\LH.xls");
		return SeleniumUtil.getData(m.getName(), xls1);
	}
	
	
	
	
}
