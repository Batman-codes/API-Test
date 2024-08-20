package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener{

	public void onStart(ITestContext context) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm-ss");
		String dateTime = now.format(format);
		
		System.out.println(" Suite Started at -> " + dateTime);
		
	}
	public void onTestStart(ITestResult result) {
		
		System.out.println(" Test Started -> " + result.getMethod().getMethodName());
	}
	public void onTestSuccess(ITestResult result) {
		
		System.out.println(" Test Success -> " + result.getMethod().getMethodName());
	}
	public void onTestFailure(ITestResult result) {
		
		System.out.println(" Test Failure -> " + result.getMethod().getMethodName());
	}
	public void onTestFinish(ITestResult result) {
		
		System.out.println(" Test Finish -> " + result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestContext context) {
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm-ss");
		String dateTime = now.format(format);
		
		System.out.println(" Test Started at -> " + dateTime);
	}

}
