package generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Generic_Test {
	public WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports reports;
	public static ExtentTest test;

	BrowserFactory bff = new BrowserFactory();
	FileManager fm = new FileManager();
	
	Logger log=Logger.getLogger(Generic_Test.class);
	
	@BeforeSuite
	public void setUp()
	{
		htmlReporter = new ExtentHtmlReporter("./reports/" + new Date().toString().replace(":", "-")+"E.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}
	

	@Parameters({ "browser" })
	@BeforeMethod
	public void openAppn(@Optional("chrome") String browser) {
		if (browser.equals("chrome")) {
			log.info("starting browser");
			driver = bff.getBrowser("chrome");

			driver.get(fm.getApplicationUrl());
		} else {
			driver = bff.getBrowser("firefox");

			driver.get(fm.getApplicationUrl());

		}

		driver.manage().timeouts()
				.implicitlyWait(fm.getImplicityWait(), TimeUnit.SECONDS);

	}

	@AfterMethod
	public void closeAppn(ITestResult res) throws IOException {
		System.out.println(res.getStatus());

		if (ITestResult.FAILURE == res.getStatus())

		{
			String testName = res.getName();
			test.fail("test script failed",
					MediaEntityBuilder.
					createScreenCaptureFromPath(new ScreenShot()
							.getPhoto(driver, testName)).build());
		}
		test.assignAuthor("ravish");
		test.assignCategory("Gui Automation");
		test.assignDevice("laptop");
		reports.setSystemInfo("windows", "10");	
		driver.quit();
		log.info("closing the browser");
	}

	
	@AfterSuite
	public void tearDown()
	{
		reports.flush();
	}
}
