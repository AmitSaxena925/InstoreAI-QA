package com.capillary.VisitorMatrix.qa.framework.core;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capillary.VisitorMatrix.qa.framework.util.ConfigProperties;
import com.capillary.VisitorMatrix.qa.framework.util.PropertyUtil;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

public class ExecutionConfig {
	private static final Logger logger = LoggerFactory.getLogger(ConfigProperties.class);
	public static String CLUSTER;
	public static int WAIT_TIME;
	public static String BROWSER;
	public static final String FFC_DATA_PATH;
	public static final String HEAT_MAP_DATA_PATH;
	public static final String TEST_DATA_PATH;
	public static final String STORECARE_V2_FFCDETAILS_URL;
	public static final String STORECARE_FFCDETAILS_URL;
	public static final String SEND_NOTIFICATION;
	public static final String ORG_ID;
	public static final String FFC_CONFIG_ID;
	public static final String EXPECTED_NUM_NOTIF;
	public static final String ADD_TRANSACTION;
	public static final String ADD_CUSTOMER;
	public static final String AUTH_LOGIN;
	public static final String HM_ADD_DEVICE;
	public static final String HM_GET_DEVICES;
	public static final String HM_ADD_LAYOUT;
	public static final String HM_GET_LAYOUT_HISTORY;
	public static final String HM_ADD_ZONE;
	public static final String HM_GET_ZONES_LAYOUTID;
	public static final String HM_GET_ZONES_DEVICEID;
	public static final String HM_MONGODB_IP;
	public static final String HM_MONGODB_PORT;
	public static final String MONGODB_USER_NAME;
	public static final String MONGODB_ROLE;
	public static final String MONGODB_PASSWORD;
	public static final String USERNAME_791;
	public static final String PASSWORD_791;
	public static final String DR_BASE_URL;
	public static final String DR_ADD_DEVICE_URL;
	public static final String DR_DETACH_DEVICE_BYDEVICEID_URL;
	public static final String DR_DETACH_DEVICE_BYTILL_URL;
	public static final String DR_META_DB;
	public static final String DR_META_DB_TABLE;
	public static final String DR_DISCARD_DEVICE_URL;
	public static final String REPORT_FROM_USERNAME;
	public static final String REPORT_FROM_USERNAME_PASSWORD;
	public static final String AI_TEAM_REPORT_TO_USERNAME;
	public static final String EMAILREPORTTEMPLATEFILE;
	public static final String INTOUCH_BASE_URL;
	public static final String DR_DB_IP_ADDRESS;
	public static final String DR_DB_IP_PORT;
	public static final String DR_DB_USER_NAME;
	public static final String DR_DB_PASSWORD;

