package com.suite1.quickr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {

	//** driver 
	private static WebDriver driver;
	private static Timeouts implicitlyWait;
	private static String destinationValue;

	static ExtentTest test;

	//** Vaariables
	String propertyNames;
	String propertyValues;
	String cancellation;
	String airWaysName;
	String filePath = "C:\\Users\\user\\eclipse-workspace\\MakeMyTripExample\\src";

	Workbook Workbook = null;


	public HomePage(WebDriver driver, Timeouts implicitlyWait, ExtentTest test) {
		this.driver = driver;
		this.implicitlyWait = implicitlyWait;
		this.test = test;
	}

	private static By clickOnCity = By.xpath("//span[@id=\"selectedCity\"]");
	private static By selectingCity = By.xpath("//body/div[3]/div[1]/div[1]/form[1]/div[2]/div[2]/div[1]/div[1]/span[1]/ul[1]/li[5]");
	private static By agriculturePath = By.xpath("//a[@id=\"agrtab\"]");
	private static By buyTabPath = By.xpath("//a[@id=\"buytab\"]");
	private static By rentTabPath = By.xpath("//a[@id=\"renttab\"]");
	private static By pgtabPath = By.xpath("//a[@id=\"pgtab\"]");
	private static By commercialPath = By.xpath("//a[@id=\"comtab\"]");
	

	//*[@id="tile_346569303"]/div/div[2]/div[1]/a

	//	private static By getTitlePaTh = By.xpath("1");
	private static By getPrice = By.xpath("1");


	public static void ClickOnCityDropDown() {
		try {
			test.log(LogStatus.PASS, "aable to click on city drop down");
			driver.findElement(clickOnCity).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "aable to click on city drop down");
			driver.findElement(clickOnCity).click();
		}
	}

	public static void CickOnCity() {
		try {
			test.log(LogStatus.PASS, "aable to click on city");
			driver.findElement(selectingCity).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "aable to click on city");
			driver.findElement(selectingCity).click();
		}

	}
	
	public static void clickOnPg() {
		try {
			test.log(LogStatus.PASS, "able to click on rent tab");
			driver.findElement(pgtabPath).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "able to click on rent tab");
			driver.findElement(pgtabPath).click();
		}
	}

	public static void CickOnAgriculture() {
		try {
			test.log(LogStatus.PASS, "able to click on agriculture tab");
			driver.findElement(agriculturePath).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "able to click on agriculture tab");
			driver.findElement(agriculturePath).click();
		}
	}

	public static void CickOnCommercial() {
		try {
			test.log(LogStatus.PASS, "able to click on agriculture tab");
			driver.findElement(commercialPath).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "able to click on agriculture tab");
			driver.findElement(commercialPath).click();
		}
	}
	
	public static void CickOnBuy() {
		try {
			test.log(LogStatus.PASS, "able to click on buy tab");
			driver.findElement(buyTabPath).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "able to click on buy tab");
			driver.findElement(buyTabPath).click();
		}
	}

	public static void CickOnRent() {
		try {
			test.log(LogStatus.PASS, "able to click on rent tab");
			driver.findElement(rentTabPath).click();
		} catch(ElementNotInteractableException e) {
			test.log(LogStatus.FAIL, "able to click on rent tab");
			driver.findElement(rentTabPath).click();
		}
	}
	
	
	
	public void SearchAgricultureFlow() throws InterruptedException {
		ClickOnCityDropDown();
		Thread.sleep(2000);
		CickOnCity();
		Thread.sleep(2000);
		CickOnAgriculture();
		test.log(LogStatus.PASS, "Successfull");
	}
	
	public void SearchBuyFlow() throws InterruptedException {
		ClickOnCityDropDown();
		Thread.sleep(2000);
		CickOnCity();
		Thread.sleep(2000);
		CickOnBuy();
		test.log(LogStatus.PASS, "Successfull");
	}
	
	public void SearchRentFlow() throws InterruptedException {
		ClickOnCityDropDown();
		Thread.sleep(2000);
		CickOnCity();
		Thread.sleep(2000);
		CickOnRent();
		test.log(LogStatus.PASS, "Successfull");
	}
	
	public void SearchpgFlow() throws InterruptedException {
		ClickOnCityDropDown();
		Thread.sleep(2000);
		CickOnCity();
		Thread.sleep(2000);
		clickOnPg();
		test.log(LogStatus.PASS, "Successfull");
	}
	
	public void SearchCommercialFlow() throws InterruptedException {
		ClickOnCityDropDown();
		Thread.sleep(2000);
		CickOnCity();
		Thread.sleep(2000);
		CickOnCommercial();
		test.log(LogStatus.PASS, "Successfull");
	}
	
	
}
