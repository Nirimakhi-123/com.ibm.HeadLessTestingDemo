package com.pack.HeadLess;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TC01_Demo_HeadLessTestingUsingHtmlUnitDriver {
	
	public static HtmlUnitDriver driver;
	
	@BeforeTest
	 public void setup() throws Exception {
	  //Initializing HtmlUnitDriver.
	  driver = new HtmlUnitDriver();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //To hide warnings logs from execution console.
	  Logger logger = Logger.getLogger("");
	  logger.setLevel(Level.OFF);
	  
	  //Opening URL In HtmlUnitDriver.
	  driver.get("https://www.facebook.com/");
	  
	  System.out.println("Set()- exeuted sucessfully..");
	 }

@Test
public void verifyFaceBookTitle() throws Exception{
	System.out.println(driver.getTitle());
	
	String FacebookTitle = driver.getTitle();
	Assert.assertTrue(FacebookTitle.contains("Facebook"));
	Thread.sleep(1000);
	
	driver.findElement(By.id("email")).sendKeys("abc@gmail.com");
	System.out.println("pass");
	
	driver.findElement(By.id("pass")).sendKeys("xyz");
	System.out.println("enter password..");
	//Thread.sleep(1000);
	driver.findElement(By.id("loginbutton")).click();
	System.out.println("Button cliked..");
	
	System.out.println("After login Face book page title.."+driver.getTitle());
	
	//After login Face book page title..Facebook
	System.out.println("verifyFaceBookTitle() .. pass this test cases.");

}

@Test()
public void verifyFaceBookTitleNew(){

	WebDriver driver = new HtmlUnitDriver();
	driver.get("https://www.facebook.com/");
	System.out.println(driver.getTitle());
	
	String FacebookTitle = driver.getTitle();
	Assert.assertTrue(FacebookTitle.contains("Selenium"));
	
	System.out.println("verifyFaceBookTitleNew().....Force fully Fail this test case");

}

@AfterMethod
public void tearDown(ITestResult result)
{

// Here will compare if test is failing then only it will enter into if condition
if(ITestResult.FAILURE==result.getStatus())
{
try 
{
// Create refernce of TakesScreenshot
TakesScreenshot ts=(TakesScreenshot)driver;
// Call method to capture screenshot
File source=ts.getScreenshotAs(OutputType.FILE);

// Copy files to specific location here it will save all screenshot in our project home directory and
// result.getName() will return name of test case so that screenshot name will be same
FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));

System.out.println("Screenshot taken");
} catch (Exception e)

{System.out.println("Exception while taking screenshot "+e.getMessage());} 

} //if
} // method
} //class end
