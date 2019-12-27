package com.capillary.VisitorMatrix.api.test.inferenceEngine;

import java.io.IOException;
import org.json.simple.JSONObject;

public class Producer extends EndPoint{

	public Producer(String endpointName) throws IOException {
		super(endpointName);
	}
	
	public void sendMessage(JSONObject object) throws IOException {
	    channel.basicPublish("",endPointName, null, object.toString().getBytes("utf-8"));
	}
}
