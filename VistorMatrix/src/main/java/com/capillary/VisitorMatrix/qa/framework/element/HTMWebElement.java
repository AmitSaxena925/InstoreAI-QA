package com.capillary.VisitorMatrix.qa.framework.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.fest.assertions.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import com.capillary.VisitorMatrix.qa.framework.element.internal.IWebElement;
import com.capillary.VisitorMatrix.qa.framework.core.DriverFactory;
import com.capillary.VisitorMatrix.qa.framework.util.CustomExpectedConditions;

public class HTMWebElement implements IWebElement {

	private String elementName;
	private String pageName;
	private String elementType;
	private By by;
	private WebElement webElement;
	private ArrayList<WebElement> webElements;

	public HTMWebElement(By by, String pageName, String elementName) {
		this.by = by;
		this.pageName = pageName;
		this.elementName = elementName;
		this.elementType = "Element";
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementType() {
		return this.elementType;
	}

	public String getPageName() {
		return this.pageName;
	}

	public String getElementName() {
		return this.elementName;
	}

	public By getBy() {
		return this.by;
	}

	public void click() {
		Reporter.log("<b>Click on " + getElementType() + "</b><br> Page | Element => " + getPageName() + " | "
				+ this.elementName + "<br>");
		try {
			getWrappedElement().click();
		} catch (WebDriverException e) {
			if (e.getMessage().contains("unknown error: Element is not clickable at point")) {
				Reporter.log("Retrying due to the error: Element is not clickable at point(X, Y)<br>");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				getWrappedElement().click();
			} else {
				throw e;
			}
		}
	}

	public void sendKeys(CharSequence... keysToSend) {
		Reporter.log("<b>Type into " + getElementType() + "</b><br> Page | Element | Text => " + this.pageName + " | "
				+ this.elementName + " | " + Arrays.toString(keysToSend) + "<br>");
		getWrappedElement().sendKeys(keysToSend);
	}

	public Point getLocation() {
		return getWrappedElement().getLocation();
	}

	public void submit() {
		Reporter.log("<b>Click on " + getElementType() + " to submit</b><br> Page | Element => " + this.pageName + " | "
				+ this.elementName + "<br>");
		getWrappedElement().submit();
	}

	public String getAttribute(String name) {
		return getWrappedElement().getAttribute(name);
	}

	public String getCssValue(String propertyName) {
		return getWrappedElement().getCssValue(propertyName);
	}

	public Dimension getSize() {
		return getWrappedElement().getSize();
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	public List<WebElement> findElements(By by) {
		return getWrappedElement().findElements(by);
	}

	public String getText() {
		return getWrappedElement().getText();
	}

	public String getTagName() {
		return getWrappedElement().getTagName();
	}

	public boolean isSelected() {
		return getWrappedElement().isSelected();
	}

	public WebElement findElement(By by) {
		return getWrappedElement().findElement(by);
	}

	public boolean isEnabled() {
		return getWrappedElement().isEnabled();
	}

	public boolean isDisplayed() {
		WebElement element;
		try {
			element = getWrappedElement();
		} catch (NoSuchElementException e) {
			return false;
		}
		return element.isDisplayed();
	}

	public void clear() {
		Reporter.log("<b>Clear " + getElementType() + " text</b> <br> Page | Element => " + this.pageName + " | "
				+ this.elementName + "<br>");
		getWrappedElement().clear();
	}

	public WebElement getWrappedElement() {
		if (this.webElement == null) {
			this.webElement = DriverFactory.getDriver().findElement(this.by);
		}
		return this.webElement;
	}

	public ArrayList<WebElement> getWrappedElements() {
		if (this.webElements == null) {
			this.webElements = (ArrayList<WebElement>) DriverFactory.getDriver().findElements(this.by);
		}
		return this.webElements;
	}

	public Coordinates getCoordinates() {
		return (Coordinates) ((Locatable) getWrappedElement()).getCoordinates();
	}

	public boolean elementWired() {
		return (webElement != null);
	}

	public void focus() {
		Reporter.log("<b>Focus on " + getElementType() + "</b><br> Page | Element => " + getPageName() + " | "
				+ getElementName() + "<br>");
		new Actions(DriverFactory.getDriver()).moveToElement(getWrappedElement()).perform();
	}

	public void assertVisible() {
		Reporter.log("<b>Assert " + getElementType() + " is visible</b> <br> Page | Element => " + getPageName() + " | "
				+ getElementName() + "<br>");
		Assertions.assertThat(isDisplayed()).isEqualTo(true);
	}

	public void assertNotVisible() {
		Reporter.log("<b>Assert " + getElementType() + " is not visible</b> <br> Page | Element => " + getPageName()
				+ " | " + getElementName() + "<br>");
		Assertions.assertThat(isDisplayed()).isEqualTo(false);
	}

	public void assertText(String text) {
		Reporter.log("<b>Assert " + getElementType() + " has text</b> <br> Page | Element | Text => " + getPageName()
				+ " | " + getElementName() + " | " + text + "<br>");
		Assertions.assertThat(getText()).isEqualToIgnoringCase(text);
	}

	public void assertContainsText(String text) {
		Reporter.log("<b>Assert " + getElementType() + " contains text</b> <br> Page | Element | Text => "
				+ getPageName() + " | " + getElementName() + " | " + text + "<br>");
		Assertions.assertThat(getText()).containsIgnoringCase(text);
	}

	public void assertDoesNotContainText(String text) {
		Reporter.log("<b>Assert " + getElementType() + " doesn't contain text</b> <br> Page | Element | Text => "
				+ getPageName() + " | " + getElementName() + " | " + text + "<br>");
		Assertions.assertThat(getText()).doesNotContain(text);
	}

	public void assertAlertContainsText(String text) {
		Reporter.log("<b>Assert that alert contains text in</b> <br> Page | Text => " + getPageName() + " | " + text
				+ "<br>");
		Assertions.assertThat(DriverFactory.getDriver().switchTo().alert().getText()).contains(text);
		DriverFactory.getDriver().switchTo().alert().accept();
	}

	private boolean isFocused() {
		return getWrappedElement().equals(DriverFactory.getDriver().switchTo().activeElement());
	}

	public void assertIsFocused() {
		Reporter.log("<b>Assert " + getElementType() + " is focused</b> <br> Page | Element => " + getPageName() + " | "
				+ getElementName() + "<br>");
		Assertions.assertThat(isFocused()).isEqualTo(true);
	}

	public void assertIsNotFocused() {
		Reporter.log("<b>Assert " + getElementType() + " is not focused</b> <br> Page | Element => " + getPageName()
				+ " | " + getElementName() + "<br>");
		Assertions.assertThat(isFocused()).isEqualTo(false);
	}

	public void waitUntilContainsText(String text) {
		Reporter.log("<b>Wait until " + getElementType() + " contains text </b><br> Page | Element | Text => "
				+ getPageName() + " | " + getElementName() + " | " + text + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.textToBePresentInElementLocated(getBy(), text));
	}

	public void waitUntilDoesNotContainText(String text) {
		Reporter.log("<b>Wait until " + getElementType() + " doesn't contain text </b><br> Page | Element | Text => "
				+ getPageName() + " | " + getElementName() + " | " + text + "<br>");
		DriverFactory.getWait()
				.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(getBy(), text)));
	}

