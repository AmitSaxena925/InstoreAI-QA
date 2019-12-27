package com.capillary.VisitorMatrix.qa.testcase;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.annotations.Test;

import com.capillary.VisitorMatrix.qa.framework.Requests.Request;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.qa.framework.util.TimeUtil;

import io.restassured.response.Response;

public class SampleFFCTest {
	
	@Test
	public void pushData() throws Exception {
		ArrayList<LinkedHashMap<String, String>> ffc_dataSet = CsvUtil.getInstance()
				.getCsvData(new File(ExecutionConfig.getTestDataPath()+ExecutionConfig.FFC_DATA_PATH));
		for (LinkedHashMap<String, String> ffc_data : ffc_dataSet) {
			String org_id = ffc_data.get("org_id").toString();
			String zone_id = ffc_data.get("Zone_id").toString();
			String store_id = ffc_data.get("Store_id").toString();
			String store_name = ffc_data.get("Store_name").toString();
			String till_id = ffc_data.get("till_id").toString();
			String till_name = ffc_data.get("till_name").toString();
			Long device_id = Long.parseLong(ffc_data.get("device_id").toString());
			int delete_days = Integer.parseInt(ffc_data.get("delete_days").toString());
			pushFFCData.push_ffc_data(org_id, zone_id, store_id, store_name, till_id, till_name, device_id,
					delete_days);		
//			pushFFCData.push_customer_data(till_name,delete_days);
//			pushFFCData.push_transaction_data(till_name,delete_days);			
		}
		Map<String, String> pathparams = new HashMap<String, String>();
		pathparams.put("notificationType", "dataSanity");
		pathparams.put("configId", ExecutionConfig.FFC_CONFIG_ID);
		pathparams.put("orgId", "794");
		String requestedTimeStamp = TimeUtil.getCurrentTime();
		Map<String, String> headers = new HashMap<String, String>();
		Response response = Request.getRequestwithPathparams(ExecutionConfig.SEND_NOTIFICATION, pathparams,headers);
		System.out.println(response.getBody().asString());
		assertEquals(200, response.getStatusCode());
//		int numberOfRows = Integer.parseInt(ExecutionConfig.EXPECTED_NUM_NOTIF);
//		FFCDataValidation fd = new FFCDataValidation();
//		fd.createConnection(Integer.parseInt(ExecutionConfig.ORG_ID));
//		fd.validate_Data(Integer.parseInt(ExecutionConfig.ORG_ID), numberOfRows, requestedTimeStamp);
	}

}