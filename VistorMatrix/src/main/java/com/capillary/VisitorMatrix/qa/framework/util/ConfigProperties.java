package com.capillary.VisitorMatrix.qa.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.BaseQueryExecutorPool;

public class ConfigProperties {

	private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	public String getProperty(String propKey) {
		return getPropertiesFile().getProperty(propKey);
	}
	
	private static Properties getPropertiesFile() {
		try {
			Properties prop = new Properties();
			if (System.getProperty("user.dir").contains("/home/arunkumarv")) {
				logger.info("inside if");
				prop.load(new FileInputStream(
						System.getProperty("user.dir") + File.separator + "config/testData/NightlyConfig.properties"));
			} else if (System.getenv("jenkins") != null) {
				logger.info("inside else if");
				prop.load(ConfigProperties.class.getResourceAsStream("./config/testData/NightlyConfig.properties"));
			} else {
				logger.info("inside else");
				logger.info(Paths.get("").toAbsolutePath().normalize().toString() + " /testData/NightlyConfig.properties");
				prop.load(new FileInputStream(Paths.get("").toAbsolutePath().normalize().toString()+"/testData/NightlyConfig.properties"));
			}
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
