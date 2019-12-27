package com.capillary.VisitorMatrix.qa.framework.element.internal;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface ISelect extends IWebElement {

    boolean isMultiple();

    void deselectByIndex(int index);

    void selectByValue(String value);

    WebElement getFirstSelectedOption();

    void selectByVisibleText(String text);

    void deselectByValue(String value);

    void deselectAll();

    List<WebElement> getAllSelectedOptions();

    List<WebElement> getOptions();

    void deselectByVisibleText(String text);

    void selectByIndex(int index);

}
