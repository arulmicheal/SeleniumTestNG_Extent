package com.framework.selenium.TestNG.main;

public class Configuration {
	/**
	 * Reporting configuration
	 */
	public static final String fileSeperator = System.getProperty("file.separator");
	public static final String reportFileName = "Test-Automaton-Report" + ".html";
	public static final String reportConfig = "extentConfig.xml";
	public static final String currDirectory = System.getProperty("user.dir");
	private static final String reportLocation = currDirectory + fileSeperator + "TestReport";
	public static final String currentReportLocation =reportLocation+ fileSeperator + General.getCurrentDateTime("yyyy-MM-dd_hh-mm-ss a");
	public static final String imagesDirectory=currentReportLocation+ fileSeperator+"Screenshots";
	
	/**
	 * Logger configuration
	 */
	public static final String format="[%1$tF %1$tl:%1tM:%1$tS %1$Tp %2$s]";
	public static final String loggerName="TestLog";
	public static final String loggerFilePath=currentReportLocation+ fileSeperator+"TestLog.log";
	
	/**
	 * Test Data configuration
	 */
	public static final String csvFilePath=currDirectory + fileSeperator+ "\\TestData\\data.csv";
}
