package com.suite1.quickr.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.suite1.quickr.BuyPage;
import com.suite1.quickr.CommercialPage;
import com.suite1.quickr.HomePage;
import com.suite1.quickr.PropertyPage;
import com.suite1.quickr.RentHomePage;
import com.suite1.quickr.pgPage;

public class TC01_TestCase_Qiuckr {
	static ExtentTest test;
	static ExtentReports report;

	static WebDriver driver = null;
	private static HomePage homePageInstance = null;
	private static PropertyPage propertyPageInstance = null;
	private static RentHomePage rentInstance = null;
	private static BuyPage buyInstance = null;
	private static pgPage pgInstance = null;
	private static CommercialPage commercialInstance = null;

	@BeforeClass
	public static void startTest() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		
		test = report.startTest("Quickr");
	}
    
	@BeforeMethod(groups = { "functional" })
	public void Before() {
		System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();

		Timeouts implicitlyWait = driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.quikr.com/homes/city/chennai");
		driver.manage().window().maximize();
		homePageInstance = new HomePage(driver, implicitlyWait, test);
		propertyPageInstance = new PropertyPage(driver, implicitlyWait, test);
		rentInstance = new RentHomePage(driver, implicitlyWait, test);
		buyInstance = new BuyPage(driver, implicitlyWait, test);
		pgInstance = new pgPage(driver, implicitlyWait, test);
		commercialInstance = new CommercialPage(driver, implicitlyWait, test);
	}

	@Test(priority = 0, groups = { "functional" })
	public void TestCase() throws Exception {
		if (driver.getTitle().equals("Property in Chennai | Residencial Real Estate Chennai - QuikrHomes")) {
			test.log(LogStatus.PASS, "Navigated successfully");
		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}

		homePageInstance.SearchpgFlow();
		pgInstance.ExtractDataFlow();
		driver.get("https://www.quikr.com/homes/city/chennai");

		homePageInstance.SearchAgricultureFlow();
		propertyPageInstance.ExtractDataFlow();
		driver.get("https://www.quikr.com/homes/city/chennai");

		homePageInstance.SearchRentFlow();
		rentInstance.ExtractDataFlow();
		driver.get("https://www.quikr.com/homes/city/chennai");

		homePageInstance.SearchBuyFlow();
		buyInstance.ExtractDataFlow();
		driver.get("https://www.quikr.com/homes/city/chennai");

		homePageInstance.SearchCommercialFlow();
		commercialInstance.ExtractDataFlow(); 

	}

	/*
	 * @Test(priority = 0, groups = {"functional"}) public void RentTestCase()
	 * throws Exception {
	 * 
	 * }
	 */

	/*
	 * @Test(groups = {"sanity"}) public void LoginTestCase() {
	 * myntraLoginInstance.LoginFlow(); }
	 * 
	 * @Test(groups = {"functional"}) public void SearchProduct() throws IOException
	 * { searchProductInstance.SearchProduct();
	 * getProductsPageInstance.GetProductLinks();
	 * 
	 * }
	 */

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
		driver.close();
	}
	/*
	 * @AfterClass public void After() { driver.close(); driver.quit();
	 * System.out.println("after method"); }
	 */
}
