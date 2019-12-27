package com.capillary.VisitorMatrix.qa.framework.core;

import static com.capillary.VisitorMatrix.qa.framework.core.config.DriverType.CHROME;
import static com.capillary.VisitorMatrix.qa.framework.core.config.DriverType.valueOf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.capillary.VisitorMatrix.qa.framework.core.config.DriverType;

public class WebDriverThread {

	private WebDriver webDriver;
	private DriverType selectedDriverType;
	private final DriverType defaultDriverType = CHROME;
	private final String browser = ExecutionConfig.BROWSER.toUpperCase();
	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();

	public WebDriver getDriver() {
		if (null == webDriver || ((RemoteWebDriver) webDriver).getSessionId() == null) {
			selectedDriverType = determineEffectiveDriverType();
			DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
			instantiateWebDriver(desiredCapabilities);
			webDriver.manage().window().maximize();
		}
		return webDriver;
	}

	public void quitDriver() throws InterruptedException {
		if (null != webDriver) {
			webDriver.manage().deleteAllCookies();
			webDriver.quit();
			webDriver= null;
		}		
	}

	private DriverType determineEffectiveDriverType() {
		DriverType driverType = defaultDriverType;
		try {
			driverType = valueOf(browser);
		} catch (IllegalArgumentException ignored) {
			System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
		} catch (NullPointerException ignored) {
			System.err.println("No driver specified, defaulting to '" + driverType + "'...");
		}
		return driverType;
	}

	private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) {
		System.out.println(" ");
		System.out.println("Current Operating System: " + operatingSystem);
		System.out.println("Current Architecture: " + systemArchitecture);
		System.out.println("Current Browser Selection: " + selectedDriverType);
		System.out.println(" ");
		webDriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
	}
}