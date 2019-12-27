package com.capillary.VisitorMatrix.api.test.deviceRegistration;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.capillary.VisitorMatrix.qa.framework.Requests.Request;
import com.capillary.VisitorMatrix.qa.framework.annotation.DataProviderParams;
import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.CsvUtil;
import com.capillary.VisitorMatrix.qa.framework.util.DataProviderUtil;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.ClusterInfo;
import com.capillary.VisitorMatrix.ui.test.testScripts.DeviceDetails;


public class StoreSenseHubAPITest {
	public static ArrayList<LinkedHashMap<String, String>> devicedata;
	// public static final DB = ExecutionConfig.DR_META_DB + "`.`"+
	// ExecutionConfig.DR_META_DB_TABLE;

	@BeforeTest
	public void beforeTest() throws Exception {
		DeviceRegistrationValidation.createConnection();
		devicedata = CsvUtil.getInstance()
				.getCsvData(new File(ExecutionConfig.getTestDataPath() + "/deviceRegister.csv"));
		for (HashMap<String, String> d : devicedata) {
			System.out.println(d.toString());
		}
		DeviceRegistrationValidation.dbDeviceDetails = DeviceRegistrationValidation
				.getDeviceDetail(DeviceRegistrationAPITest.devicedata);

		for (DeviceDetails d : DeviceRegistrationValidation.dbDeviceDetails) {
			System.out.println(d.toString());
		}
		boolean deviceUploadStatus = DeviceRegistrationValidation.uploadDevices();
		if (deviceUploadStatus == false) {
			System.out.println("DeviceRegistrationAPITest.beforeTest() is failed");
		}
		for (int i = 0; i < DeviceRegistrationValidation.dbDeviceDetails.size(); i++) {
			System.out.println(DeviceRegistrationValidation.dbDeviceDetails.get(i).toString());
		}
//		String insertQuery = "INSERT INTO device_registration_meta(device_id,device_type,wifi_mac_id,lan_mac_id,till_id,store_id,org_id,device_name,processor,lens,with_staff_switch,case_version,case_color,is_active,enable_notifications,enable_demographics_capture,date_installed,date_removed,auto_update_time,notes) VALUES\n" + 
//				" (201907171834015,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834016,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834017,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834018,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834019,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834020,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834021,'FFC','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')\n" + 
//				",(201907171834022,'CE','b8:27:eb:d6:b7:6e','b8:27:eb:83:e2:3b',0,0,0,'qa_bay','Raspberry_Pi_3B_Plus','V2',0,'cvp_dev','Black',1,1,0,'03-20-2019 00:00:00','NA','03-20-2019 00:00:00','imported')";
//		DeviceRegistrationValidation.executeUpdate(insertQuery);
	}

}
