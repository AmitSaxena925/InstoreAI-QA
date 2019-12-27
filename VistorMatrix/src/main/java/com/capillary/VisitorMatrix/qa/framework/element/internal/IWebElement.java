package com.capillary.VisitorMatrix.qa.framework.element.internal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

@SuppressWarnings("deprecation")
public interface IWebElement extends WebElement, WrapsElement, Locatable {

    boolean elementWired();

}