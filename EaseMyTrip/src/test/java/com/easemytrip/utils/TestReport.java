package com.easemytrip.utils;

import java.io.IOException;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/* this contains methods of test report*/

public class TestReport extends Base implements ITestListener {

	/* What to do when test start */
	
	public void onTestStart(ITestResult result) {
		testLog = extentReporter.createTest(result.getName());
		testLog.info(result.getName() + " Started");
	}
	
	/* What to do when test succeeds */
	
	public void onTestSuccess(ITestResult result) {
		testLog.pass(result.getName() + " Successful");
		try {
			testLog.info("PFA : Final Snapshot Below ");
			String newDate = new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
			String path = HelperFunctions.getSnap(".\\src\\test\\resources\\output\\Login" + newDate + ".png");
			testLog.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* What to do when test fails */
	
	public void onTestFailure(ITestResult result) {
		testLog.fail(result.getName() + " Unsuccessful");
		try {
			testLog.info("PFA : Final Snapshot Below ");
			String newDate = new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
			String path = HelperFunctions.getSnap(".\\src\\test\\resources\\output\\Login" + newDate + ".png");
			testLog.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* What to do when test finishes */

	public void onFinish(ITestContext context) {
		testLog.info("Writing Report");
	//	extentReporter.flush();
	}

	public void onTestSkipped(ITestResult result) {
		testLog.info("Test Skipped due to an error");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

}