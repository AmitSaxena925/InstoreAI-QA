package com.capillary.VisitorMatrix.api.test.deviceRegistration;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.capillary.VisitorMatrix.qa.framework.Requests.Request;
import com.capillary.VisitorMatrix.qa.framework.annotation.DataProviderParams;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.qa.framework.util.DataProviderUtil;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.ClusterInfo;
import com.capillary.VisitorMatrix.ui.test.testScripts.DeviceDetails;
import com.rabbitmq.tools.json.JSONUtil;

import io.restassured.response.Response;

public class StoreSenseHubAPI {
	public static ArrayList<LinkedHashMap<String, String>> devicedata;
	// public static final DB = ExecutionConfig.DR_META_DB + "`.`"+
	// ExecutionConfig.DR_META_DB_TABLE;

	@BeforeTest
	public void beforeTest() throws Exception {
		DeviceRegistrationValidation.createConnection();
		devicedata = CsvUtil.getInstance()
				.getCsvData(new File(ExecutionConfig.getTestDataPath() + "/deviceRegister.csv"));
		for (HashMap<String, String> d : devicedata) {
			System.out.println(d.toString());
		}
		DeviceRegistrationValidation.dbDeviceDetails = DeviceRegistrationValidation
				.getDeviceDetail(StoreSenseHubAPI.devicedata);

		for (DeviceDetails d : DeviceRegistrationValidation.dbDeviceDetails) {
			System.out.println(d.toString());
		}
		boolean deviceUploadStatus = DeviceRegistrationValidation.uploadDevices();
		if (deviceUploadStatus == false) {
			System.out.println("DeviceRegistrationAPITest.beforeTest() is failed");
		}
		for (int i = 0; i < DeviceRegistrationValidation.dbDeviceDetails.size(); i++) {
			System.out.println(DeviceRegistrationValidation.dbDeviceDetails.get(i).toString());
		}
//		String insertQuery = "INSERT INTO device_registration_meta(device_id,device_type,wifi_mac_id,lan_mac_id,till_id,store_id,org_id,device_name,processor,lens,with_staff_switch,case_version,case_color,is_active,enable_notifications,enable_demographics_capture,date_installed,date_removed,auto_update_time,notes) VALUES\n" + 
//				" (201907171834015,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834016,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834017,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834018,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834019,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834020,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834021,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834022,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')";
//		DeviceRegistrationValidation.executeUpdate(insertQuery);
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration", "tableName=Add_device" })
	@Test(priority = 1, enabled = true, description = "adding device to store", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addDevice(String DeviceIdRow, String userName, String password, String org_id, String store_id,
			String till_id) throws NumberFormatException, Exception {
		System.out.println(String.valueOf(
				DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(DeviceIdRow)).getDeviceId()));
		String deviceId = String
				.valueOf(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(DeviceIdRow)).getDeviceId());
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("deviceType",
				DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(DeviceIdRow)).getDeviceType());
		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("deviceId", deviceId);
		Response response = Request.postwithpathqueryparametrs(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_ADD_DEVICE_URL, userName, password, pathParams,
				queryParams);
		System.out.println("The status Code is " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		JSONObject resObj = JsonUtil.StringtoJson(response.getBody().asString());
		System.out.println("The cluster is " + ClusterInfo.getInstance().getCluster());

		// Validate request data with response data
		assertEquals(deviceId, ((JSONObject) resObj.get("entity")).get("deviceId").toString());
		assertEquals(till_id, ((JSONObject) resObj.get("entity")).get("tillId").toString());
		assertEquals(org_id, ((JSONObject) resObj.get("entity")).get("orgId").toString());
		assertEquals(store_id, ((JSONObject) resObj.get("entity")).get("storeId").toString());
		assertEquals(1, Integer.parseInt(((JSONObject) resObj.get("entity")).get("isActive").toString()));
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(DeviceIdRow)).getDeviceType(),
				((JSONObject) resObj.get("entity")).get("deviceType").toString());
		assertEquals(true, resObj.get("success"));

		// Validate response data with device_registration_meta db.
		try {
			String QUERY = "SELECT device_id,device_type,wifi_mac_id,lan_mac_id,till_id,store_id,org_id,device_name,processor,lens,with_staff_switch,case_version,case_color,is_active,enable_notifications,enable_demographics_capture,notes FROM `"
					+ ExecutionConfig.DR_META_DB + "`.`" + ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = "
					+ Long.parseLong(deviceId) + " AND org_id = " + Integer.parseInt(org_id) + " AND is_active = 1";
			JSONObject resdevice = (JSONObject) resObj.get("entity");
			DeviceRegistrationValidation.validateDeviceDetails(resdevice, QUERY);
		} catch (NoClassDefFoundError e) {
			System.out.println(e.getMessage());
		}
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration", "tableName=Add_device" })
	@Test(enabled = false, description = "adding device to store", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addDeviceWithoutDeviceType(String DeviceIdRow, String userName, String password, String org_id,
			String store_id, String till_id) throws NumberFormatException, Exception {
		String deviceId = String
				.valueOf(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(DeviceIdRow)).getDeviceId());
		Map<String, String> queryParams = new HashMap<String, String>();
		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("deviceId", deviceId);
		Response response = Request.postwithpathqueryparametrs(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_ADD_DEVICE_URL, userName, password, pathParams,
				queryParams);
		System.out.println("The status Code is " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		JSONObject resObj = JsonUtil.StringtoJson(response.getBody().asString());
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("message"),
				"Insufficient parameters passed");
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("status"), false);
		assertEquals(resObj.get("success"), false);
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration", "tableName=assignedTill" })
	@Test(enabled = false, description = "adding device to till where same deviceType is already assigned to till", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void adddeviceToAssignedTill(String deviceIdRow, String userName, String password)
			throws NoSuchAlgorithmException, ParseException {
		String deviceId = String
				.valueOf(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getDeviceId());
		Parameters.getParameterObj().setpathparams("deviceId", deviceId);
		Parameters.getParameterObj().setqueryParams("deviceType",
				DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getDeviceType());
		System.out.println(Parameters.getParameterObj().toString());
		Response response = Request.postwithpathqueryparametrs(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_ADD_DEVICE_URL, userName, password,
				Parameters.getParameterObj().pathParams, Parameters.getParameterObj().queryParams);
		System.out.println("The status Code is " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		JSONObject resObj = JsonUtil.StringtoJson(response.getBody().asString());
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("message"),
				"Till already active for another device of same device type");
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("status"), false);
		assertEquals(resObj.get("success"), false);
		// Parameters.getParameterObj().
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration",
			"tableName=InvalidDevice" })
	@Test(enabled = false, description = "adding device to store", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addUnknownDevice(String deviceId, String deviceType, String userName, String password)
			throws NoSuchAlgorithmException, ParseException {
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("deviceType", deviceType);
		Map<String, String> pathParams = new HashMap<String, String>();
		pathParams.put("deviceId", deviceId);
		Response response = Request.postwithpathqueryparametrs(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_ADD_DEVICE_URL, userName, password, pathParams,
				queryParams);
		System.out.println("The status Code is " + response.getStatusCode());
		assertEquals(200, response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		JSONObject resObj = JsonUtil.StringtoJson(response.getBody().asString());
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("message"),
				"Device ID passed is invalid");
		assertEquals(((JSONObject) ((JSONArray) resObj.get("errors")).get(0)).get("status"), false);
		assertEquals(resObj.get("success"), false);
		System.out.println("The cluster is " + ClusterInfo.getInstance().getCluster());
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration",
			"tableName=detachDevicebyTill" })
	@Test(priority = 2, enabled = true, description = "detach the device using TillId and deviceType", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void detachDeviceFromTill(String deviceIdRow, String userName, String Password, String org_id,
			String store_id, String till_id)
			throws NoSuchAlgorithmException, ParseException, SQLException, java.text.ParseException {
		String deviceType = DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow))
				.getDeviceType();
		System.out.println("The deviceType is " + deviceType);
		Parameters.getParameterObj().setpathparams("tillId", till_id);
		Parameters.getParameterObj().setqueryParams("deviceType", deviceType);
		Response detachResponse = Request.deletewithpathqueryparameters(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_DETACH_DEVICE_BYTILL_URL, userName, Password,
				Parameters.getParameterObj().pathParams, Parameters.getParameterObj().queryParams);
		assertEquals(200, detachResponse.getStatusCode());
		System.out.println("the response is " + detachResponse.getBody().asString());
		JSONObject resObject = new JSONObject();
		resObject = JsonUtil.StringtoJson(detachResponse.getBody().asString());
		assertEquals(0, Integer.parseInt(((JSONObject) resObject.get("entity")).get("isActive").toString()));
		String deviceId = ((JSONObject) resObject.get("entity")).get("deviceId").toString();
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setOrgId(0);
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setStoreId(0);
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setTillId(0);
		String query = "Select till_id,store_id,org_id FROM `" + ExecutionConfig.DR_META_DB + "`.`"
				+ ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = " + Long.parseLong(deviceId)
				+ " AND is_active = 1";
		List<Map<String, Object>> detachDBDevice = DeviceRegistrationValidation.executeQuery(query);
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getOrgId(),
				Integer.parseInt(detachDBDevice.get(0).get("org_id").toString()));
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getStoreId(),
				Integer.parseInt(detachDBDevice.get(0).get("store_id").toString()));
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getTillId(),
				Integer.parseInt(detachDBDevice.get(0).get("till_id").toString()));
		query = "SELECT count(*) AS count FROM `" + ExecutionConfig.DR_META_DB + "`.`"
				+ ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = " + Long.parseLong(deviceId)
				+ " AND is_active = 1 ";
		List<Map<String, Object>> detachDBDevice1 = DeviceRegistrationValidation.executeQuery(query);
		assertEquals(1, Integer.parseInt(detachDBDevice1.get(0).get("count").toString()));
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration",
			"tableName=detachDevicebyDeviceId" })
	@Test(priority = 2, enabled = false, description = "detach the device using deviceId and deviceType", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void detachDeviceFromDeviceId(String deviceIdRow, String userName, String password, String org_id,
			String store_id, String till_id)
			throws NoSuchAlgorithmException, ParseException, SQLException, java.text.ParseException {
		String deviceType = DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow))
				.getDeviceType();
		String deviceId = String
				.valueOf(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getDeviceId());
		Parameters.getParameterObj().deleteparams();
		Parameters.getParameterObj().setpathparams("deviceId", deviceId);
		Parameters.getParameterObj().setqueryParams("deviceType", deviceType);
		System.out.println("The deviceType is " + deviceType);
		Response detachResponse = Request.deletewithpathqueryparameters(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_DETACH_DEVICE_BYDEVICEID_URL, userName, password,
				Parameters.getParameterObj().pathParams, Parameters.getParameterObj().queryParams);
		System.out.println("The status code is " + detachResponse.getBody().asString());
		System.out.println("The response is " + detachResponse.getBody().asString());
		JSONObject resObject = new JSONObject();
		resObject = JsonUtil.StringtoJson(detachResponse.getBody().asString());
		assertEquals(0, Integer.parseInt(((JSONObject) resObject.get("entity")).get("isActive").toString()));
		String resDeviceId = ((JSONObject) resObject.get("entity")).get("deviceId").toString();
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setOrgId(0);
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setStoreId(0);
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setTillId(0);
		String query = "Select till_id,store_id,org_id FROM `" + ExecutionConfig.DR_META_DB + "`.`"
				+ ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = " + Long.parseLong(resDeviceId)
				+ " AND is_active = 1";
		List<Map<String, Object>> detachDBDevice = DeviceRegistrationValidation.executeQuery(query);
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getOrgId(),
				Integer.parseInt(detachDBDevice.get(0).get("org_id").toString()));
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getStoreId(),
				Integer.parseInt(detachDBDevice.get(0).get("store_id").toString()));
		assertEquals(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getTillId(),
				Integer.parseInt(detachDBDevice.get(0).get("till_id").toString()));
		query = "SELECT count(*) AS count FROM `" + ExecutionConfig.DR_META_DB + "`.`"
				+ ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = " + Long.parseLong(resDeviceId)
				+ " AND is_active = 1 ";
		List<Map<String, Object>> detachDBDevice1 = DeviceRegistrationValidation.executeQuery(query);
		assertEquals(1, Integer.parseInt(detachDBDevice1.get(0).get("count").toString()));
	}

	@DataProviderParams({ "fileName=DeviceRegistration.xls", "sheetName=deviceRegistration","tableName=discardDeviceByDeviceId" })
	@Test(priority = 2, enabled = true, description = "detach the device using deviceId and deviceType", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void discardDevice(String deviceIdRow, String userName, String password, String org_id, String store_id,
			String till_id) throws NoSuchAlgorithmException, ParseException, SQLException, java.text.ParseException {
		Parameters.getParameterObj().deleteparams();
		String deviceId = String
				.valueOf(DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).getDeviceId());
		Parameters.getParameterObj().setpathparams("deviceId", deviceId);
		Response discardDevice = Request.deletewithPathParameters(
				ExecutionConfig.DR_BASE_URL + ExecutionConfig.DR_DISCARD_DEVICE_URL, userName, password,
				Parameters.getParameterObj().pathParams);
		System.out.println("The status code is " + discardDevice.getStatusCode());
		System.out.println("The response is " + discardDevice.getBody().asString());
		JSONObject resObject = new JSONObject();
		resObject = JsonUtil.StringtoJson(discardDevice.getBody().asString());
		assertEquals(0, Integer.parseInt(((JSONObject) resObject.get("entity")).get("isActive").toString()));
		assertEquals(true, Boolean.parseBoolean(resObject.get("success").toString()));
		String query = "SELECT count(*) AS count FROM `" + ExecutionConfig.DR_META_DB + "`.`"
				+ ExecutionConfig.DR_META_DB_TABLE + "` WHERE device_id = " + deviceId + " AND is_active = 1";
		List<Map<String, Object>> discardDbDevice = DeviceRegistrationValidation.executeQuery(query);
		assertEquals(0, Integer.parseInt(discardDbDevice.get(0).get("count").toString()));
		DeviceRegistrationValidation.dbDeviceDetails.get(Integer.parseInt(deviceIdRow)).setIs_active(0);		
	}
	
	
	@AfterTest
	public void tearDown() throws SQLException, java.text.ParseException {
		String query = "DELETE FROM `instore_ai_devices`.`device_registration_meta` where device_id IN (201907171834015,201907171834016,201907171834017,201907171834018,201907171834019,201907171834020,201907171834021,201907171834022)";
		DeviceRegistrationValidation.deleteQuery(query);
		DeviceRegistrationValidation.closeConnection();
	}
}
