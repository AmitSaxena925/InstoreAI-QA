package com.capillary.VisitorMatrix.api.test.heatMap;

import com.mongodb.BasicDBObject;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import com.capillary.VisitorMatrix.qa.framework.Requests.Request;
import com.capillary.VisitorMatrix.qa.framework.annotation.DataProviderParams;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.DataProviderUtil;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.capillary.VisitorMatrix.qa.framework.util.TimeUtil;

public class HeatMapAPITest {
	public static Device device;
	public static String day;
	public static Map<String, String> header;
	public static String authToken;
	public static Dbtunneling tunnel;
	public static SimpleDateFormat dformat;

	@BeforeTest
	public void setup() throws Exception {
		HeatMapAPITest.header = new HashMap<String, String>();
		Date date = new Date();
		dformat = new SimpleDateFormat("yyyyMMddHHmmss");
		day = dformat.format(date);
		System.out.println(day);
		// HeatMap.authToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6WyIxMDg3NTIiXSwib3JnSUQiOjc5NCwiZXhwIjoxNTM5ODAxODc1LCJpYXQiOjE1Mzk3MTU0NzYsImlzcyI6ImNhcGlsbGFyeXRlY2guY29tIiwiYXVkIjoiY2FwaWxsYXJ5LGludG91Y2gsYXJ5YSxyZW9uLGFwcHMiLCJzb3VyY2UiOiJXRUJBUFAifQ.aQtev7ugMaAHN1L0DqCSTGukQNiF0WNj-GOgf4bjHbY";
		HeatMapAPITest.authToken = Request.generateToken(ExecutionConfig.USERNAME_791, ExecutionConfig.PASSWORD_791,
				ExecutionConfig.AUTH_LOGIN);
		HeatMapAPITest.header.put("Authorization", "Bearer " + HeatMapAPITest.authToken);
		HeatMapAPITest.header.put("Content-Type", "application/json");
		HeatMapAPITest.header.put("x-cap-api-auth-org-id", "794");
		tunnel = Dbtunneling.getInstance();
		tunnel.createConnection();
	}

	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=Add_device" })
	@Test(enabled = true, priority = 1, description = "adding device to store", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addDevicestoStore(String userName, String password, String deviceName, String sectionName,
			String storeId, String orgId) throws JsonProcessingException, ParseException {
		device = new Device(deviceName + day, sectionName + day, Integer.parseInt(storeId), Integer.parseInt(orgId),
				Long.parseLong(day));
		ObjectMapper om = new ObjectMapper();
		String deviceObject = om.writeValueAsString(device);

		System.out.println("The request body is" + deviceObject);

		Response response = Request.postRequestWithJson(HeatMapAPITest.header, deviceObject, ExecutionConfig.HM_ADD_DEVICE);

		assertEquals(200, response.getStatusCode());
		System.out.println("The status code is " + response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		assertEquals("successful", response.getBody().jsonPath().get("response").toString());
		BasicDBObject query = new BasicDBObject();
		query.put("deviceName", deviceName + day);
		query.put("sectionName", sectionName + day);
		query.put("storeId", Integer.parseInt(storeId));
		query.put("orgId", Integer.parseInt(orgId));
		query.put("deviceId", Long.parseLong(HeatMapAPITest.day));
		JSONArray arr = HeatMapAPITest.tunnel.executequery(query, "cc-db", "devices");
		JSONObject doc = (JSONObject) arr.get(0);
		System.out.println(doc.toString());
		assertEquals(doc.get("deviceName"), deviceName + day);
		assertEquals(doc.get("sectionName"), sectionName + day);
		assertEquals(doc.get("storeId").toString(), storeId);
		assertEquals(doc.get("orgId").toString(), orgId);
		assertEquals(((long) ((double) doc.get("deviceId"))), Long.parseLong(HeatMapAPITest.day));

	}

	@SuppressWarnings("unchecked")
	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=Get_device" })
	@Test(enabled = true, priority = 2, description = "Get All the store devices", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void getAllStoreDevices(String storeId, String orgId) throws ParseException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("orgId", orgId);
		params.put("storeId", storeId);
		Response response = Request.getRequestWithparams(HeatMapAPITest.header, params, ExecutionConfig.HM_GET_DEVICES);
		assertEquals(200, response.getStatusCode());
		System.out.println("The status code is " + response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		JSONObject devices = new JSONObject();
		devices = (JSONObject) (JsonUtil.StringtoJson(response.getBody().asString())).get("response");
		String res = devices.get("devices").toString();
		JSONArray devicesArray = JsonUtil.stringtoJsonArray(res);
		BasicDBObject query = new BasicDBObject();
		query.put("storeId", Integer.parseInt(storeId));
		query.put("orgId", Integer.parseInt(orgId));
		JSONArray devDbArr = HeatMapAPITest.tunnel.executequery(query, "cc-db", "devices");
		for (int i = 0; i < devDbArr.size(); i++) {
			JSONObject obj = (JSONObject) devDbArr.get(i);
			obj.put("deviceId", (long) ((double) obj.get("deviceId")));
			obj.put("_id", ((JSONObject) obj.get("_id")).get("$oid"));
			System.out.println(obj);
		}
		System.out.println(devDbArr.toString());
		assertEquals(JsonUtil.CompareJsonArray(devicesArray.toString(), devDbArr.toString()), true);
	}

	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=update_section_name" })
	@Test(enabled = true, priority = 3, description = "Update device Section name", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void updateDeviceSectionName(String sectionName) throws ParseException, JsonProcessingException {
		day = dformat.format(new Date());
		device.setSectionName(sectionName + day);
		System.out.println(device.SectionName);
		ObjectMapper om = new ObjectMapper();
		String deviceObject = om.writeValueAsString(device);
		Response response = Request.postRequestWithJson(HeatMapAPITest.header, deviceObject, ExecutionConfig.HM_ADD_DEVICE);
		assertEquals(200, response.getStatusCode());
		System.out.println("The status code is " + response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		BasicDBObject query = new BasicDBObject();
		query.put("deviceName", device.getDeviceName());
		query.put("sectionName", device.getSectionName());
		query.put("storeId", device.getStoreId());
		query.put("orgId", device.getOrgId());
		query.put("deviceId", device.getDeviceId());
		JSONArray arr = HeatMapAPITest.tunnel.executequery(query, "cc-db", "devices");
		JSONObject doc = (JSONObject) arr.get(0);
		System.out.println(doc.toString());
		assertEquals(doc.get("deviceName"), device.getDeviceName());
		assertEquals(doc.get("sectionName"), device.getSectionName());
		assertEquals(Integer.parseInt(doc.get("storeId").toString()), device.getStoreId());
		assertEquals(Integer.parseInt(doc.get("orgId").toString()), device.getOrgId());
		assertEquals(((long) ((double) doc.get("deviceId"))), device.getDeviceId());
	}

	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=update_device_name" })
	@Test(enabled = true, priority = 4, description = "Update device name", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void updateDeviceName(String deviceName) throws ParseException, JsonProcessingException {
		day = dformat.format(new Date());
		device.setDeviceName(deviceName + day);
		System.out.println(device.SectionName);
		ObjectMapper om = new ObjectMapper();
		String deviceObject = om.writeValueAsString(device);
		Response response = Request.postRequestWithJson(HeatMapAPITest.header, deviceObject, ExecutionConfig.HM_ADD_DEVICE);
		assertEquals(200, response.getStatusCode());
		System.out.println("The status code is " + response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		BasicDBObject query = new BasicDBObject();
		query.put("deviceName", device.getDeviceName());
		query.put("sectionName", device.getSectionName());
		query.put("storeId", device.getStoreId());
		query.put("orgId", device.getOrgId());
		query.put("deviceId", device.getDeviceId());
		JSONArray arr = HeatMapAPITest.tunnel.executequery(query, "cc-db", "devices");
		JSONObject doc = (JSONObject) arr.get(0);
		System.out.println(doc.toString());
		assertEquals(doc.get("deviceName"), device.getDeviceName());
		assertEquals(doc.get("sectionName"), device.getSectionName());
		assertEquals(Integer.parseInt(doc.get("storeId").toString()), device.getStoreId());
		assertEquals(Integer.parseInt(doc.get("orgId").toString()), device.getOrgId());
		assertEquals(((long) ((double) doc.get("deviceId"))), device.getDeviceId());

	}

	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=Add_layout" })
	@Test(enabled = true, priority = 5, description = "add layout to device", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addLayoutToDevice(String layoutName)
			throws NoSuchAlgorithmException, ParseException, java.text.ParseException {
		int x = -3;
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, x);
		Date day1 = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = df.format(day1);
		System.out.println(startDate);
		HashMap<String, String> layout = new HashMap<String, String>();
		layout.put("layoutName", layoutName + HeatMapAPITest.day);
		layout.put("deviceId", String.valueOf(device.getDeviceId()));
		layout.put("storeId", String.valueOf(device.storeId));
		layout.put("orgId", String.valueOf(device.getOrgId()));
		layout.put("isCurrent", "true");
		layout.put("startDate", startDate);
		String imapgepath = ExecutionConfig.getTestDataPath()+"/image.jpeg";
		HashMap<String, File> multipart = new HashMap<String, File>();
		multipart.put("image", new File(imapgepath));
		Response response = Request.postmultipartrequestWithPathParams(ExecutionConfig.HM_ADD_LAYOUT, header, layout,
				imapgepath);

		System.out.println("The created layout is " + response.getBody().asString());
		assertEquals(200, response.getStatusCode());
		assertEquals("Successful", response.jsonPath().getString("response.message"));
		String layoutAPIDeatils = response.jsonPath().getString("response.message");
		JSONObject layoutAPIObj = (JSONObject) ((JSONObject) (((JSONObject) JsonUtil
				.StringtoJson(response.getBody().asString())).get("response"))).get("layout");
		System.out.println(layoutAPIObj);
		BasicDBObject query = new BasicDBObject();
		query.put("storeId", device.getStoreId());
		query.put("orgId", device.getOrgId());
		query.put("deviceId", device.getDeviceId());
		query.put("layoutName", layoutName + HeatMapAPITest.day);
		query.put("layoutId", layoutAPIObj.get("layoutId"));
		JSONArray layoutDbObjs = HeatMapAPITest.tunnel.executequery(query, "cc-db", "layouts");
		assertEquals(1, layoutDbObjs.size());
		JSONObject dblayout = (JSONObject) layoutDbObjs.get(0);

		// Comparision of API with request data
		assertEquals(layoutAPIObj.get("isCurrent"), true);
		assertEquals(Integer.parseInt(layoutAPIObj.get("storeId").toString()), device.getStoreId());
		assertEquals(layoutAPIObj.get("deviceId"), device.getDeviceId());
		assertEquals(Integer.parseInt(layoutAPIObj.get("orgId").toString()), device.getOrgId());
		assertEquals(layoutAPIObj.get("layoutName"), layoutName + HeatMapAPITest.day);
		String[] APIStartDate = layoutAPIObj.get("startDate").toString().split("T");
		assertEquals(APIStartDate[0], startDate);

		// comparision of API with DB data
		assertEquals(layoutAPIObj.get("image"), dblayout.get("image"));
		assertEquals(layoutAPIObj.get("isCurrent"), dblayout.get("isCurrent"));
		assertEquals(layoutAPIObj.get("storeId"), dblayout.get("storeId"));
		assertEquals(layoutAPIObj.get("deviceId"), ((long) ((double) dblayout.get("deviceId"))));
		assertEquals(layoutAPIObj.get("layoutId"), dblayout.get("layoutId"));
		assertEquals(layoutAPIObj.get("orgId"), dblayout.get("orgId"));
		assertEquals(layoutAPIObj.get("layoutName"), dblayout.get("layoutName"));
		System.out.println("The API is start Date is " + layoutAPIObj.get("startDate").toString());
		String APIstartDate = TimeUtil.convertISOtoUTC(layoutAPIObj.get("startDate").toString());
		long dbdate = (long) ((JSONObject) dblayout.get("startDate")).get("$date");
		String DBStartDate = TimeUtil.convertEpoctoUTC(dbdate);
		assertEquals(APIstartDate, DBStartDate);
		if (device.getLayouts() == null) {
			device.setLayouts(new ArrayList<Layout>());
		}
		Layout lt = new Layout();
		lt.setImage(layoutAPIObj.get("image").toString());
		lt.setStartDate(layoutAPIObj.get("startDate").toString().split("T")[0]);
		lt.setLayoutId(layoutAPIObj.get("layoutId").toString());
		lt.setIsCurrent((boolean) layoutAPIObj.get("isCurrent"));
		lt.setLayoutName(layoutAPIObj.get("layoutName").toString());
		device.getLayouts().add(lt);
		System.out.println("The list of layouts are " + device.getLayouts());
	}

	@Test(enabled = true, priority = 6, description = "Update endDate to layout name")
	public void addEndDatetoLayout() throws ParseException {
		int x = -1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, x);
		Date day1 = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String endDate = df.format(day1);
		System.out.println("The end date is " + endDate);
		HashMap<String, String> layoutInfo = new HashMap<String, String>();
		layoutInfo.put("image", device.getLayouts().get(0).getImage().toString());
		layoutInfo.put("layoutName", device.getLayouts().get(0).getLayoutName());
		layoutInfo.put("startDate", device.getLayouts().get(0).getStartDate());
		layoutInfo.put("layoutId", device.getLayouts().get(0).getLayoutId());
		layoutInfo.put("isCurrent", String.valueOf(device.getLayouts().get(0).getIsCurrent()));
		layoutInfo.put("storeId", String.valueOf(device.getStoreId()));
		layoutInfo.put("endDate", endDate);
		layoutInfo.put("orgId", String.valueOf(device.getOrgId()));
		layoutInfo.put("deviceId", String.valueOf(device.getDeviceId()));
		System.out.println("The layout info is" + layoutInfo);
		Response response = Request.postRequestwithFormParams(ExecutionConfig.HM_ADD_LAYOUT, HeatMapAPITest.header,
				layoutInfo);
		assertEquals(200, response.getStatusCode());
		System.out.println(response.getBody().asString());
		assertEquals(response.jsonPath().getString("response.message"), "Successful");
		assertEquals(response.jsonPath().getString("response.layout.nModified").toString(), "1");

		BasicDBObject query = new BasicDBObject();
		query.put("storeId", device.getStoreId());
		query.put("orgId", device.getOrgId());
		query.put("deviceId", device.getDeviceId());
		query.put("layoutName", device.getLayouts().get(0).getLayoutName());
		query.put("layoutId", device.getLayouts().get(0).getLayoutId());
		JSONArray layoutDbObjs = HeatMapAPITest.tunnel.executequery(query, "cc-db", "layouts");
		assertEquals(1, layoutDbObjs.size());
		JSONObject dblayout = (JSONObject) layoutDbObjs.get(0);
		assertEquals(device.getLayouts().get(0).getImage().toString(), dblayout.get("image"));
		assertEquals(device.getLayouts().get(0).getIsCurrent(), dblayout.get("isCurrent"));
		assertEquals(device.getStoreId(), Integer.parseInt(dblayout.get("storeId").toString()));
		assertEquals(device.getDeviceId(), ((long) ((double) dblayout.get("deviceId"))));
		assertEquals(device.getLayouts().get(0).getLayoutId(), dblayout.get("layoutId"));
		assertEquals(device.getOrgId(), Integer.parseInt(dblayout.get("orgId").toString()));
		assertEquals(device.getLayouts().get(0).getLayoutName(), dblayout.get("layoutName"));
		System.out.println("The layout start Date is " + device.getLayouts().get(0).getStartDate());
		long dbStartdate = (long) ((JSONObject) dblayout.get("startDate")).get("$date");
		long dbEnddate = (long) ((JSONObject) dblayout.get("endDate")).get("$date");
		Date d = new Date(dbStartdate);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(device.getLayouts().get(0).getStartDate(), sf.format(d).toString());
		d = new Date(dbEnddate);
		assertEquals(endDate, sf.format(d));
		device.getLayouts().get(0).setEndDate(endDate);
	}

	@SuppressWarnings("unchecked")
	@Test(enabled = true, priority = 7)
	public void getLayoutHistoryforDevice() throws ParseException, NoSuchAlgorithmException {
		Map<String, String> pathparm = new HashMap<String, String>();
		pathparm.put("deviceId", String.valueOf(device.getDeviceId()));
		Response response = Request.getRequestwithPathparams(ExecutionConfig.HM_GET_LAYOUT_HISTORY, pathparm,
				HeatMapAPITest.header);
		JSONObject res = JsonUtil.StringtoJson(response.getBody().asString());
		JSONObject obj = (JSONObject) res.get("response");
		JSONArray layoutsAPIArray = (JSONArray) obj.get("layout");
		for (int i = 0; i < layoutsAPIArray.size(); i++) {
			((JSONObject) layoutsAPIArray.get(i)).put("startDate",
					TimeUtil.convertISOtoUTC((((JSONObject) layoutsAPIArray.get(i)).get("startDate")).toString()));

			if ((((JSONObject) layoutsAPIArray.get(i)).get("endDate")) != null) {
				((JSONObject) layoutsAPIArray.get(i)).put("endDate",
						TimeUtil.convertISOtoUTC((((JSONObject) layoutsAPIArray.get(i)).get("endDate")).toString()));
			}
		}
		BasicDBObject query = new BasicDBObject();
		query.put("deviceId", device.getDeviceId());
		query.put("orgId", device.getOrgId());
		query.put("storeId", device.getStoreId());
		JSONArray layoutsDBArray = tunnel.executequery(query, "cc-db", "layouts");
		for (int i = 0; i < layoutsDBArray.size(); i++) {
			JSONObject layout = (JSONObject) layoutsDBArray.get(i);
			layout.put("deviceId", (long) ((double) layout.get("deviceId")));
			layout.put("_id", ((JSONObject) layout.get("_id")).get("$oid"));
			layout.put("startDate",
					TimeUtil.convertEpoctoUTC((long) ((JSONObject) layout.get("startDate")).get("$date")));
			if (layout.get("endDate") != null) {
				layout.put("endDate",
						TimeUtil.convertEpoctoUTC((long) ((JSONObject) layout.get("endDate")).get("$date")));
			}
		}
		assertEquals(JsonUtil.CompareJsonArray(layoutsAPIArray.toString(), layoutsDBArray.toString()), true);
	}

	@SuppressWarnings("unchecked")
	@DataProviderParams({ "fileName=heatMapData.xls", "sheetName=heatMap", "tableName=Add_zones" })
	@Test(enabled = true, priority = 8, description = "Add Zone to layout", dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
	public void addZonestoLayout(String ZoneName, String active, String grids) throws JsonProcessingException, ParseException {
		int[] gridarray = Arrays.stream(grids.split(",")).mapToInt(Integer::parseInt).toArray();
		JSONArray gridInfo =  new JSONArray();
		for(int grid: gridarray)
		{
			 gridInfo.add(grid);
		}
		for (int i =0 ;i <device.getLayouts().size(); i ++) {
			JSONObject zone = new JSONObject();
			zone.put("deviceId", device.getDeviceId());
			zone.put("zoneName", ZoneName);
			zone.put("gridInfo", gridInfo);
			zone.put("active", Boolean.parseBoolean(active));
			zone.put("layoutId", device.getLayouts().get(i).getLayoutId());
			Response response = Request.postRequestWithJson(HeatMapAPITest.header,(new ObjectMapper()).writeValueAsString(zone),ExecutionConfig.HM_ADD_ZONE);
			System.out.println("The created zone is "+ response.getBody().asString());
			JSONObject responseObject =  JsonUtil.StringtoJson(response.getBody().asString());
			JSONObject ZoneAPIObject =  new JSONObject();
			ZoneAPIObject = (JSONObject)((JSONObject)responseObject.get("response")).get("zone");
			BasicDBObject query = new BasicDBObject();
			query.put("deviceId",device.getDeviceId());
			query.put("layoutId", device.getLayouts().get(i).getLayoutId());
			query.put("zoneId", ZoneAPIObject.get("zoneId"));
			query.put("zoneName", ZoneName);
			JSONArray zonesDbArray =  tunnel.executequery(query, "cc-db", "zones");
			JSONObject ZoneDBObject = (JSONObject) zonesDbArray.get(0);
			ZoneDBObject.put("deviceId", (long) ((double) ZoneDBObject.get("deviceId")));
			ZoneDBObject.put("_id", ((JSONObject) ZoneDBObject.get("_id")).get("$oid"));
			System.out.println("The DB zone details "+ZoneDBObject);
			assertEquals(ZoneDBObject.equals(ZoneAPIObject),true);
			if (device.getLayouts().get(i).getZones() == null) {
				device.getLayouts().get(i).setZones(new ArrayList<Zone>());
			}
			device.getLayouts().get(i).getZones().add(new Zone(ZoneName,Boolean.parseBoolean(active),gridInfo));			
		}
	}

	@SuppressWarnings("unchecked")
	@Test(enabled = true, priority = 9)
	public void getZonesUsingLayoutId() throws NoSuchAlgorithmException, ParseException
	{
		List<Layout> layouts = device.getLayouts();
		for (int i = 0; i < layouts.size(); i++) {
			String layoutId = layouts.get(i).getLayoutId();
			Map<String,String> pathParams = new HashMap<String, String>();
			pathParams.put("layoutId", layoutId);
			Response response = Request.getRequestwithPathparams(ExecutionConfig.HM_GET_ZONES_LAYOUTID, pathParams, HeatMapAPITest.header);
			JSONArray ZonesAPIArray = new JSONArray();
			ZonesAPIArray = (JSONArray) ((JSONObject) ((JsonUtil.StringtoJson(response.getBody().asString())).get("response"))).get("zones");
			System.out.println("The response Zones are "+ZonesAPIArray);
			JSONArray ZonesDBArray =  new JSONArray();
			BasicDBObject query = new BasicDBObject();
			query.put("layoutId", layoutId);
			query.put("deviceId", device.getDeviceId());
			ZonesDBArray = tunnel.executequery(query,"cc-db","zones");
			for(int j = 0; j < ZonesDBArray.size();j++)
			{
				((JSONObject)ZonesDBArray.get(j)).put("_id", ((JSONObject)((JSONObject) ZonesDBArray.get(j)).get("_id")).get("$oid").toString());
				((JSONObject)ZonesDBArray.get(j)).put("deviceId", (long)((double)((JSONObject) ZonesDBArray.get(j)).get("deviceId")));
			}
			System.out.println("The DB Zones array is "+ZonesDBArray);
			System.out.println("The API zones array is "+ ZonesAPIArray);
			assertEquals(ZonesAPIArray.equals(ZonesDBArray), true);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Test(priority = 10, enabled = true)
	public void getZonesUsingDeviceId() throws NoSuchAlgorithmException, ParseException
	{
		Map<String,String> pathParams =  new HashMap<String, String>();
		pathParams.put("deviceId", String.valueOf(device.getDeviceId()));
		Response response = Request.getRequestwithPathparams(ExecutionConfig.HM_GET_ZONES_DEVICEID, pathParams, HeatMapAPITest.header);
		JSONObject responseObj =  JsonUtil.StringtoJson(response.getBody().asString());
		JSONArray zonesAPIArray = (JSONArray)((JSONObject)responseObj.get("response")).get("zones");
		BasicDBObject query = new BasicDBObject();
		query.put("deviceId", device.getDeviceId());
		JSONArray zonesDBArray = tunnel.executequery(query, "cc-db", "zones");		
		for (int i = 0; i < zonesDBArray.size(); i++) {
			((JSONObject)zonesDBArray.get(i)).put("deviceId",(long)((double)((JSONObject) zonesDBArray.get(i)).get("deviceId")));
			((JSONObject)zonesDBArray.get(i)).put("_id", ((JSONObject)((JSONObject) zonesDBArray.get(i)).get("_id")).get("$oid").toString());
		}
		assertEquals(zonesAPIArray.equals(zonesDBArray), true);
		
	}
	
	@AfterTest()
	public void close() {
		HeatMapAPITest.tunnel.closeMongoConnection();
	}
}
