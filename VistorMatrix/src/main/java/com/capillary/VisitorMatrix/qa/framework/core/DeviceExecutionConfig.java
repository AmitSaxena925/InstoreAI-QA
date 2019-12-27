package com.capillary.VisitorMatrix.qa.framework.core;

import java.io.File;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capillary.VisitorMatrix.qa.framework.util.ConfigProperties;
import com.capillary.VisitorMatrix.qa.framework.util.PropertyUtil;

public class DeviceExecutionConfig {
	private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	public static final String FFCRBASEURL;
	public static final String FFCRSERVICESTART;
	public static final String FFCRSERVICESTOP;
	public static final String TEST_DATA_PATH;
	public static final String FFCRSERVICESTATUS;
	public static final String FFCRSETRTC;
	public static final String FFCRGETRTC;
	public static final String FFCRSETCONFIG;
	public static final String FFCRADDTILL;
	public static final String FFCRGETTILL;
	public static final String FFCRADDSTAFFDATA;
	public static final String FFCRADDCUSTOMERDATA;
	public static final String FFCRTESTMODE;
	public static final String FFCRHEARTBEATSTATUS;
	public static final String FFCRGETCONFIG;
	public static final String FFCRGETUNSYCDATA;
	public static final String FFCRGETIMAGE;
	public static final String FFCRQCCHECK;
	public static final String FFCRSYNCDB;
	static {
		try {
			TEST_DATA_PATH = getTestDataPath();
//			PropertyUtil.getInstance().load(ExecutionConfig.TEST_DATA_PATH + "/config.properties");
			FFCRBASEURL = PropertyUtil.getInstance().getValue("FFCRBASEURL");
			FFCRSERVICESTART = PropertyUtil.getInstance().getValue("FFCRSERVICESTART");
			FFCRSERVICESTOP = PropertyUtil.getInstance().getValue("FFCRSERVICESTOP");
			FFCRSERVICESTATUS = PropertyUtil.getInstance().getValue("FFCRSERVICESTATUS");
			FFCRSETRTC = PropertyUtil.getInstance().getValue("FFCRSETRTC");
			FFCRGETRTC = PropertyUtil.getInstance().getValue("FFCRGETRTC");
			FFCRSETCONFIG = PropertyUtil.getInstance().getValue("FFCRSETCONFIG");
			FFCRADDTILL = PropertyUtil.getInstance().getValue("FFCRADDTILL");
			FFCRGETTILL = PropertyUtil.getInstance().getValue("FFCRGETTILL");
			FFCRADDSTAFFDATA = PropertyUtil.getInstance().getValue("FFCRADDSTAFFDATA");
			FFCRADDCUSTOMERDATA = PropertyUtil.getInstance().getValue("FFCRADDCUSTOMERDATA");
			FFCRTESTMODE = PropertyUtil.getInstance().getValue("FFCRTESTMODE");
			FFCRHEARTBEATSTATUS = PropertyUtil.getInstance().getValue("FFCRHEARTBEATSTATUS");
			FFCRGETCONFIG = PropertyUtil.getInstance().getValue("FFCRGETCONFIG");
			FFCRGETUNSYCDATA = PropertyUtil.getInstance().getValue("FFCRGETUNSYCDATA");
			FFCRGETIMAGE = PropertyUtil.getInstance().getValue("FFCRGETIMAGE");
			FFCRQCCHECK = PropertyUtil.getInstance().getValue("FFCRQCCHECK");
			FFCRSYNCDB = PropertyUtil.getInstance().getValue("FFCRSYNCDB");
			
			System.setProperty("org.uncommons.reportng.escape-output", "false");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Something wrong !!! Check configurations.", e);
		}
	}

	public static String getTestDataPath() {
		try {
			if (System.getProperty("user.dir").contains("/Users/arun.v")) {
				System.out.println(
						"The test data path is " + System.getProperty("user.dir") + File.separator + "config/testData");
				return System.getProperty("user.dir") + File.separator + "config/testData";
			} else if (System.getenv("jenkins") != null) {
				return "./config/testData";
			} else {
				return Paths.get("").toAbsolutePath().normalize().toString() + "/testData";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTestSuitePath() {
		try {
			if (System.getProperty("user.dir").contains("/Users/arun.v")) {
				System.out.println("The test data path is " + System.getProperty("user.dir") + File.separator
						+ "config/testSuite");
				return System.getProperty("user.dir") + File.separator + "config/testSuite";
			} else if (System.getenv("jenkins") != null) {
				return "./config/testSuite";
			} else {
				return Paths.get("").toAbsolutePath().normalize().toString() + "/testSuite";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
