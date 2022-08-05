package com.automationstoretest.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {
	
	private static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	
	public static void setExtent(String hostName, String projectName, String tester) {
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/extent-report/"+"test-output.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/configuration/extent.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "Samsung-laptop");
		extent.setSystemInfo("ProjectName", "OnlineStoreTest");
		extent.setSystemInfo("Tester", "Wi9htWalker");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
	}
	public static void endReport() {
		extent.flush();
	}
}
