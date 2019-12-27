package com.capillary.VisitorMatrix.qa.framework.core.config;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum DriverType implements DriverSetup {

	FIREFOX {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			System.setProperty("webdriver.gecko.driver", ExecutionConfig.getTestDataPath()+"/geckodriver");
			return new FirefoxDriver(capabilities);
		}
	},

	CHROME {
		public DesiredCapabilities getDesiredCapabilities() {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
			HashMap<String, String> chromePreferences = new HashMap<String, String>();
			chromePreferences.put("profile.password_manager_enabled", "false");
			capabilities.setCapability("chrome.prefs", chromePreferences);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
	        options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--test-type");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("start-maximized");
			options.addArguments("disable-extensions");
			options.addArguments("no-sandbox");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("forceDevToolsScreenshot", true);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			return capabilities;
		}

		public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", ExecutionConfig.getTestDataPath()+"/chromedriver");
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
	        options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--test-type");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("start-maximized");
			options.addArguments("disable-extensions");
			options.addArguments("no-sandbox");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("forceDevToolsScreenshot", true);
			return new ChromeDriver(options);
		}
	}
}
