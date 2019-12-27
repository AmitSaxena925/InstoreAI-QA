package com.capillary.VisitorMatrix.api.test.ffcrDevice;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.rabbitmq.tools.json.JSONUtil;

import io.restassured.response.Response;

public class DeviceApisValidation {

	public static void checkTillDetails(Response res, TillDetails tillDetails) throws ParseException {
		assertEquals(200, res.getStatusCode());
		JSONObject tillJson = (JSONObject) (JsonUtil.StringtoJson(res.getBody().asString())).get("response");
		assertEquals(tillDetails.tillName, tillJson.get("tillName").toString());
		assertEquals(tillDetails.timeZone, tillJson.get("timeZone").toString());
		assertEquals(tillDetails.cluster, tillJson.get("clusterName").toString());
		if (tillDetails.ce == false) {
			assertEquals(true, tillJson.get("configuredWithCE").toString().equals("FFCOnly"));
		}
		if (tillDetails.mqtt == true) {
			assertEquals(true, tillJson.get("mqttStatus").toString().equals("MQTTEnabled"));
		}
//		if (tillDetails.mqtt == false) {
//			assertEquals(true, tillJson.get("mqttStatus").toString().equals("MQTTEnabled"));
//		}
//		if (tillDetails.ce == true)
//			{
//				assertEquals(false,tillJson.get("configuredWithCE").toString().equals("FFCOnly"));
//			}
//
	}

	public static void loadTillDetails(JSONObject tillJson, TillDetails tillDetails) {
		tillDetails.first_name = tillJson.get("first_name").toString();
		tillDetails.loginUrl = tillJson.get("loginUrl").toString();
		tillDetails.org_id = tillJson.get("org_id").toString();
		tillDetails.org_name = tillJson.get("org_name").toString();
		tillDetails.storeCareUrl = tillJson.get("storeCareUrl").toString();
		tillDetails.updateUrl = tillJson.get("updateUrl").toString();
		tillDetails.till_id = tillJson.get("till_id").toString();
		tillDetails.handshakeStatus = tillJson.get("handshakeStatus").toString();
		tillDetails.first_name = tillJson.get("first_name").toString();
		tillDetails.store_id = tillJson.get("store_id").toString();
		tillDetails.mqttStatus = tillJson.get("mqttStatus").toString();
	}

	public static void loadTillDetail(JSONObject tillJson, TillDetails tillDetails) {
		tillDetails.tillName = tillJson.get("tillname").toString();
		tillDetails.password = tillJson.get("password").toString();
		tillDetails.timeZone = tillJson.get("timeZone").toString();
		tillDetails.ce = Boolean.parseBoolean(tillJson.get("ce").toString());
		tillDetails.mqtt = Boolean.parseBoolean(tillJson.get("mqtt").toString());
		tillDetails.cluster = tillJson.get("cluster").toString();
	}

	public static void detachTillvalidate(Response response) {

		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(response.getBody().jsonPath().get("status.message").toString(),
				"FFC.py stopped,FFC Device detached successfully from capillary");
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()), true);
	}

	public static void addconfigvalidate(Response response) {

		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(response.getBody().jsonPath().get("status.message").toString(), "Config Saved");
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()), true);
	}

	public static void FFCStatusafterStart(Response response) {

		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()), true);
		assertEquals(response.getBody().jsonPath().get("status.message").toString(), "FFC is running and counting");
	}

	public static void FFCStatusafterStop(Response response) {

		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()), true);
		assertEquals(response.getBody().jsonPath().get("status.message").toString(), "FFC is Not running");
	}

	public static void FFCStopValidate(Response response) {

		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(true, Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()));
		assertEquals("Device Stop triggered Successfully",
				response.getBody().jsonPath().get("status.message").toString());
	}

	public static void configVaidate(Response response, TillDetails tilldetails) throws ParseException {
		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		JSONObject configJson = (JSONObject) ((JSONObject) (JsonUtil.StringtoJson(response.getBody().asString())))
				.get("response");
		assertEquals(configJson, tilldetails.config);
	}
	
	public static void validateSetRTC(Response response)
	{
		assertEquals(200, response.getStatusCode());
		assertEquals(true, Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()));
		assertEquals("TimeSet Successfully.", response.getBody().jsonPath().get("status.message").toString());
	}
	
	public static void validateGetRTC(Response response) throws ParseException
	{
		System.out.println("Response is " +response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Integer.parseInt(response.getBody().jsonPath().get("status.code").toString()), 200);
		assertEquals(true, Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()));
		System.out.println(response.getBody().jsonPath().get("status.message").toString());
		String time = ((JSONObject)JsonUtil.StringtoJson(response.getBody().jsonPath().get("status.message").toString())).get("time").toString();
		System.out.println("The RTC time is "+ time);
	}
	public static void heartbeatValidate(Response response,String ffcStatus)
	{
		System.out.print("Response is " + response.getBody().asString());
		assertEquals(response.getStatusCode(), 200);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.awsCertificates").toString()), true);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.capVerified").toString()), true);
		assertEquals(response.getBody().jsonPath().get("response.cpuUsage").toString(), "0.0");
		assertEquals(response.getBody().jsonPath().get("response.deviceId").toString(), ExecutionConfig.FFCRDEVCIEID.toString());
		assertEquals(response.getBody().jsonPath().get("response.ffcStatus").toString(), ffcStatus);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.hub.demographyUrl").toString()), true);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.hub.loginUrl").toString()), true);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.hub.storeCareUrl").toString()), true);
		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("response.hub.updateUrl").toString()), true);
		assertEquals(response.getBody().jsonPath().get("response.internet").toString(), "Connected");
	}
}
