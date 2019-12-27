package com.capillary.VisitorMatrix.qa.framework.Requests;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.capillary.VisitorMatrix.qa.framework.util.EncryptionUtil;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {

	LogConfig logconfig = new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails()
			.enablePrettyPrinting(true);

	public static Response getRequestwithPathparams(String url, Map<String, String> pathparam,
			Map<String, String> headers) throws NoSuchAlgorithmException {
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture));
		Response response = (Response) httpRequest.headers(headers).accept(ContentType.JSON).pathParams(pathparam).log()
				.all().get(url).then().extract().response();
		return response;
	}

	public static String generateToken(String userName, String password, String url) {
		Map<String, String> logindetails = new HashMap<String, String>();
		logindetails.put("username", userName);
		logindetails.put("password", password);
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httprequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response response = (Response) httprequest.header("Content-Type", "application/x-www-form-urlencoded")
				.formParams(logindetails).log().all().post(url).then().extract().response();
		System.out.println("The status code is " + response.getStatusCode());
		System.out.println("The response is " + response.getBody().asString());
		return response.getBody().jsonPath().getString("token").toString();
	}

	public static Response postRequestWithJson(Map<String, String> header, String body, String url) {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httprequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response response = httprequest.headers(header).body(body).log().all().post(url).then().extract().response();
		return response;
	}

	public static Response postmultipartrequestWithPathParams(String Url, Map<String, String> header,
			Map<String, String> formparms, String multiPartData) throws NoSuchAlgorithmException {
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture));
		Response response = (Response) httpRequest.headers(header).header("Content-Type", "multipart/form-data")
				.multiPart("image", new File(multiPartData)).formParams(formparms).log().all().post(Url).then()
				.extract().response();
		return response;
	}

	public static Response postwithpathqueryparametrs(String url, String userName, String password,
			Map<String, String> pathparam, Map<String, String> queryparam) throws NoSuchAlgorithmException {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response response = (Response) httpRequest.auth().basic(userName, EncryptionUtil.md5Encrypt(password))
				.header("Content-Type", "application/json").pathParams(pathparam).queryParams(queryparam).log().all()
				.post(url).then().extract().response();
		return response;
	}

	public static Response deletewithpathqueryparameters(String Url, String userName, String password,
			Map<String, String> pathParam, Map<String, String> queryParam) throws NoSuchAlgorithmException {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response response = (Response) httpRequest.auth().basic(userName, EncryptionUtil.md5Encrypt(password))
				.header("Content-Type", "application/json").pathParams(pathParam).queryParams(queryParam).log().all()
				.delete(Url).then().extract().response();
		return response;
	}

	public static Response deletewithPathParameters(String Url, String userName, String password,
			Map<String, String> pathParam) throws NoSuchAlgorithmException {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response response = (Response) httpRequest.auth().basic(userName, EncryptionUtil.md5Encrypt(password))
				.header("Content-Type", "application/json").pathParams(pathParam).log().all().delete(Url).then()
				.extract().response();
		return response;
	}

	public static Response postRequestwithFormParams(String url, Map<String, String> header,
			Map<String, String> formParameters) {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httprequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)
								.encodeContentTypeAs("multipart/form-data", ContentType.TEXT)));
		Response response = httprequest.headers(header).header("content-type", "application/x-www-form-urlencoded")
				.formParams(formParameters).log().all().post(url).then().extract().response();
		return response;
	}

	public static Response getRequestWithparams(Map<String, String> headers, Map<String, String> params, String url) {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httprequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response reponse = httprequest.headers(headers).params(params).get(url).then().extract().response();
		return reponse;
	}

	public static Response getRequestWithqueryparams(Map<String, String> headers, Map<String, String> params,
			String url) {
		EncoderConfig encoderconfig = new EncoderConfig();
		RequestSpecification httprequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture))
				.config(RestAssured.config()
						.encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		Response reponse = httprequest.headers(headers).queryParams(params).log().all().get(url).then().extract()
				.response();
		return reponse;
	}

	public static Response postRequestWithJsonBody(Map<String, String> headers, String url, JSONObject body) {
		RequestSpecification httpRequest = RestAssured.given()
				.filter(new RequestLoggingFilter(RequestResponseLogger.getInstance().requestCapture));
		Response response = httpRequest.headers(headers).body(body.toString()).log().all().post(url).then().extract()
				.response();
		return response;
	}
}
