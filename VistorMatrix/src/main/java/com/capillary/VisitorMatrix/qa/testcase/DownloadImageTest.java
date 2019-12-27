package com.capillary.VisitorMatrix.qa.testcase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;

public class DownloadImageTest {
	public static void main(String[] args) {
	    HttpClient httpClient = HttpClientBuilder.create().build(); 

	    try {

	        HttpPost request = new HttpPost("http://nightly.capillary.in/instoreai/storesense/storesense-hub/report/demography/inferred-image?key=nightly/50303/50014457/50015469/images/dedcbe0b-f4ae-4069-ba2b-23f482ea9d91.jpg");
	        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
//	        postParameters.add(new BasicNameValuePair("content", "options"));
//	        postParameters.add(new BasicNameValuePair("options", "json"));
//	        postParameters.add(new BasicNameValuePair("constr", "Chart"));
//	        postParameters.add(new BasicNameValuePair("type", "png"));
	        String usernameColonPassword = "cvp.qa:202cb962ac59075b964b07152d234b70";
	        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(usernameColonPassword.getBytes());
//	        request.setEntity(new UrlEncodedFormEntity(postParameters));
	        request.setHeader(HttpHeaders.AUTHORIZATION,basicAuthPayload);
	        HttpResponse response = httpClient.execute(request);
	        System.out.println(response.getStatusLine().getStatusCode());


	        // write the inputStream to a FileOutputStream
	        OutputStream outputStream =
	                    new FileOutputStream(new File("bild11.png"));

	        int read = 0;
	        byte[] bytes = new byte[1024];

	        while ((read = response.getEntity().getContent().read(bytes)) != -1) {
	            outputStream.write(bytes, 0, read);
	        }

	        outputStream.close();

	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}


