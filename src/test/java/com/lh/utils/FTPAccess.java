package com.lh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.testng.Assert;

 
public class FTPAccess {
    
    FTPClient ftp = null;
     
    // Constructor to connect to the FTP Server
    public FTPAccess(String host, int port, String username, String password) throws Exception{
        
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host,port);
        System.out.println("FTP URL is:"+ftp.getDefaultPort());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(username, password);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();        
    }    
    public void deleteFTPFile(String fileName, String hostDir)
            throws Exception
            {
    	//String localFileFullName=System.getProperty("user.dir")+"/src/test/resources/"+fileName;
    	try {
    	//	FileOutputStream fos = new FileOutputStream(hostDir); 
 System.out.println(hostDir + fileName);
                this.ftp.deleteFile(hostDir + fileName);
                }
                catch(Exception e){
 
                }
            }
    // Method to upload the File on the FTP Server
    public void uploadFTPFile(String fileName, String hostDir)
            throws Exception
            {
    	String localFileFullName=System.getProperty("user.dir")+"/src/test/resources/"+fileName;
                try {
                InputStream input = new FileInputStream(new File(localFileFullName));
 
                this.ftp.storeFile(hostDir + fileName, input);
                }
                catch(Exception e){
 
                }
            }
    
    // Download the FTP File from the FTP Server
    public void downloadFTPFile(String source, String destination) {
        try (FileOutputStream fos = new FileOutputStream(destination)) {
            this.ftp.retrieveFile(source, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // list the files in a specified directory on the FTP
    public boolean listFTPFiles(String directory, String fileName) throws IOException {
        // lists files and directories in the current working directory
        boolean verificationFilename = false;        
        FTPFile[] files = ftp.listFiles(directory);
        for (FTPFile file : files) {
            String details = file.getName();                
            System.out.println(details);            
            if(details.equals(fileName))
            {
                System.out.println("Correct Filename");
                verificationFilename=details.equals(fileName);
                Assert.assertTrue(details.equals(fileName), "Verification Failed: The filename is not updated at the CDN end.");                
            }
        }  
        
         return verificationFilename;
    }
    
    // Disconnect the connection to FTP
    public void disconnect(){
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already saved to server
            }
        }
    }
    
    // Main method to invoke the above methods
    public static void main(String[] args) {
        try {
            FTPAccess ftpobj = new FTPAccess("10.177.141.160", 21, "LHFTPQA", "LHFTPQA");
            ftpobj.deleteFTPFile("Biometric_Automation_37223.csv", "/Biometrics/Automation/");
        //    ftpobj.downloadFTPFile("Shruti.txt", "/users/shruti/Shruti.txt");
            System.out.println("FTP File deleted successfully");
            boolean result = ftpobj.listFTPFiles("/Biometrics/Automation", "ftptest.csv");
            System.out.println(result);
            ftpobj.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
