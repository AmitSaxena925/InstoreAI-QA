package com.capillary.VisitorMatrix.qa.testcase;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.params.CoreConnectionPNames;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Reporter;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.EncryptionUtil;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class pushFFCData {
	public static String V1json = null;
	public static int totalWalkin = 0;
	public static String[] mobilenumbers;
	public static int[] walkins = new int[9];
	public static int storcount = 0;
	public static int storewalkin = 0;
	public static HashMap<String, List<String>> time_mobile;

	public static void push_ffc_data(String org_id, String zone_id, String store_id, String store_name, String till_id,
			String till_name, Long deviceId, int deletedays) {
		String day = "";
		Date date = new Date();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
		day = dformat.format(date);
		System.out.println(day);
		totalWalkin = 0;
		String json = "";
		storcount = 0;
		String customerJson = "";
		json = createCustomerWalkinPayload(org_id, zone_id, store_id, store_name, till_id, till_name, deviceId, day,
				deletedays);
		Response response = sendRequest(ExecutionConfig.STORECARE_V2_FFCDETAILS_URL, json, till_name);
		assertEquals(true, response.getBody().jsonPath().get("response.status.success").equals(true));
		assertEquals(200, response.getStatusCode());
		response = sendRequest(ExecutionConfig.STORECARE_FFCDETAILS_URL, V1json, till_name);
		assertEquals(true, response.getBody().jsonPath().get("response.status.success").equals(true));
		assertEquals(200, response.getStatusCode());

	}
	

	public static void push_customer_data(String till_name, int deletedays) throws ParseException {
		String customerBody = createCustomerPayload(deletedays);
		Response response = sendRequest(ExecutionConfig.ADD_CUSTOMER, customerBody, till_name);
		assertEquals(200, response.getStatusCode());
		
	}

	public static void push_transaction_data(String till_name, int deletedays) throws ParseException {
		String TransactionBody = createTransactionPayload(deletedays);
		Response response = sendRequest(ExecutionConfig.ADD_TRANSACTION, TransactionBody, till_name);
		System.out.println("The response is "+ response.getBody().asString());
	}

	@SuppressWarnings("unchecked")
	public static String createCustomerWalkinPayload(String org_id, String zone_id, String store_id, String store_name,
			String till_id, String till_name, Long deviceId, String day, int deletedays) {
		JSONObject object = new JSONObject();
		JSONObject v1obj = new JSONObject();
		try {
			Date date = new SimpleDateFormat("yyyy/MM/dd 00:00:00").parse(day);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -deletedays);
			System.out.println("The calender time is " + cal.getTime());
			Date date1 = cal.getTime();
			SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
			day = dformat.format(date1);
			long epoch = (date1.getTime()) / 1000;
			System.out.println(epoch);
			Long basetimestamp = epoch;
			System.out.println("The basetimestamp is " + basetimestamp);
			object.put("org_id", org_id);
			object.put("till_id", till_id);
			object.put("zone_id", zone_id);
			object.put("store_id", store_id);
			object.put("source", "till");
			object.put("store_server_id", null);
			object.put("store_server_name", null);
			object.put("till_name", till_name);
			object.put("store_name", store_name);
			object.put("timestamp", basetimestamp);
			object.put("deviceId", deviceId);

			v1obj.put("org_id", org_id);
			v1obj.put("till_id", till_id);
			v1obj.put("zone_id", zone_id);
			v1obj.put("store_id", store_id);
			v1obj.put("source", "till");
			v1obj.put("store_server_id", null);
			v1obj.put("store_server_name", null);
			v1obj.put("till_name", till_name);
			v1obj.put("store_name", store_name);
			v1obj.put("timestamp", basetimestamp);
			v1obj.put("deviceId", deviceId);

			JSONArray counterdetails = new JSONArray();
			JSONArray v1counterdetails = new JSONArray();
			int walkin = 0;

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = dateFormat.parse(day);
			Long time = currentDate.getTime() / 1000;
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			// System.out.println("The hour of the day is " + hour);
			// int hour = currentDate.getHours();
			int minute = cal.get(Calendar.MINUTE);
			// System.out.println("The minute of the day is " + minute);
			int second = cal.get(Calendar.SECOND);
			// System.out.println("The second of the day is " + second);
			basetimestamp = (time - (minute * 60) - second);
			basetimestamp = basetimestamp - (hour * 3600);
			// System.out.println("The final base timestamp is " + basetimestamp);

			storewalkin = 0;

			for (int i = 0; i < 24; i++) {
				JSONObject counterdetls = new JSONObject();
				if (i < 10 || i > 21) {
					counterdetls.put("outCount", 0);
					counterdetls.put("inCount", 0);
					counterdetls.put("startDate", basetimestamp);
					counterdetls.put("endDate", (basetimestamp + 3600));
					JSONObject v1counterdetls = new JSONObject();
					v1counterdetls.put("outCount", 0);
					v1counterdetls.put("inCount", 0);
					v1counterdetls.put("startDate", basetimestamp);
					v1counterdetls.put("endDate", (basetimestamp + 3600));
					v1counterdetails.add(v1counterdetls);
					counterdetls.put("inCountTimeStamps", generateEvent(basetimestamp, 0));
					counterdetls.put("outCountTimeStamps", generateEvent(basetimestamp, 0));
					counterdetails.add(counterdetls);
				} else {
					walkin = generateRandomWalkin(15, 1);
					storewalkin = storewalkin + walkin;

					counterdetls.put("outCount", walkin);
					counterdetls.put("inCount", walkin - 1);
					counterdetls.put("startDate", basetimestamp);
					counterdetls.put("endDate", (basetimestamp + 3600));
					JSONObject v1counterdetls = new JSONObject();
					v1counterdetls.put("outCount", walkin);
					v1counterdetls.put("inCount", walkin - 1);
					v1counterdetls.put("startDate", basetimestamp);
					v1counterdetls.put("endDate", (basetimestamp + 3600));
					v1counterdetails.add(v1counterdetls);
					counterdetls.put("inCountTimeStamps", generateEvent(basetimestamp, walkin - 1));
					counterdetls.put("outCountTimeStamps", generateEvent(basetimestamp, walkin));
					counterdetails.add(counterdetls);

				}
				basetimestamp = basetimestamp + 3600;
			}

			walkins[storcount] = storewalkin;
			storcount = storcount + 1;
			object.put("counterDetails", counterdetails);
			v1obj.put("counterDetails", v1counterdetails);
		} catch (Exception ex) {
			Reporter.log(ex.getMessage());
			return null;
		}
		V1json = v1obj.toString();
		System.out.println(V1json);
		return object.toString();
	}

	public static String generateRandommobile() {
		String set1 = Integer.toString(generateRandomWalkin(9, 7));
		String set2 = Integer.toString(generateRandomWalkin(9, 0));
		String set3 = Integer.toString(generateRandomWalkin(9, 0));
		String set4 = Integer.toString(generateRandomWalkin(9, 0));
		String set5 = Integer.toString(generateRandomWalkin(9, 0));
		String set6 = Integer.toString(generateRandomWalkin(9, 0));
		String set7 = Integer.toString(generateRandomWalkin(9, 0));
		String set8 = Integer.toString(generateRandomWalkin(9, 0));
		String set9 = Integer.toString(generateRandomWalkin(9, 0));
		String set10 = Integer.toString(generateRandomWalkin(9, 0));
		System.out.println(set1 + set2 + set3 + set4 + set5 + set6 + set7 + set8 + set9 + set10);
		return (set1 + set2 + set3 + set4 + set5 + set6 + set7 + set8 + set9 + set10);

	}

	@SuppressWarnings("unchecked")
	public static JSONArray generateEvent(long timestamp, int count) {
		JSONArray CountTimeStamps = new JSONArray();
		for (int i = 0; i < count; i++) {
			CountTimeStamps.add(timestamp + generateRandomWalkin(3600, 1));
		}
		return CountTimeStamps;

	}

	public static int generateRandomWalkin(int max, int min) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static String createTransactionPayload(int deletedays) throws ParseException
	{
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject response = new JSONObject();
		int billamount = 0;
		String day = "";
		Date date = new Date();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
		day = dformat.format(date);
		date = new SimpleDateFormat("yyyy/MM/dd 00:00:00").parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -deletedays);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:00:00");
		for (int hour = 0; hour < 24; hour++) {
			String d = format.format(cal.getTime());
			ArrayList<String> mobileNumbers = (ArrayList<String>) time_mobile.get(d);
			for (int i = 0; i < mobileNumbers.size(); i++) {
				JSONObject temp = new JSONObject();
				temp.put("billing_time", d);
				temp.put("bill_client_id", mobileNumbers.get(i));
				temp.put("type", "regular");
				temp.put("number", "BILL-" + mobileNumbers.get(i));
				billamount = generateRandomWalkin(3500, 1000);
				temp.put("amount", billamount);
				temp.put("notes", "Test Bills");
				temp.put("gross_amount", billamount - 250);
				temp.put("discount", 250);
				JSONObject customer = new JSONObject();
				customer.put("mobile", mobileNumbers.get(i));
				temp.put("customer", customer);
				array.add(temp);
			}
			object.put("transaction", array);
			response.put("root", object);
			cal.add(Calendar.MINUTE, 60);
		}

		System.out.println("The transaction request is " + response.toString());
		return response.toString();
	}
	
	public static String createCustomerPayload(int deletedays) throws ParseException
	{
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject response = new JSONObject();
		String day = "";
		Date date = new Date();
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
		day = dformat.format(date);
		date = new SimpleDateFormat("yyyy/MM/dd 00:00:00").parse(day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -deletedays);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:00:00");
		time_mobile = new HashMap<String, List<String>>();
		for (int i = 0; i < 24; i++) {
			String d = format.format(cal.getTime());
			System.out.println("The hour is " + d);
			System.out.println("The storewalkin is " + storewalkin);
			String mobile;
			int totalconversion = generateRandomWalkin(5, 1);
			System.out.println("The totalConversion is " + totalconversion);
			ArrayList<String> mobiles = new ArrayList<>();
			for (int j = 0; j < totalconversion; j++) {
				JSONObject temp = new JSONObject();
				mobile = generateRandommobile();
				System.out.println("the mobile number is " + mobile);
				mobiles.add(mobile);
				temp.put("mobile", mobile);
				temp.put("email", "em_" + mobile + "@em.com");
				temp.put("external_id", mobile);
				temp.put("firstname", "Raj");
				temp.put("lastname", "Malhotra");
				temp.put("gender", "M");
				temp.put("registered_on", d);
				array.add(temp);
			}
			time_mobile.put(d, mobiles);
			object.put("customer", array);
			response.put("root", object);
			cal.add(Calendar.MINUTE, 60);
		}
		System.out.println("The json is " + response.toString());
		return response.toJSONString();
	}
	
	public static Response sendRequest(String URL, String json, String till) {
		System.out.println("The request body is\n");
		System.out.println(json);
		System.out.println("The till name is " + till);
		System.out.println("The url is " + URL);
		Map<String, String> header = new HashMap<String, String>();
		try {
			header.put("Content-Type", "application/json");
			String md5password = EncryptionUtil.md5Encrypt("123");
			String auth = EncryptionUtil.authorizationHeader(till, md5password);
			header.put("Authorization", auth);
			RequestSpecification httpRequest = RestAssured.given();
			RestAssuredConfig restAssuredConfig =RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			        setParam("http.connection.timeout",300000).
			        setParam("http.socket.timeout",300000).
			        setParam("http.connection-manager.timeout",300000));
			httpRequest.config(restAssuredConfig);
			Response response = (Response) httpRequest.auth().basic(till, EncryptionUtil.md5Encrypt("Test1234"))
					.headers(header).accept(ContentType.JSON).body(json).log().all().post(URL).then().extract()
					.response();
			System.out.println(response.getBody().asString());
			System.out.println(response.getStatusCode());
			return response;
		} catch (Exception ex) {
			return null;
		}
	}

}
