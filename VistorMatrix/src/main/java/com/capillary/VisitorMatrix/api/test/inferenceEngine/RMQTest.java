package com.capillary.VisitorMatrix.api.test.inferenceEngine;

import java.io.IOException;
import org.json.simple.JSONObject;

public class RMQTest {
	public static void main(String[] args) throws IOException {
		System.out.println("Hellow world");
		Producer p = new Producer("storeSenseHubInferenceEngineQueue");
		JSONObject data = new JSONObject();
//		System.out.println(data.toJSONString());
		data.put("visitorDocId","5cfa053b66c3b97638c199df");
		System.out.println(data.toJSONString());
		p.sendMessage(data);
	}
}