	public void waitUntilPresenceOfElementLocated() {
		Reporter.log("<b>Wait until " + getElementType() + " presence of element is located </b><br> Page | Element => "
				+ getPageName() + " | " + getElementName() + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.presenceOfElementLocated(getBy()));
	}

	public void waitUntilVisible() {
		Reporter.log("<b>Wait until " + getElementType() + " is visible </b><br> Page | Element => " + getPageName()
				+ " | " + getElementName() + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(getBy()));
	}

	public void waitUntilNotVisible() {
		Reporter.log("<b>Wait until " + getElementType() + " is not visible </b><br> Page | Element => " + getPageName()
				+ " | " + getElementName() + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
	}

	public void waitUntilEditable() {
		Reporter.log("<b>Wait until " + getElementType() + " is editable </b><br> Page | Element => " + getPageName()
				+ " | " + getElementName() + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.elementToBeClickable(getBy()));
	}

	public void waitUntilNotEditable() {
		Reporter.log("<b>Wait until " + getElementType() + " is not editable </b><br> Page | Element => "
				+ getPageName() + " | " + getElementName() + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getBy())));
	}

	public void waitUntilContainsAttributeValue(String attributeName, String attributeValue) {
		Reporter.log("<b>Wait until " + getElementType()
				+ " contains attribute value </b><br> Page | Element | Attribute | Value => " + getPageName() + " | "
				+ getElementName() + " | " + attributeName + " | " + attributeValue + "<br>");
		DriverFactory.getWait().until(
				CustomExpectedConditions.attributeValueToBeContainedInElement(getBy(), attributeName, attributeValue));
	}

	public void waitUntilDoesNotContainAttributeValue(String attributeName, String attributeValue) {
		Reporter.log("<b>Wait until " + getElementType()
				+ " doesn't contain attribute value </b><br> Page | Element | Attribute | Value => " + getPageName()
				+ " | " + getElementName() + " | " + attributeName + " | " + attributeValue + "<br>");
		DriverFactory.getWait().until(ExpectedConditions.not(
				CustomExpectedConditions.attributeValueToBeContainedInElement(getBy(), attributeName, attributeValue)));
	}

	public void waitUntilContainsAttribute(String attributeName) {
		Reporter.log("<b>Wait until " + getElementType()
				+ " contains attribute value </b><br> Page | Element | Attribute | Value => " + getPageName() + " | "
				+ getElementName() + " | " + attributeName + "<br>");
		DriverFactory.getWait().until(CustomExpectedConditions.attributeToBeContainedInElement(getBy(), attributeName));
	}

	public void waitUntilDoesNotContainAttribute(String attributeName) {
		Reporter.log("<b>Wait until " + getElementType()
				+ " doesn't contain attribute value </b><br> Page | Element | Attribute | Value => " + getPageName()
				+ " | " + getElementName() + " | " + attributeName + "<br>");
		DriverFactory.getWait().until(ExpectedConditions
				.not(CustomExpectedConditions.attributeToBeContainedInElement(getBy(), attributeName)));
	}

	public HTMWebElement and() {
		return this;
	}

	/**
	 * Scroll to make the element visible on the view port
	 *
	 */

	public void scrollToView() {
		String script = "arguments[0].scrollIntoView(true);";
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript(script, getWrappedElement());
	}

	public void setattribute(String attributeName, String attributeValue) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		js.executeScript("arguments[0].setAttribute(aruguments[1],arguments[2]);", getWrappedElement(), attributeName,
				attributeValue);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
		return null;
	}

	public void fnHighlightMe() throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		for (int iCnt = 0; iCnt < 3; iCnt++) {
			// Execute java-script
			js.executeScript("arguments[0].style.border='4px groove green'", getWrappedElement());
			Thread.sleep(10000);
			js.executeScript("arguments[0].style.border=''", getWrappedElement());
		}
	}

	public void waitforElementPresent(int waitTime, TimeUnit unit) {
		Reporter.log("<b>Wait until " + getElementType() + " is visible within " + waitTime + " " + unit
				+ "s </b><br> Page | Element => " + getPageName() + " | " + getElementName() + "<br>");

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverFactory.getDriver()).withTimeout(waitTime, unit)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			
			public Boolean apply(WebDriver driver) {
				
				if (getWrappedElement().isDisplayed()) {
					return true;
				}
				return false;				
			}
		};
		wait.until(function);
	}
}