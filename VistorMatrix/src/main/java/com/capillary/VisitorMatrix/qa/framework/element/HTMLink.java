package com.capillary.VisitorMatrix.qa.framework.element;

import org.fest.assertions.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.capillary.VisitorMatrix.qa.framework.element.internal.ILink;
import com.capillary.VisitorMatrix.qa.framework.core.DriverFactory;

public class HTMLink extends HTMWebElement implements ILink {

    public HTMLink(By by, String pageName, String elementName) {
        super(by, pageName, elementName);
        setElementType("HTMLink");
    }

    public void assertClickable(){
        Reporter.log("<b>Assert that " + getElementType() + " is clickable</b> <br> Page | Element => " + getPageName() + " | " + getElementName() + " <br>");
        Assertions.assertThat(isEnabled()).isEqualTo(true);
    }

    public void assertNotClickable(){
        Reporter.log("<b>Assert that " + getElementType() + " is not clickable</b> <br> Page | Element => " + getPageName() + " | " + getElementName() + " <br>");
        Assertions.assertThat(isEnabled()).isEqualTo(false);
    }

    public void waitUntilClickable(){
        Reporter.log("<b>Wait until " + getElementType() + " is clickable </b><br> Page | Element => " + getPageName() + " | " + getElementName() + "<br>");
        DriverFactory.getWait().until(ExpectedConditions.elementToBeClickable(getBy()));
    }

    public void waitUntilNotClickable(){
        Reporter.log("<b>Wait until " + getElementType() + " is not clickable </b><br> Page | Element => " + getPageName() + " | " + getElementName() + "<br>");
        DriverFactory.getWait().until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getBy())));
    }
}
