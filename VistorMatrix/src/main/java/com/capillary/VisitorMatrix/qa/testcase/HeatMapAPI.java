package com.capillary.VisitorMatrix.qa.testcase;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.ClusterInfo;

public class HeatMapAPI {
	public static String Cluster;
	@BeforeClass
	public void setup() {
		Cluster = ClusterInfo.getInstance().getCluster();
	}
  @Test
  public void pushData() throws IOException, ParseException {
	  ArrayList<LinkedHashMap<String, String>> ffc_dataSet = CsvUtil.getInstance()
				.getCsvData(new File(ExecutionConfig.HEAT_MAP_DATA_PATH));
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
  }
}
