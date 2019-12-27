package com.capillary.VisitorMatrix.qa.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {

	private static PropertyUtil prop;
	private Properties properties;

	private PropertyUtil() {
		properties = new Properties();
	}

	public static synchronized PropertyUtil getInstance() {
		if (prop == null) {
			prop = new PropertyUtil();
		}
		return prop;
	}

	public void load(String fileName) throws IOException {
//		InputStream input = null;
//		input = getClass().getClassLoader().getResourceAsStream(fileName);
//		properties.load(input);
		properties.load(new FileInputStream(fileName));
	}

	public void load(File file) throws IOException {
		InputStream input = new FileInputStream(file);
		properties.load(input);
	}

	public void clear() {
		properties.clear();
	}

	public String getValue(String key) {
		return properties.getProperty(key).trim();
	}

	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	public void propertytonull() {
		prop = null;
	}

	public HashMap<String, String> propertiesMap() {
		HashMap<String, String> map = new HashMap<String, String>((Map) properties);
		return map;
	}

	public  static Properties getPropertiesFile() {
		try {
			Properties prop = new Properties();
			if (System.getProperty("user.dir").contains("workspace")) {
				prop.load(new FileInputStream(
						System.getProperty("user.dir") + File.separator + "testData/NightlyConfig.properties"));
			} else if (System.getenv("jenkins") != null) {
				prop.load(PropertyUtil.class.getResourceAsStream("./testData/NightlyConfig.properties"));
			} else {
				prop.load(PropertyUtil.class.getResourceAsStream("testData/NightlyConfig.properties"));
			}
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
