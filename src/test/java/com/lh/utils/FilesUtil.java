package com.lh.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class FilesUtil {
	public static void copyFiles(String origin, String destination) throws IOException {
		Path FROM = Paths.get(origin);
		Path TO = Paths.get(destination);
		// overwrite the destination file if it exists, and copy
		// the file attributes, including the rwx permissions
		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES };
		Files.copy(FROM, TO, options);
		System.out.println("old =" + FROM);
		System.out.println("new =" + TO);
	}

	public static List<String> returnFileNamesUsingExtention(String clientName, String ext,String folder) throws IOException {
		// String fileName = " ";

		GregorianCalendar gcalendar = new GregorianCalendar();
		String time = gcalendar.getTime().toString().replace(":", "");
		List<String> results = new ArrayList<String>();
		File[] files = new File(System.getProperty("user.dir") + "//src//test//resources//"+folder)
				.listFiles();
		// If this pathname does not denote a directory, then listFiles()
		// returns null.

		File newName = new File(
				System.getProperty("user.dir") + "//src//test//resources//" + clientName +time.replace(" ", "") + ".csv");
		for (File file : files) {
			if(folder.contains("ImportUploadFiles")){
			if (file.isFile() && file.getName().startsWith(clientName) && file.getName().endsWith(ext)) {

				copyFiles(file.getPath(), newName.getPath());
				// file.renameTo(newName);
				results.add(newName.getName());
				System.out.println(file.getName() + " " + newName.getName());
			}
			} else if (file.isFile()&& file.getName().startsWith(clientName) && file.getName().endsWith(ext)) {
				results.add(file.getName());
				System.out.println(file.getName());
			}
		}

		return results;

	}

	public static String[] readCSVFile(String filename) {
		String csvFile = System.getProperty("user.dir") + "//src//test//resources//" + filename;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int count = 0;
		String[] csvList = null;
		List<String> lines = new ArrayList<>();
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				lines.add(line);

				// use comma as separator
				csvList = lines.get(0).split(cvsSplitBy);
				for (count = 0; count < csvList.length; count++) {
					System.out.println(csvList[count]);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
		return csvList;
	}

	public static void deleteFile(String fileName){
		File[] files = new File(System.getProperty("user.dir") + "//src//test//resources")
				.listFiles();
		// If this pathname does not denote a directory, then listFiles()
		// returns null.

		for (File file : files) {
			if (file.isFile() && file.getName().startsWith(fileName)) {
				file.delete();
			}
			}

	}
	public static void main(String[] args) throws IOException {

		// System.out.println(returnFileNamesUsingExtention("Expedia",".csv"));
	}
}
