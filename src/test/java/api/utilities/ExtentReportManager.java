package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {


	public ExtentSparkReporter htmlReporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	public void configureReport() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/reports/TestReport-"+timestamp+".html";
		
		htmlReporter = new ExtentSparkReporter(reportPath);
		
//		add system/environment info to report
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("Application", "Pet Store Users API");
		reports.setSystemInfo("OS", "Windows 11");
		reports.setSystemInfo("User Name", "Shubham Verma");

//		Configuration to change the name & theme of report
		htmlReporter.config().setDocumentTitle("RestAssured Automation Project");
		htmlReporter.config().setReportName("Pet Store Users API");
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
//	onStart method is called when any Test starts
    @Override
	public void onStart(ITestContext context) {
		configureReport();
	}
	
//	onFinish method is called after all Tests are executed
    @Override
    public void onFinish(ITestContext context) {
		reports.flush(); // it is mandatory to call flush method to ensure information is written to the started reporter
	}
	
//	When TC get failed, onTestFailure methods is called
    @Override
    public void onTestFailure(ITestResult Result) {
    	test=reports.createTest(Result.getName());
    	test.assignCategory(Result.getMethod().getGroups());
    	test.createNode(Result.getName());
    	test.log(Status.FAIL, "Test Failed");
    	test.log(Status.FAIL, Result.getThrowable().getMessage());
    }
	
//	When TC get skipped, onTestSkipped method is called
    @Override
    public void onTestSkipped(ITestResult Result) {
    	test=reports.createTest(Result.getName());
    	test.assignCategory(Result.getMethod().getGroups());
    	test.createNode(Result.getName());
    	test.log(Status.SKIP, "Test Skipped");
    	test.log(Status.SKIP, Result.getThrowable().getMessage());
    }
	
//	When TC get started, onTestStart method is called
    @Override
    public void onTestStart(ITestResult Result) {
		System.out.println("Name of Test method started: " + Result.getName());	
	}
	
//	When TC get passed, onTestSuccess method is called
    @Override
    public void onTestSuccess(ITestResult Result) {
    	test=reports.createTest(Result.getName());
    	test.assignCategory(Result.getMethod().getGroups());
    	test.createNode(Result.getName());
    	test.log(Status.PASS, "Test Passed");
    }
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
		
	}

}
