package com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.common;

import org.openqa.selenium.By;

import com.capillary.VisitorMatrix.qa.framework.base.BasePage;
import com.capillary.VisitorMatrix.qa.framework.element.HTMLabel;
import com.capillary.VisitorMatrix.qa.framework.element.HTMLink;
import com.capillary.VisitorMatrix.qa.framework.element.HTMTextInput;

public class Header extends BasePage{

    public Header(String pageName) {
        super(pageName);
    }


    /**
     * Navigates to menu items by clicking on all the provided links in the specified order
     * @param menuLinks array of link texts (menu links to click) as seen on the UI
     *
     */
    public void navigateTo(String ...menuLinks){
        for(String link: menuLinks){
            HTMLink menuElement = new HTMLink(By.linkText(link), getPageName(), link + " Menu HTMLink");
            menuElement.waitUntilClickable();
            menuElement.click();
        }
        waitUntilAllAJAXCallsFinish();
    }
    
    public HTMLink settings()
	{
		return new HTMLink(By.id("user-settings"),getPageName(),"Settings Link");
	}
    
    public HTMTextInput orgSelection()
    {
    	return new HTMTextInput(By.xpath("//i[@id='org_selection_dropdown']/parent::span[contains(text(),'Capillary Technologies')]"), getPageName(), "org selection TextBox");
    }
    
    public HTMTextInput orgtextbox()
    {
    	return new HTMTextInput(By.id("org-search-div"), getPageName(), "Enter orgname");
    }
    
    public HTMLabel orgName(String orgName)
    {
    	return new HTMLabel(By.xpath("//a[ contains(text(),'"+orgName+"')]"), getPageName(), "select entered org Name");
    }
}
