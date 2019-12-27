package com.capillary.VisitorMatrix.qa.framework.element;

import org.fest.assertions.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.capillary.VisitorMatrix.qa.framework.element.internal.ICheckBox;
import com.capillary.VisitorMatrix.qa.framework.core.DriverFactory;

public class HTMCheckBox extends HTMWebElement implements ICheckBox {

    public HTMCheckBox(By by, String pageName, String elementName) {
        super(by, pageName, elementName);
        setElementType("Checkbox");
    }

    public void toggle() {
        Reporter.log("<b>Click on " + getElementType() + "</b><br> Page | Element => " + getPageName() + " | " +
                getElementName() + "<br>");
        click();
    }

    public void check() {
        Reporter.log("<b>Check " + getElementType() + "</b><br> Page | Element => " + getPageName() + " | " +
                getElementName() + "<br>");
        if (!isChecked()) {
            toggle();
        }
    }

    public void unCheck() {
        Reporter.log("<b>Un-check " + getElementType() + "</b><br> Page | Element => " + getPageName() + " | " +
                getElementName() + "<br>");
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }

    public void assertChecked(){
        Reporter.log("<b>Assert that " + getElementType() + " is checked</b> <br> Page | Element => " + getPageName() + " | " + getElementName() + "<br>");
        Assertions.assertThat(isChecked()).isEqualTo(true);
    }

    public void assertUnChecked(){
        Reporter.log("<b>Assert that " + getElementType() + " is un-checked</b> <br> Page | Element => " + getPageName() + " | " + getElementName() + "<br>");
        Assertions.assertThat(isChecked()).isEqualTo(false);
    }

    public void waitUntilChecked(){
        Reporter.log("<b>Wait until " + getElementType() + " is checked</b> <br> Page | element => " + getPageName() + " | " + getElementName());
        DriverFactory.getWait().until(ExpectedConditions.elementSelectionStateToBe(getBy(), true));
    }

    public void waitUntilUnChecked(){
        Reporter.log("<b>Wait until " + getElementType() + " is un-checked</b> <br> Page | element => " + getPageName() + " | " + getElementName());
        DriverFactory.getWait().until(ExpectedConditions.elementSelectionStateToBe(getBy(), false));
    }
}
