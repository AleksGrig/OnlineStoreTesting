package com.automationstoretest.utility;

import java.io.IOException;

import com.automationstoretest.base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	protected static ExtentReports extent;
	private static ExtentSparkReporter sparkReporter;
	
	public static void setExtent(String hostName, String projectName, String tester) throws IOException {
    String reportPath = """
      %s/test-output/extent-report/test-output.html""".formatted(Base.userDir);
		sparkReporter= new ExtentSparkReporter(reportPath);

    String extentConfigPath = """
      %s/configuration/extent.xml""".formatted(Base.userDir);
		// try {
      sparkReporter.loadXMLConfig(extentConfigPath);
    //} catch (IOException e) { e.printStackTrace(); }

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("HostName", "Samsung-laptop");
		extent.setSystemInfo("ProjectName", "OnlineStoreTest");
		extent.setSystemInfo("Tester", "Wi9htWalker");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
	}
	public static void endReport() {
		extent.flush();
	}
}
