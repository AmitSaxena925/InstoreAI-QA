package com.capillary.VisitorMatrix.api.test.deviceRegistration;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Predicate;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import com.capillary.VisitorMatrix.qa.framework.core.DriverFactory;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.DataImportPage;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.IntouchHomePage;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.IntouchLoginPage;
import com.capillary.VisitorMatrix.ui.test.testScripts.DeviceDetails;
import com.mysql.jdbc.Driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceRegistrationValidation {

	public static WebDriver driver = null;
	public static ArrayList<LinkedHashMap<String, String>> devicedata;
	public static DeviceRegistrationValidation drv;
	public static List<DeviceDetails> csvDeviceDetails;
	public static final Logger LOGGER = LoggerFactory.getLogger(DeviceRegistrationValidation.class);
	public static Connection conn;
	public static LinkedList<DeviceDetails> dbDeviceDetails;

	public static void createConnection() throws Exception {
		LOGGER.info("tyring to create connection");
		try {
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			Properties properties = new Properties();
			properties.setProperty("user", ExecutionConfig.DR_DB_USER_NAME);
			properties.setProperty("password", ExecutionConfig.DR_DB_PASSWORD);
			properties.setProperty("useSSL", "false");
			properties.setProperty("autoReconnect", "true");
			// String connectionUrl = "jdbc:mysql://" + ExecutionConfig.DR_DB_IP_ADDRESS +
			// ":"
			// + ExecutionConfig.DR_DB_IP_PORT + "/" + ExecutionConfig.DR_META_DB;
			String connectionUrl = "jdbc:mysql://localhost:27000/" + ExecutionConfig.DR_META_DB;
			conn = DriverManager.getConnection(connectionUrl, properties);
			System.out.println("successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
			conn.close();
		}
	}

	public static void closeConnection() throws SQLException {
		conn.close();
	}

	public static List<Map<String, Object>> executeQuery(String query) throws SQLException, ParseException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		MapListHandler mapListHandler = new MapListHandler();
		List<Map<String, Object>> result = mapListHandler.handle(rs);
		return result;
	}
	
	public static int executeUpdate(String query) throws SQLException, ParseException {
		Statement stmt = conn.createStatement();
		int result = stmt.executeUpdate(query);
		return result;
	}
	
	public static void deleteQuery(String query) throws SQLException
	{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

	public static boolean uploadDevices() throws Exception {
		try {
			csvDeviceDetails = new LinkedList<DeviceDetails>();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsss");
//			Date date = new Date();
//			String curDate = formatter.format(date);
//			System.out.println("Current date is " + curDate);
			devicedata = CsvUtil.getInstance()
					.getCsvData(new File(ExecutionConfig.getTestDataPath() + "/" + "deviceRegister.csv"));
//			int i = 0;
//			for (Map<String, String> device : devicedata) {
//				device.put("device_id", String.valueOf(Long.parseLong(curDate) + i));
//				i++;
//			}
//			CsvUtil.getInstance().updateCSVData(
//					new File(ExecutionConfig.getTestDataPath() + "/" + "deviceRegister.csv"), devicedata);

			for (Map<String, String> device : devicedata) {
				DeviceDetails d = new DeviceDetails();
				d.setDeviceId(Long.parseLong(device.get("device_id").toString()));
				d.setDeviceName(device.get("device_name").toString());
				d.setDeviceType(device.get("device_type").toString());
				d.setStoreId(Integer.parseInt(device.get("store_id").toString()));
				d.setOrgId(Integer.parseInt(device.get("org_id").toString()));
				d.setTillId(Integer.parseInt(device.get("till_id").toString()));
				d.setIs_active(Integer.parseInt(device.get("is_active").toString()));
				csvDeviceDetails.add(d);
			}

//			driver = DriverFactory.getDriver();
//			driver.get(ExecutionConfig.INTOUCH_BASE_URL);
//			IntouchLoginPage loginPage = new IntouchLoginPage("Intouch Login Page");
//			loginPage.login("ashish", "123");
//			drv = new DeviceRegistrationValidation();
//			IntouchHomePage homepage = new IntouchHomePage();
//			homepage.waitUntilAllAJAXCallsFinish();
//			homepage.getHeader().orgSelection().click();
//			homepage.getHeader().orgtextbox().waitUntilEditable();
//			homepage.getHeader().orgtextbox().sendKeys("PYTHON_AUTO");
//			homepage.getHeader().orgName("PYTHON_AUTO").waitUntilVisible();
//			homepage.getHeader().orgName("PYTHON_AUTO").click();
//			homepage.waitUntilAllAJAXCallsFinish();
//			homepage.getHeader().settings().waitUntilClickable();
//			homepage.getHeader().settings().click();
//			homepage.waitUntilAllAJAXCallsFinish();
//			homepage.getSideMenu().master_data_management().waitUntilClickable();
//			homepage.getSideMenu().master_data_management().click();
//			homepage.getSideMenu().Data_ImportButton().waitUntilClickable();
//			homepage.getSideMenu().Data_ImportButton().click();
//			homepage.getSideMenu().dataImportLink().waitUntilClickable();
//			homepage.getSideMenu().dataImportLink().click();
//			homepage.waitUntilAllAJAXCallsFinish();
//			DataImportPage dataimport = new DataImportPage();
//			dataimport.uploadDataCSV().waitUntilEditable();
//			dataimport.uploadFile().sendKeys(ExecutionConfig.TEST_DATA_PATH + "/deviceRegister.csv");
//			dataimport.submitButton().click();
//			dataimport.waitUntilAllAJAXCallsFinish();
//			dataimport.chooseProfile().waitUntilEditable();
//			dataimport.chooseProfile().click();
//			dataimport.chooseInstoreAiDevicesProfile().click();
//			dataimport.chooseTemplate().waitUntilClickable();
//			dataimport.chooseTemplate().click();
//			dataimport.chooseTemplate().selectByVisibleText("Add_Device");
//			dataimport.setupImportSubmitButton().waitUntilClickable();
//			dataimport.setupImportSubmitButton().click();
//			dataimport.waitUntilAllAJAXCallsFinish();
//			dataimport.submitMapping().scrollToView();
//			dataimport.submitMapping().click();
//			dataimport.waitUntilAllAJAXCallsFinish();
//			dataimport.importToTemporaryDB().scrollToView();
//			dataimport.processData("Temp DB").isChecked();
//			assertEquals(devicedata.size(), Integer.parseInt(dataimport.noOfRecords().getText()));
//			dataimport.importToDBSubmit().isEnabled();
//			dataimport.importToDBSubmit().click();
//			dataimport.importToMainDB().scrollToView();
//			dataimport.importToDatabase().isEnabled();
//			dataimport.importToDatabase().click();
//			dataimport.importToDBSubmit().isEnabled();
//			dataimport.importToDBSubmit().click();
//			dataimport.waitUntilAllAJAXCallsFinish();
//			dataimport.importMessage().waitUntilVisible();
//			assertEquals("No of records imported successfully : " + devicedata.size(),
//					dataimport.importMessage().getText());
			String deviceId = "201907171834015,201907171834016,201907171834017,201907171834018,201907171834019,201907171834020,201907171834021,201907171834022";
//			driver.quit();
			for (Map<String, String> device : devicedata) {
				deviceId = deviceId + device.get("device_id").toString() + ",";
			}
			deviceId = deviceId.substring(0, deviceId.length() - 1);
			System.out.println("device Ids are " + deviceId);
			String query = "Select device_id,device_type,wifi_mac_id,lan_mac_id,till_id,store_id,org_id,device_name,processor,lens,with_staff_switch,case_version,case_color,is_active,enable_notifications,enable_demographics_capture,notes from `instore_ai_devices`.`device_registration_meta` WHERE device_id IN("
					+ deviceId + ") and is_active = 1 order by device_id asc";
//			 drv.createConnection();
			List<Map<String, Object>> dbdd = DeviceRegistrationValidation.executeQuery(query);
			DeviceRegistrationValidation.dbDeviceDetails = new LinkedList<DeviceDetails>();
			DeviceRegistrationValidation.dbDeviceDetails = DeviceRegistrationValidation.getDeviceDetails(dbdd);
//			drv.compareDBCSVdevices(dbDeviceDetails, csvDeviceDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
//			driver.quit();
			return false;
		}
	}

	public static void validateDeviceDetails(JSONObject resDevice, String query) {
		List<Map<String, Object>> metadbDevice;
		try {
			metadbDevice = DeviceRegistrationValidation.executeQuery(query);
			System.out.println("The db details are" + metadbDevice.get(0).toString());
			assertEquals(1, metadbDevice.size());
			assertEquals(resDevice.get("deviceId").toString(), metadbDevice.get(0).get("device_id").toString());
			assertEquals(resDevice.get("deviceType").toString(), metadbDevice.get(0).get("device_type").toString());
			assertEquals(resDevice.get("deviceName").toString(), metadbDevice.get(0).get("device_name").toString());
			assertEquals(resDevice.get("tillId").toString(), metadbDevice.get(0).get("till_id").toString());
			assertEquals(resDevice.get("storeId").toString(), metadbDevice.get(0).get("store_id").toString());
			assertEquals(resDevice.get("orgId").toString(), metadbDevice.get(0).get("org_id").toString());
			assertEquals(resDevice.get("active").toString(), metadbDevice.get(0).get("is_active").toString());
			assertEquals(resDevice.get("wifiMacId").toString(), metadbDevice.get(0).get("wifi_mac_id").toString());
			assertEquals(resDevice.get("lanMacId").toString(), metadbDevice.get(0).get("lan_mac_id").toString());
			assertEquals(resDevice.get("notes").toString(), metadbDevice.get(0).get("notes").toString());

			for (DeviceDetails dd : DeviceRegistrationValidation.dbDeviceDetails) {
				if (dd.getDeviceId() == Long.parseLong(resDevice.get("deviceId").toString())) {
					dd.setIs_active(Integer.parseInt(resDevice.get("isActive").toString()));
					dd.setOrgId(Integer.parseInt(resDevice.get("orgId").toString()));
					dd.setStoreId(Integer.parseInt(resDevice.get("storeId").toString()));
					dd.setTillId(Integer.parseInt(resDevice.get("tillId").toString()));
				}
			}
			System.out.println(DeviceRegistrationValidation.dbDeviceDetails);
			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void compareDBCSVdevices(List<DeviceDetails> dbDd, List<DeviceDetails> dd) {
		assertEquals(dd.size(), dbDd.size());
		for (int i = 0; i < dbDd.size(); i++) {
			assertEquals(dbDd.get(i).getDeviceId(), dd.get(i).getDeviceId());
			assertEquals(dbDd.get(i).getDeviceName(), dd.get(i).getDeviceName());
			assertEquals(dbDd.get(i).getStoreId(), dd.get(i).getStoreId());
			assertEquals(dbDd.get(i).getIs_active(), dd.get(i).getIs_active());
			// assertEquals(dbDd.get(i).getOrgId(), dd.get(i).getOrgId());
			assertEquals(dbDd.get(i).getTillId(), dd.get(i).getTillId());
			assertEquals(dbDd.get(i).getNotes(), dd.get(i).getNotes());
		}
	}

	public static LinkedList<DeviceDetails> getDeviceDetails(List<Map<String, Object>> DeviceDetails) {
		LinkedList<DeviceDetails> deviceDetailList = new LinkedList<DeviceDetails>();
		for (Map<String, Object> dbdevice : DeviceDetails) {
			DeviceDetails d = new DeviceDetails();
			d.setDeviceId(Long.parseLong(dbdevice.get("device_id").toString()));
			d.setDeviceName(dbdevice.get("device_name").toString());
			d.setDeviceType(dbdevice.get("device_type").toString());
			d.setStoreId(Integer.parseInt(dbdevice.get("store_id").toString()));
			d.setOrgId(Integer.parseInt(dbdevice.get("org_id").toString()));
			d.setTillId(Integer.parseInt(dbdevice.get("till_id").toString()));
			d.setIs_active(Integer.parseInt(dbdevice.get("is_active").toString()));
			deviceDetailList.add(d);
		}
		return deviceDetailList;
	}
	
	public static LinkedList<DeviceDetails> getDeviceDetail(ArrayList<LinkedHashMap<String, String>> devicedata2) {
		LinkedList<DeviceDetails> deviceDetailList = new LinkedList<DeviceDetails>();
		for (Map<String, String> dbdevice : devicedata2) {
			DeviceDetails d = new DeviceDetails();
			d.setDeviceId(Long.parseLong(dbdevice.get("device_id").toString()));
			d.setDeviceName(dbdevice.get("device_name").toString());
			d.setDeviceType(dbdevice.get("device_type").toString());
			d.setStoreId(Integer.parseInt(dbdevice.get("store_id").toString()));
			d.setOrgId(Integer.parseInt(dbdevice.get("org_id").toString()));
			d.setTillId(Integer.parseInt(dbdevice.get("till_id").toString()));
			d.setIs_active(Integer.parseInt(dbdevice.get("is_active").toString()));
			deviceDetailList.add(d);
		}
		return deviceDetailList;
	}
}
