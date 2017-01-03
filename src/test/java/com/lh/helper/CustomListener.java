package com.lh.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.lh.utils.Xls_Reader;

public class CustomListener extends TestListenerAdapter implements ITestListener,IInvokedMethodListener,ISuiteListener
{
  public static Hashtable<String,String> resultTable = null;
  public static String  resultFolderName;
  public static String resultFilePath;
  public static ArrayList<String> keys;
  @Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//resultTable.put(result.getName(),"Pass");
		report(result.getName(),"Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//resultTable.put(result.getName(),result.getThrowable().getMessage());
		report(result.getName(),result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//resultTable.put(result.getName(),result.getThrowable().getMessage());
		report(result.getName(),result.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
	System.out.println("Starting suite"+suite.getName());
		if(resultTable==null)
			keys = new ArrayList<String>();
		resultTable = new Hashtable<String, String>();
		
		if(resultFolderName==null)
		{
			
			Date d = new Date();
			resultFolderName = d.toString().replaceAll(":","_");
			File f = new File(System.getProperty("user.dir")+"/Reports/"+resultFolderName);
			System.out.println("info is"+f);
			f.mkdir();
			
			resultFilePath= System.getProperty("user.dir")+"/Reports/"+resultFolderName+"/LH_Automation_Report.xlsx";
			File src = new File(System.getProperty("user.dir")+"/Reports/LH_Automation_Report.xlsx");
			File dest = new File(resultFilePath);
			System.out.println("Test report file : "+resultFilePath);
	     
			
			try {
				copyFile(src, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		if(resultTable!=null){
		System.out.println("Finishing suite" +suite.getName());
		System.out.println(resultTable);
		System.out.println(keys);
		
		//write results in xls
		if(suite.getName().equals(Constants.ROOT_SUITE)){
		Xls_Reader xls = new Xls_Reader(resultFilePath);
		//xls.addSheet(suite.getName());  
		
		//add the result in the sheet
		//xls.setCellData(sheetName, colName, rowNum, data)
		xls.setCellData(suite.getName(),0,1,"Test Cases");
		xls.setCellData(suite.getName(),1,1,"Description");
		xls.setCellData(suite.getName(),2,1,"Status");
		
		
		
		for(int i=0;i<keys.size();i++)
		{
			String key = keys.get(i);
			String result = resultTable.get(key);
			xls.setCellData(suite.getName(),0,i+2,key);
			xls.setCellData(suite.getName(),1,i+2,result);
			
			
		}
		  }
		resultTable=null;
		keys=null;
	}
	
	}
	public void report(String name,String result)
	{
		  int iterationNumber=1;
		  while(resultTable.containsKey(name))
		  {
			  iterationNumber++; 
		  }
		    keys.add(name+"iteration"+iterationNumber);
			resultTable.put(name+"iteration"+iterationNumber,result);
			
			//keys.add(name);
			//resultTable.put(name,result);
	}
	

	public static void copyFile(File oldLocation, File newLocation) throws IOException {
        if ( oldLocation.exists( )) {
            BufferedInputStream  reader = new BufferedInputStream( new FileInputStream(oldLocation) );
            BufferedOutputStream  writer = new BufferedOutputStream( new FileOutputStream(newLocation, false));
            try {
                byte[]  buff = new byte[8192];
                int numChars;
                while ( (numChars = reader.read(  buff, 0, buff.length ) ) != -1) {
                    writer.write( buff, 0, numChars );
                }
            } catch( IOException ex ) {
                throw new IOException("IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if ( reader != null ){                      
                        writer.close();
                        reader.close();
                    }
                } catch( IOException ex ){
                    ex.getMessage();
                    }
            }
        } else {
            throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to " + newLocation.getPath() );
        }
	}
}

