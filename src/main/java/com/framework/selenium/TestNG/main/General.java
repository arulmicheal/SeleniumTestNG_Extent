package com.framework.selenium.TestNG.main;

import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class General {
		/**
		 * Create path if does not exist, returns current directory path if unable to create
		 * @param path
		 * @return
		 */
		public static String createDirectoryPath(String path) {
			File testDirectory = new File(path);
			if (!testDirectory.exists()) {
				if (testDirectory.mkdir()) {
					System.out.println("Directory: " + path + " is created!");
					return path;
				} else {
					System.out.println("Failed to create directory: " + path);
					return System.getProperty("user.dir");
				}
			} else {
				System.out.println("Directory already exists: " + path);
			}
			return path;
		}
		/**
		 * Create file path if does not exist.
		 * @param path
		 * @return
		 */
		public static String createFilePath(String path) {
			File filePath = new File(path);
			
			if (!filePath.exists()) {
				String testDirectory=createDirectoryPath(filePath.getParent());
				try {
					new File(testDirectory+"\\"+filePath.getName()).createNewFile();
				} catch (IOException e) {
					System.out.println("File : " + path + " is not created!");
					e.printStackTrace();
				}
					System.out.println("File Path: " + path + " is created!");
					return path;
			} else {
				System.out.println("File Path already exists: " + path);
			}
			return path;
		}
		/**
		 * Gets the current date time in provided format
		 * @param strDateFormat
		 * @return
		 */
		public static String getCurrentDateTime(String strDateFormat) {
			try
			{
				return DateTimeFormatter.ofPattern(strDateFormat, Locale.getDefault()).format(LocalDateTime.now());
			}catch(DateTimeException ex)
			{
				return DateTimeFormatter.ofPattern("yyyy-MM-dd_hh-mm-ss a", Locale.getDefault()).format(LocalDateTime.now());
			}
		}

}
