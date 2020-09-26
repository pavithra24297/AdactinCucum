package com.lexicon.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public  static WebDriver driver;
	public Properties prop;

	
	public  WebDriver initializeDriver() {
		
		try {
			String pathToPropertyFile = System.getProperty("user.dir")
					+ "/src/main/java/com/lexicon/config/config.properities";
			String pathToDriver = System.getProperty("user.dir") + "/Driver/chromedriver.exe";

			System.out.println(pathToDriver + "/n" + pathToPropertyFile);

			prop = new Properties();
			FileInputStream fis = new FileInputStream(pathToPropertyFile);

			prop.load(fis);
			String browserName = prop.getProperty("browser");

			if (browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", pathToDriver);
				driver = new ChromeDriver();
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
		}


		return driver;
	}

// DropDown method
	public static void dropDownMethod(WebElement element, String option, String value) {

		Select s = new Select(element);
		if (option.equalsIgnoreCase("Value")) {
			s.selectByValue(value);
		}

		else if (option.equalsIgnoreCase("VisibleText")) {
			s.selectByVisibleText(value);
		}

		else if (option.equalsIgnoreCase("Index")) {
			int i = Integer.parseInt(value);
			s.selectByIndex(i);
		}
	}

	// Click method
	public static void mouseclick(WebElement element) {

		element.click();
	}

	// get url method
	public void getUrl() {

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		this.waitFor(20);
		
	}

	// navigate back method
	public  void naviBack() {

		driver.navigate().back();
	}

//navigate forward method
	public  void naviForward() {

		driver.navigate().forward();

	}

	// Navigate to url
	public  void navito(String url) {

		driver.navigate().to(url);

	}

	// Navigate Refresh method
	public void naviRefresh() {
		driver.navigate().refresh();

	}

	//Sendkeys  method
	public  void sendKeys(WebElement element, String str) {
		element.sendKeys(str);
	}
//using Actions moving to element
	public void moveToElement(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).perform();

	}

	// using Actions contextclick
	public void mouseContextClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element).perform();

	}

	// using Actions doubleclick
	public void doubleClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick(element).perform();

	}

	// using Actions doubleclick
	public void actionClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.click(element).perform();
	}

//using Actions Drag and Drop
	public  void dragdrop(WebElement src, WebElement des) {
		try {
			Actions ac = new Actions(driver);
			ac.dragAndDrop(src, des).build().perform();
		} catch (Exception e) {
		}
	}

	// Simple alert
	public  void sAlert() {
		try {
			Alert simple = driver.switchTo().alert();
			simple.accept();
		} catch (Exception e) {

		}
	}

// Confirm alert
	public void cAlert() {
		try {
			Alert confirm = driver.switchTo().alert();
			confirm.accept();
			Alert confirm2 = driver.switchTo().alert();
			confirm2.dismiss();
		} catch (Exception e) {
		}
	}

//prompt alert
	public void pAlert() {
		try {
			Alert Prompt = driver.switchTo().alert();
			Prompt.accept();
			Alert prom2 = driver.switchTo().alert();
			prom2.sendKeys("welcome");
			prom2.accept();
			driver.switchTo().defaultContent();

		} catch (Exception e) {
		}
	}

	// File upload method
	public void fileUpload(WebElement element, String file) {

		try {
			File file1 = new File(file);
			element.sendKeys(file1.getAbsolutePath());
		} catch (Exception e) {
		}
	}

	// Datadriven code
	public void dataDriven(String Sheetname, String Columnname) throws IOException {

		try {
			String excelpath = System.getProperty("user.dir") + "";
			FileInputStream fis = new FileInputStream(excelpath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int sheets = workbook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(Sheetname)) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row Firstrow = rows.next();
					Iterator<Cell> cell = Firstrow.iterator();
					while (cell.hasNext()) {
						Cell value = cell.next();
						if (value.getStringCellValue().equalsIgnoreCase(Columnname)) {

						}

					}

				}
			}

		} catch (Exception e) {
		}
	}

	// This is wrapper method wait for element presence located

	public void waitForElementPresence(By locator) throws NotFoundException {
		 WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// This is wrapper method wait for element to be clickable

	public void waitForElementToBeClickable(By locator) throws NotFoundException {
		 WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// This is wrapper method wait for visibility of element located

	public void waitForElementVisibilityLocated(By locator) throws NotFoundException {
		 WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// This is wrapper method wait for visibility of element

	public void waitForElementVisibility(WebElement webElement) throws NotFoundException {
		 WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	// This is wrapper method wait for Invisibility of element located

	public void waitForElementInVisibilityLocated(By locator) throws NotFoundException {
		 WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Waits for the specified amount of [timeInMilliseconds].

	public boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}

	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean checkElementDisplayed(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return driver.findElement(locator).isDisplayed();
		} catch (NotFoundException exception) {

			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean checkElementPresence(By locator) {
		try {
			this.waitForElementPresence(locator);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean isElementDisplayed(By locator) {
		try {
			 WebDriverWait wait= new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean checkElementVisibile(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return driver.findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean checkElementInVisibile(By locator) {
		try {
			this.waitForElementInVisibilityLocated(locator);
			return !driver.findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return true;
		}
	}

	// This is wrapper method to check the web element is displayed on the page

	public boolean waitForElementDisplayed(By locator, int timeInMiliSeconds) {
		try {
			WebDriverWait webDriverWait = new WebDriverWait(driver, timeInMiliSeconds);
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return driver.findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	// This method will switch you to the Frame by Frame name

	public boolean switchToFrameUsingIframeElement(By locator) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(locator));
			return true;
		} catch (NoSuchElementException exception) {

			exception.printStackTrace();
			return false;
		}
	}

	// This method will switch you to the Frame by Frame name

	public boolean switchToFrameUsingNameOrId(String frameName) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(frameName);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	// This method will switch you to the Frame by Frame name

	public boolean switchToFrameUsingIndex(int index) {
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(index);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description : This method will switch you to the default Window
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

// Take Screen shot for given web driver.

	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath)
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webDriver);
		//Call getScreenshotAs method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File destFile = new File(fileWithPath);
		//Copy file at destination
		try 
		{
			FileUtils.copyFile(srcFile, destFile);
			return true;
		} 
		catch (IOException iOException) 
		{
			iOException.printStackTrace();
			return false;
			
		}
	}
}