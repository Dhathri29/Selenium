package com.suite1.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MagicBricks {

	private static WebDriver driver;
	static MagicBricks obj=new MagicBricks();
	
	//private static By locationPath = By.cssSelector("#residentialIndex > header > div > div.headerRightCont > div.header-links.groupBlock > ul > li.dropContainer.helpCenter > a");
	private static By locationPath = By.xpath("");
	private static By FirstValue = By
			.xpath("//*[@id=\"residentialIndex\"]/header/div/div[3]/div[1]/ul/li[8]/div/ul/li[1]/span");	

	private static String parentWindow;
	private static ExtentTest test;
	
	private ExtentReports report;
    public void SearchRoomsBasedOnTestData(WebDriver driver, ExtentTest test, ExtentReports report) {
		
		test.log(LogStatus.PASS, "Entered SearchRoomsBasedOnTestData Class");

		this.driver = driver;
		this.test=test;
		this.report=report;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void SearchRoomsBasedOnTestData() {
		// TODO Auto-generated constructor stub
	}
	
	//main method from the class.
	public static void help() {
		// TODO Auto-generated method stub
		driver.findElement(locationPath).click();
		firstvalue();
		
	}

	private static void firstvalue() {
		
		try {
			driver.findElement(FirstValue).click();
			
			//Calling function to handle child window
			obj.handlingWindows();
			
			
			//getting data into excel
			File file = new File("C:\\Users\\user\\eclipse-workspace\\OyoApplication\\src\\MagicBricks.xlsx");
			FileInputStream istream=new FileInputStream(file);
			Workbook fetchWorkbook=new XSSFWorkbook(istream);
			Sheet sheet=fetchWorkbook.getSheet("help");
			
			String TextName = driver.findElement(By.xpath("/html/body/section[2]/div[1]/div[2]/div[1]/div[1]/div/div[1]/text()")).getText();
			System.out.println(TextName);
			
			Row row=sheet.getRow(0);
			
			System.out.println(row.getLastCellNum());
			Cell cell=row.createCell(row.getLastCellNum());
			ExtentTest test;
			
			cell.setCellValue(TextName);
			
			istream.close();
			FileOutputStream ostream=new FileOutputStream(file);
			fetchWorkbook.write(ostream);
			ostream.close();
			
			//Closing Child Window
			driver.close();
			
			//Returning to Parent Window
			driver.switchTo().window(parentWindow);
			
			
			
		} catch (Exception e) {
			ExtentTest test = null;
			// TODO Auto-generated catch block
			test.log(LogStatus.FAIL, "failed");		}
	}
	
	private void handlingWindows() throws Exception {

		String name="C://test.png";
		
		parentWindow=driver.getWindowHandle();
		//System.out.println(parentWindow);
		Set<String> childWindows =driver.getWindowHandles();
		
		Iterator<String> i=childWindows.iterator();
		
		while(i.hasNext())
		{
			String ChildWindow=i.next();
			if(!parentWindow.equalsIgnoreCase(ChildWindow))
			{
				driver.switchTo().window(ChildWindow);
				
				this.takeSnapShot(driver, name) ;
			}
		}
	}
	
	public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

         //Move image file to new destination

         File DestFile=new File(fileWithPath);

        //Copy file at destination

          FileUtils.copyFile(SrcFile, DestFile);

		
	}	
}



