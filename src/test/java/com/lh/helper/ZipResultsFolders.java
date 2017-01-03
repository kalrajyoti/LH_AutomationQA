package com.lh.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.testng.Reporter;

import com.lh.testConfig.TestProperty;

/**
 * <h2>Zips the test output results folder</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class ZipResultsFolders {
	private static final String SRC_TESTSUITE_OUTPUT = "\\testsuite-output\\LH_test_suite";
	/**
	 * Zipped file name
	 */
	static String outputZipFile;
	/**
	 * The list of files to be added to the zip results
	 */
	private List<String> fileList;
	/**
	 * The source folder containing the files to be zipped.
	 */
	private static String SOURCE_FOLDER;;

	/**
	 * Read the source folder for the list of files to be zipped
	 */
	public ZipResultsFolders() {
		fileList = new ArrayList<String>();
	}

	/**
	 * Zip the files
	 */
	public static void zipResults() {
		String currDir = System.getProperty("user.dir");
		Reporter.log("The Initial current Directory is :" +  currDir);
		currDir = currDir.substring(0, currDir.length());
		System.out.println("Present working dir is - " + currDir);
		
		Reporter.log("The current Directory is :" +  currDir);
		outputZipFile = currDir + "\\" + TestProperty.ZIPFILE_REPORT_NAME;
		Reporter.log("The outpur zip file is - " + outputZipFile);
		
		SOURCE_FOLDER = currDir + SRC_TESTSUITE_OUTPUT;
		Reporter.log("The source folder is - " + SOURCE_FOLDER);
		
		ZipResultsFolders appZip = new ZipResultsFolders();
		appZip.generateFileList(new File(SOURCE_FOLDER));
		appZip.zipIt(outputZipFile);
	}

	/**
	 * Add the files from the source folder to the zipped folder
	 * @param zipFile
	 */
	public void zipIt(String zipFile) {
		byte[] buffer = new byte[1024];
		String source = "";
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			try {
				source = SOURCE_FOLDER.substring(
						SOURCE_FOLDER.lastIndexOf("\\") + 1,
						SOURCE_FOLDER.length());
			} catch (Exception e) {
				source = SOURCE_FOLDER;
			}
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);

			Reporter.log("Output to Zip : " + zipFile);
			FileInputStream in = null;

			for (String file : this.fileList) {
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(SOURCE_FOLDER + File.separator
							+ file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}

			zos.closeEntry();

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void generateFileList(File node) {

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				Reporter.log("The filename is- " + filename);
				generateFileList(new File(node, filename));
			}
		}

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString()));
		}

	}

	private String generateZipEntry(String file) {
		return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	}
}