package com.suite1.testdata;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC_01_MagicBricks {

	static WebDriver driver = null;
	private static MagicBricks searchRoomsInstance = null;
	private ExtentReports report;
//	private static LoginPage myntraLoginInstance = null;
//	private static SearchPage searchProductInstance= null;
//	private static GetProductsPage getProductsPageInstance= null;
	private ExtentTest test;
	
	@BeforeTest
	public void Before() {
		System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		
		/*System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dell\\Desktop\\Selenium\\geckodriver.exe");
		driver=new FirefoxDriver(); */
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.magicbricks.com/");
		report = new ExtentReports("C:\\Users\\Dell\\Downloads\\MagicBrinks\\ExtentreportResults.html");
		test=report.startTest("TC_01_MagicBricks");
		searchRoomsInstance = new MagicBricks();
		//test.log(LogStatus.PASS, "navigated to url");
	}
	
	@Test
	public void MagicBricksTestCase() throws Exception {
		MagicBricks.help();
		test.log(LogStatus.PASS, "navigated MagicBricksBasednTestData Class");
	}
	
	@AfterTest
	public void After() {
		//report.endTest(test);
		//report.flush();
		driver.close();
		System.out.println("after method");
	}
}

