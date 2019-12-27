package com.capillary.VisitorMatrix.ui.test.testScripts;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.capillary.VisitorMatrix.api.test.deviceRegistration.DeviceRegistrationValidation;
import com.capillary.VisitorMatrix.qa.framework.base.BaseTest;
import com.capillary.VisitorMatrix.qa.framework.core.DriverFactory;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.DataImportPage;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.IntouchHomePage;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.IntouchLoginPage;

public class DataImportUITest extends BaseTest {
	public static WebDriver driver = null;
	public static ArrayList<LinkedHashMap<String, String>> devicedata;
	public static DeviceRegistrationValidation drv;
	public static List<DeviceDetails> csvDeviceDetails;

	@BeforeTest
	public void beforeTest() throws IOException {
		csvDeviceDetails = new LinkedList<DeviceDetails>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsss");
		Date date = new Date();
		String curDate = formatter.format(date);
		System.out.println("Current date is " + curDate);
		InetAddress IP = InetAddress.getLocalHost();
		System.out.println(IP.toString());
		devicedata = CsvUtil.getInstance()
				.getCsvData(new File(ExecutionConfig.getTestDataPath() + "/" + "deviceRegister.csv"));
		int i = 0;
		for (Map<String, String> device : devicedata) {
			device.put("device_id", String.valueOf(Long.parseLong(curDate) + i));
			i++;
		}
		CsvUtil.getInstance().updateCSVData(new File(ExecutionConfig.getTestDataPath() + "/" + "deviceRegister.csv"),
				devicedata);

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
		driver = DriverFactory.getDriver();
		driver.get(ExecutionConfig.INTOUCH_BASE_URL);
		IntouchLoginPage loginPage = new IntouchLoginPage("Intouch Login Page");
		loginPage.login("ashish", "123");
		drv = new DeviceRegistrationValidation();
	}

	@Test(enabled=true)
	public void dataImportTest() throws Exception {
		IntouchHomePage homepage = new IntouchHomePage();
		homepage.waitUntilAllAJAXCallsFinish();
		homepage.getHeader().orgSelection().click();
		homepage.getHeader().orgtextbox().waitUntilEditable();
		homepage.getHeader().orgtextbox().sendKeys("PYTHON_AUTO");
		homepage.getHeader().orgName("PYTHON_AUTO").waitUntilVisible();
		homepage.getHeader().orgName("PYTHON_AUTO").click();
		homepage.waitUntilAllAJAXCallsFinish();
		homepage.getHeader().settings().waitUntilClickable();
		homepage.getHeader().settings().click();
		homepage.waitUntilAllAJAXCallsFinish();
		homepage.getSideMenu().master_data_management().waitUntilClickable();
		homepage.getSideMenu().master_data_management().click();
		homepage.getSideMenu().Data_ImportButton().waitUntilClickable();
		homepage.getSideMenu().Data_ImportButton().click();
		homepage.getSideMenu().dataImportLink().waitUntilClickable();
		homepage.getSideMenu().dataImportLink().click();
		homepage.waitUntilAllAJAXCallsFinish();
		DataImportPage dataimport = new DataImportPage();
		dataimport.uploadDataCSV().waitUntilEditable();
		dataimport.uploadFile().sendKeys(ExecutionConfig.TEST_DATA_PATH + "/deviceRegister.csv");
		dataimport.submitButton().click();
		dataimport.waitUntilAllAJAXCallsFinish();
		dataimport.chooseProfile().waitUntilEditable();
		dataimport.chooseProfile().click();
		dataimport.chooseInstoreAiDevicesProfile().click();
		dataimport.chooseTemplate().waitUntilClickable();
		dataimport.chooseTemplate().click();
		dataimport.chooseTemplate().selectByVisibleText("Add_Device");
		dataimport.setupImportSubmitButton().waitUntilClickable();
		dataimport.setupImportSubmitButton().click();
		dataimport.waitUntilAllAJAXCallsFinish();
		dataimport.submitMapping().scrollToView();
		dataimport.submitMapping().click();
		dataimport.waitUntilAllAJAXCallsFinish();
		dataimport.importToTemporaryDB().scrollToView();
		dataimport.processData("Temp DB").isChecked();
		assertEquals(devicedata.size(), Integer.parseInt(dataimport.noOfRecords().getText()));
		dataimport.importToDBSubmit().isEnabled();
		dataimport.importToDBSubmit().click();
		dataimport.importToMainDB().scrollToView();
		dataimport.importToDatabase().isEnabled();
		dataimport.importToDatabase().click();
		dataimport.importToDBSubmit().isEnabled();
		dataimport.importToDBSubmit().click();
		dataimport.waitUntilAllAJAXCallsFinish();
		dataimport.importMessage().waitUntilVisible();
		assertEquals("No of records imported successfully : " + devicedata.size(),
				dataimport.importMessage().getText());
		String deviceId = "";
		for (Map<String, String> device : devicedata) {
			deviceId = deviceId + device.get("device_id").toString() + ",";
		}
		deviceId = deviceId.substring(0, deviceId.length() - 1);
//		String query = "Select * from `instore_ai_devices`.`device_registration_meta` WHERE device_id IN(" + deviceId
//				+ ") order by device_id asc";
//		DeviceRegistrationValidation.createConnection();
//		List<Map<String, Object>> dbdd = DeviceRegistrationValidation.executeQuery(query);
//		LinkedList<DeviceDetails> dbDeviceDetails = drv.getDeviceDetails(dbdd);
//		drv.compareDBCSVdevices(dbDeviceDetails, csvDeviceDetails);
	}

	@AfterTest
	public void afterMethod() throws InterruptedException, SQLException {
		DriverFactory.closeDriverObject();
		drv.closeConnection();
	}
}
