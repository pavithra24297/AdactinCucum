package com.lexicon.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lexicon.base.BaseClass;

public class LoginPage extends BaseClass{
	
	
	public LoginPage() {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@id='logonIdentifier']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtPass;
	
	@FindBy(xpath="//button[@id='next']")
	WebElement btnSignIn;
	
	/*@FindBy(xpath="//a[@id='forgotPassword']")
	WebElement lnkForgotPass;*/
	
	
	/** Set Email Address*/
	public void setEmailAddress(String email)
	{
		txtEmailAddress.sendKeys(email);
			
	}
	
	/** Set Password*/
	public void setPassword(String pass)
	{
		txtPass.sendKeys(pass);
	}
	
	/** click on SignIn Button*/
	public void clickOnSignInButton()
	{
		btnSignIn.click();
	}
	
	

}
