package com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages;

import org.openqa.selenium.By;

import com.capillary.VisitorMatrix.qa.framework.base.BasePage;
import com.capillary.VisitorMatrix.qa.framework.element.HTMButton;
import com.capillary.VisitorMatrix.qa.framework.element.HTMTextInput;

public class IntouchLoginPage extends BasePage {

	public IntouchLoginPage(String pageName) {
		super(pageName);
	}
	
	public HTMTextInput userName()
	{
		return new HTMTextInput(By.id("login_user"), getPageName(), "UserName TextBox");
	}
	
	public HTMTextInput Password()
	{
		return new HTMTextInput(By.id("login_cred"), getPageName(), "Password  TextBox");
	}
	
	public HTMButton submitLogin()
	{
		return new HTMButton(By.name("submit"), getPageName(), "Login Button");
	}
	public void login(String userName, String password)
	{
		userName().click();
		userName().sendKeys(userName);
		Password().click();
		Password().sendKeys(password);
		submitLogin().click();
	}
}
