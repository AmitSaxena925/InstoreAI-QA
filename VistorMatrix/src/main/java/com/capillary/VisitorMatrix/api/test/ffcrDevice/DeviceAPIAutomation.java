package com.capillary.VisitorMatrix.api.test.ffcrDevice;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.capillary.VisitorMatrix.qa.framework.Requests.Request;
import com.capillary.VisitorMatrix.qa.framework.Requests.RequestResponseLogger;
import com.capillary.VisitorMatrix.qa.framework.annotation.DataProviderParams;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.listeners.BaseClassLogger;
import com.capillary.VisitorMatrix.qa.framework.listeners.BasicExtentReport;
import com.capillary.VisitorMatrix.qa.framework.listeners.ExtentManager;
import com.capillary.VisitorMatrix.qa.framework.listeners.ExtentTestManager;
import com.capillary.VisitorMatrix.qa.framework.util.DataProviderUtil;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.capillary.VisitorMatrix.qa.framework.util.TimeUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class DeviceAPIAutomation extends BaseTest {
	public static String rtcTime;
	public TillDetails tillDetails;

	@BeforeTest
	public void beforeTest() {
		Parameters.getParameterObj().setheaderParams("Authorization", "Basic dXNlcjp1c2VyMTIz");
		Parameters.getParameterObj().setheaderParams("Accept", "application/json");
		tillDetails = new TillDetails();
	}

	@AfterMethod
	public void clearTest() {
		RequestResponseLogger.clearInstance();
	}

	@Test(description = "scanssid", enabled = true, priority = 1)
	public void scanSSID() {
		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
				Parameters.getParameterObj().pathParams, ExecutionConfig.FFCRBASEURL + "scanssid");
		assertEquals(200, response.getStatusCode());
		Log("The status Code is " + response.getStatusCode());
		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
		logInfo(response.getBody().asString());
	}

//	@Test(description = "Get RTC status", priority = 2)
//	public void getRTC() throws ParseException {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams, ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRGETRTC);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.validateGetRTC(response);
//	}
//
//	@Test(description = "Set RTC Time", priority = 3)
//	public void setRTC() throws NoSuchAlgorithmException, InterruptedException {
//		DeviceAPIAutomation.rtcTime = TimeUtil.getRTCCurrentTime();
//		System.out.println("RTC Time is " + rtcTime);
//		Parameters.getParameterObj().setpathparams("rtcTime", rtcTime);
//		Response response = Request.getRequestwithPathparams(ExecutionConfig.FFCRBASEURL + "rtcsettime/{rtcTime}",
//				Parameters.getParameterObj().pathParams, Parameters.getParameterObj().headers);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.validateSetRTC(response);
//		Thread.sleep(6000);
//	}
//
//	@DataProviderParams({ "fileName=FFCRDevice.xls", "sheetName=DeviceData", "tableName=UpdateTill" })
//	@Test(description = "updateTillDetails", enabled = true, priority = 4, dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
//	public void updateTilldetails(String body) throws ParseException, InterruptedException {
//
//		Parameters.getParameterObj().setheaderParams("Content-Type", "application/json");
//		Response response = Request.postRequestWithJson(Parameters.getParameterObj().headers, body,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRADDTILL);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		assertEquals(200, response.getStatusCode());
//		JSONObject obj = JsonUtil.StringtoJson(body);
//		DeviceApisValidation.loadTillDetail(obj, tillDetails);
//		Thread.sleep(300000);
//	}
//
//	@Test(description = "Get till details", enabled = true, priority = 5)
//	public void getTillDetails() throws ParseException {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams, ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRGETTILL);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.checkTillDetails(response, tillDetails);
//	}
//
//	// heartbeatstatus
//	@DataProviderParams({ "fileName=FFCRDevice.xls", "sheetName=DeviceData", "tableName=GetHeartBeatStatus" })
//	@Test(description = "Get heartBeatStatus", enabled = true, priority = 6, dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
//	public void getheartbeat(String ffcStatus) {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRHEARTBEATSTATUS);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.heartbeatValidate(response, ffcStatus);
//	}
//
//	@DataProviderParams({ "fileName=FFCRDevice.xls", "sheetName=DeviceData", "tableName=SetConfig" })
//	@Test(description = "SetConfig", enabled = true, priority = 7, dataProviderClass = DataProviderUtil.class, dataProvider = "ExcelDataProvider")
//	public void setConfig(String configData) throws ParseException {
//		Response response = Request.postRequestWithJson(Parameters.getParameterObj().headers, configData,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRSETCONFIG);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.addconfigvalidate(response);
//		tillDetails.config = (JSONObject) JsonUtil.StringtoJson(configData);
//
//	}
//
//	@Test(description = "getConfig", enabled = true, priority = 8)
//	public void getConfig() throws ParseException, InterruptedException {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams, ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRGETCONFIG);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.configVaidate(response, tillDetails);
//		Thread.sleep(240000);
//	}
//
//	@Test(description = "FFC Start", enabled = true, priority = 9)
//	public void FFCStart() throws InterruptedException {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRSERVICESTART);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		assertEquals(response.getStatusCode(), 200);
//		System.out.print("Response is " + response.getBody().asString());
//		assertEquals(Boolean.parseBoolean(response.getBody().jsonPath().get("status.success").toString()), true);
//		assertEquals(response.getBody().jsonPath().get("status.message").toString(),
//				"Device Start triggered Successfully");
//		Thread.sleep(60000);
//	}
//
//	@Test(description = "FFC Status", enabled = true, priority = 10)
//	public void FFCStatus() {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRSERVICESTATUS);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.FFCStatusafterStart(response);
//	}
//
//	@Test(description = "FFC Stop", enabled = true, priority = 11)
//	public void FFCStop() throws InterruptedException {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRSERVICESTOP);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.FFCStopValidate(response);
//		Thread.sleep(60000);
//	}
//
//	@Test(description = "FFC Status", enabled = true, priority = 12)
//	public void FFCStatusafterStop() {
//		Response response = Request.getRequestWithparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams,
//				ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRSERVICESTATUS);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.FFCStatusafterStop(response);
//	}
//
//	@Test(description = "Detach Till", enabled = true, priority = 13)
//	public void detachTill() {
//		Parameters.getParameterObj().setqueryParams("tillName", tillDetails.tillName);
//		Parameters.getParameterObj().setqueryParams("password", tillDetails.password);
//		Response response = Request.getRequestWithqueryparams(Parameters.getParameterObj().headers,
//				Parameters.getParameterObj().queryParams, ExecutionConfig.FFCRBASEURL + ExecutionConfig.FFCRDETACHTILL);
//		Log("The status Code is " + response.getStatusCode());
//		logInfo(RequestResponseLogger.getInstance().requestWriter.toString());
//		logInfo(response.getBody().asString());
//		DeviceApisValidation.detachTillvalidate(response);
//		Parameters.getParameterObj().deleteparams();
//		tillDetails = null;
//	}
}
