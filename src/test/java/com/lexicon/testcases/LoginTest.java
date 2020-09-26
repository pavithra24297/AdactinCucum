package com.lexicon.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lexicon.base.BaseClass;
import com.lexicon.pom.LoginPage;

public class LoginTest extends BaseClass{
	
	
	LoginPage objLoginPage;
	
	public LoginTest()
	{
		super();
	}
	
	public void InitializePages()
	{
		
		objLoginPage=new LoginPage();
		
	}
	
	@BeforeMethod
	public void setUp()
	{
		
		initializeDriver();
		getUrl();
		this.InitializePages();
	}
	
	@Test
	public void doLogin()
	{
		
		objLoginPage.setEmailAddress("dishantcodal@yopmail.com");
		objLoginPage.setPassword("Password@123");
		objLoginPage.clickOnSignInButton();
		
	}

	@AfterMethod
	public void tearDownEnviornment()
	{
		driver.close();
	}
}
