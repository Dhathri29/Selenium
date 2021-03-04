package com.suite1.quickr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class pgPage {
	//** driver 
	private static WebDriver driver;
	private static Timeouts implicitlyWait;

	static ExtentTest test;

	//** Vaariables
	String propertyNames;
	String propertyValues;
	String propertyLinks;
	String filePath = "C:\\Users\\user\\eclipse-workspace\\MakeMyTripExample\\src";
	String parentWindow;
	String childWindow;
	String tabName = "Buy";
   
	Workbook Workbook = null;


	public pgPage(WebDriver driver, Timeouts implicitlyWait, ExtentTest test) {
		this.driver = driver;
		this.implicitlyWait = implicitlyWait;
		this.test = test;
	}

	public void ExtractDataFlow() throws IOException {
		for(int i=1;i<=4;i++) { 
			Excel(filePath, "Makemytrip Test.xlsx", "Sheet3", i);
			ClickOnProperty(i);
		}
	}

	public void Excel(String filePath, String fileName, String sheetName, int i) throws IOException {
		File file = new File(filePath + "\\" + fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook = new XSSFWorkbook(inputStream);

		Sheet Sheet = Workbook.getSheet(sheetName);

		int rowcount=Sheet.getLastRowNum() - Sheet.getFirstRowNum();

		System.out.println("rowcount in write" + rowcount); 

		Row row = Sheet.getRow(0);

		Row newRow = Sheet.createRow(rowcount+1);
		System.out.println(newRow.getLastCellNum() + "get last column in 1");

		propertyNames = GetPropertyNames(i);
		Cell cell0 = newRow.createCell(newRow.getLastCellNum()+1);
		cell0.setCellValue(propertyNames);

		propertyValues = GetPropertyValues(i);
		Cell cell1 = newRow.createCell(newRow.getLastCellNum());
		cell1.setCellValue(propertyValues);

		test.log(LogStatus.PASS, "Data added to excel successfully");
		inputStream.close();

		FileOutputStream outputStream = new FileOutputStream(file);

		Workbook.write(outputStream);

		outputStream.close();
		Workbook.close();
	}

	public String GetPropertyNames(int i) {
		String propertName = "";
		try {
			propertName = driver.findElement(By.xpath("//*[@id=\"scroll-main-div\"]/div["+ i+"]/div/div[2]/div[1]/a/h3")).getText();
			
			                                           
			test.log(LogStatus.PASS, "Property name added");

		} catch (Exception e) {
			propertName = driver.findElement(By.xpath("//*[@id=\"scroll-main-div\"]/div["+ i+"]/div/div[2]/div[1]/a/h3")).getText();
		}
		return propertName; 
		
	
	}

	public String GetPropertyValues(int i) {
		String propertValue = "";
		try {
			propertValue = driver.findElement(By.xpath("//*[@id=\"scroll-main-div\"]/div["+ i+"]/div/div[2]/div[3]/div[2]/div/span")).getText();
			test.log(LogStatus.PASS, "success");
		} catch (Exception e) {
			propertValue = driver.findElement(By.xpath("//div[@id='scroll-main-div']/div["+ i+"]/div/div[2]/div[3]/div[2]/div/span")).getText();
		}
		return propertValue;	
	}

	public void ClickOnProperty(int i) { 

		try {
			driver.findElement(By.xpath("//*[@id=\"scroll-main-div\"]/div["+ i+"]/div/div[2]/div[1]/a/h3")).click(); 
			parentWindow = driver.getWindowHandle();
			Set<String> windows =driver.getWindowHandles();

			Iterator<String> I1= windows.iterator(); while(I1.hasNext()) { 
				String childWindow=I1.next();

				if(!parentWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);

					System.out.println(driver.switchTo().window(childWindow).getTitle());

					test.log(LogStatus.PASS, "Child Window"); 
					takeSnapShot(driver, filePath+ tabName +i+".png"); 
					driver.close(); 
				}
				driver.switchTo().window(parentWindow); 
			} 
		}catch (Exception e) {
			driver.findElement(By.xpath("//*[@id=\"scroll-main-div\"]/div["+ i+"]/div/div[2]/div[1]/a/h3")).click(); 
		} 
	}


	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