	// FFCR device API automation details
	public static final String FFCRBASEURL;
	public static final Long FFCRDEVCIEID;
	public static final String FFCRSERVICESTART;
	public static final String FFCRSERVICESTOP;
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
	public static final String FFCRDETACHTILL;
	public static final String FFCRNETWORKSTATUS;
	static {
//		File file = null;
		try {
			// if(ClusterInfo.getInstance().getCluster().equals("NIGHTLY"))
			// {
			// file = new File("testData/NightlyConfig.properties");
			// }
			// else if (ClusterInfo.getInstance().getCluster().equals("PROD"))
			// {
			// file = new File("testData/ProdConfig.properties");
			// }
			TEST_DATA_PATH = getTestDataPath();
			// ConfigProperties PropertyUtil.getInstance() = new ConfigProperties();
			PropertyUtil.getInstance().load(ExecutionConfig.TEST_DATA_PATH + "/config.properties");
			BROWSER = PropertyUtil.getInstance().getValue("BROWSER");
			WAIT_TIME = Integer.parseInt(PropertyUtil.getInstance().getValue("WAIT_TIME"));
			FFC_DATA_PATH = PropertyUtil.getInstance().getValue("FFC_DATA_PATH");
			HEAT_MAP_DATA_PATH = PropertyUtil.getInstance().getValue("HEAT_MAP_DATA_PATH");
			HM_MONGODB_IP = PropertyUtil.getInstance().getValue("HM_MONGODB_IP");
			HM_MONGODB_PORT = PropertyUtil.getInstance().getValue("HM_MONGODB_PORT");
			STORECARE_V2_FFCDETAILS_URL = PropertyUtil.getInstance().getValue("STORECARE_V2_FFCDETAILS_URL");
			STORECARE_FFCDETAILS_URL = PropertyUtil.getInstance().getValue("STORECARE_FFCDETAILS_URL");
			SEND_NOTIFICATION = PropertyUtil.getInstance().getValue("SEND_NOTIFICATION");
			ORG_ID = PropertyUtil.getInstance().getValue("ORG_ID");
			FFC_CONFIG_ID = PropertyUtil.getInstance().getValue("FFC_CONFIG_ID");
			EXPECTED_NUM_NOTIF = PropertyUtil.getInstance().getValue("EXPECTED_NUM_NOTIF");
			ADD_TRANSACTION = PropertyUtil.getInstance().getValue("ADD_TRANSACTION");
			ADD_CUSTOMER = PropertyUtil.getInstance().getValue("ADD_CUSTOMER");
			AUTH_LOGIN = PropertyUtil.getInstance().getValue("AUTH_LOGIN");
			HM_ADD_DEVICE = PropertyUtil.getInstance().getValue("HM_ADD_DEVICE");
			HM_GET_DEVICES = PropertyUtil.getInstance().getValue("HM_GET_DEVICES");
			HM_ADD_LAYOUT = PropertyUtil.getInstance().getValue("HM_ADD_LAYOUT");
			HM_GET_LAYOUT_HISTORY = PropertyUtil.getInstance().getValue("HM_GET_LAYOUT_HISTORY");
			HM_ADD_ZONE = PropertyUtil.getInstance().getValue("HM_ADD_ZONE");
			HM_GET_ZONES_LAYOUTID = PropertyUtil.getInstance().getValue("HM_GET_ZONES_LAYOUTID");
			HM_GET_ZONES_DEVICEID = PropertyUtil.getInstance().getValue("HM_GET_ZONES_DEVICEID");
			MONGODB_USER_NAME = PropertyUtil.getInstance().getValue("MONGODB_USER_NAME");
			MONGODB_ROLE = PropertyUtil.getInstance().getValue("MONGODB_ROLE");
			MONGODB_PASSWORD = PropertyUtil.getInstance().getValue("MONGODB_PASSWORD");
			PASSWORD_791 = PropertyUtil.getInstance().getValue("PASSWORD_791");
			USERNAME_791 = PropertyUtil.getInstance().getValue("USERNAME_791");
			DR_BASE_URL = PropertyUtil.getInstance().getValue("DR_BASE_URL");
			DR_DB_IP_ADDRESS = PropertyUtil.getInstance().getValue("DR_DB_IP_ADDRESS");
			DR_DB_IP_PORT = PropertyUtil.getInstance().getValue("DR_DB_IP_PORT");
			DR_DB_USER_NAME = PropertyUtil.getInstance().getValue("DR_DB_USER_NAME");
			DR_DB_PASSWORD = PropertyUtil.getInstance().getValue("DR_DB_PASSWORD");
			DR_ADD_DEVICE_URL = PropertyUtil.getInstance().getValue("DR_ADD_DEVICE_URL");
			DR_DETACH_DEVICE_BYDEVICEID_URL = PropertyUtil.getInstance().getValue("DR_DETACH_DEVICE_BYDEVICEID_URL");
			DR_DETACH_DEVICE_BYTILL_URL = PropertyUtil.getInstance().getValue("DR_DETACH_DEVICE_BYTILL_URL");
			DR_DISCARD_DEVICE_URL = PropertyUtil.getInstance().getValue("DR_DISCARD_DEVICE_URL");
			DR_META_DB = PropertyUtil.getInstance().getValue("DR_META_DB");
			DR_META_DB_TABLE = PropertyUtil.getInstance().getValue("DR_META_DB_TABLE");
			AI_TEAM_REPORT_TO_USERNAME = PropertyUtil.getInstance().getValue("AI_TEAM_REPORT_TO_USERNAME");
			REPORT_FROM_USERNAME = PropertyUtil.getInstance().getValue("REPORT_FROM_USERNAME");
			REPORT_FROM_USERNAME_PASSWORD = PropertyUtil.getInstance().getValue("REPORT_FROM_USERNAME_PASSWORD");
			EMAILREPORTTEMPLATEFILE = PropertyUtil.getInstance().getValue("EMAILREPORTTEMPLATEFILE");
			INTOUCH_BASE_URL = PropertyUtil.getInstance().getValue("INTOUCH_BASE_URL");

			FFCRBASEURL = PropertyUtil.getInstance().getValue("FFCRBASEURL");
			FFCRDEVCIEID = Long.parseLong(PropertyUtil.getInstance().getValue("FFCRDEVCIEID"));
			FFCRSERVICESTART = PropertyUtil.getInstance().getValue("FFCRSERVICESTART");
			FFCRSERVICESTOP = PropertyUtil.getInstance().getValue("FFCRSERVICESTOP");
			FFCRSERVICESTATUS = PropertyUtil.getInstance().getValue("FFCRSERVICESTATUS");
			FFCRSETRTC = PropertyUtil.getInstance().getValue("FFCRSETRTC");
			FFCRGETRTC = PropertyUtil.getInstance().getValue("FFCRGETRTC");
			FFCRSETCONFIG = PropertyUtil.getInstance().getValue("FFCRSETCONFIG");
			FFCRADDTILL = PropertyUtil.getInstance().getValue("FFCRADDTILL");
			FFCRGETTILL = PropertyUtil.getInstance().getValue("FFCRGETTILL");
			FFCRDETACHTILL = PropertyUtil.getInstance().getValue("FFCRDETACHTILL");
			FFCRADDSTAFFDATA = PropertyUtil.getInstance().getValue("FFCRADDSTAFFDATA");
			FFCRADDCUSTOMERDATA = PropertyUtil.getInstance().getValue("FFCRADDCUSTOMERDATA");
			FFCRTESTMODE = PropertyUtil.getInstance().getValue("FFCRTESTMODE");
			FFCRHEARTBEATSTATUS = PropertyUtil.getInstance().getValue("FFCRHEARTBEATSTATUS");
			FFCRGETCONFIG = PropertyUtil.getInstance().getValue("FFCRGETCONFIG");
			FFCRGETUNSYCDATA = PropertyUtil.getInstance().getValue("FFCRGETUNSYCDATA");
			FFCRGETIMAGE = PropertyUtil.getInstance().getValue("FFCRGETIMAGE");
			FFCRQCCHECK = PropertyUtil.getInstance().getValue("FFCRQCCHECK");
			FFCRSYNCDB = PropertyUtil.getInstance().getValue("FFCRSYNCDB");
			FFCRNETWORKSTATUS = PropertyUtil.getInstance().getValue("FFCRNETWORKSTATUS");
			
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
