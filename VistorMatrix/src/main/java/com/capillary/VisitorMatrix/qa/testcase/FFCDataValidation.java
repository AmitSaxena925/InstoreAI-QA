//package com.capillary.VisitorMatrix.qa.testcase;
//
//import static org.testng.Assert.assertEquals;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import org.apache.commons.dbutils.handlers.MapListHandler;
//
//import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
//import com.capillary.VisitorMatrix.qa.framework.util.TimeUtil;
//import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.BaseQueryExecutorPool;
//import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.ClusterInfo;
//import com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery.MysqlConnector;
//import com.capillary.servicediscovery.model.KnownService;
//
//public class FFCDataValidation {
//	BaseQueryExecutorPool pool = new BaseQueryExecutorPool();
//	private String dbName = "`ffc_notification`";
//	private String tableName = "`notifications`";
//	private String dbTableName = dbName.concat(".").concat(tableName);
//	public static MysqlConnector dbclient;
//	public static Connection conn;
//
//	public void createConnection(int org_id) throws Exception {
//		conn = pool.getShardedDBConnection(KnownService.INTOUCH_DB_MYSQL_MASTER, org_id);
//	}
//
//	public List<Map<String, Object>> executeQuery(String query) throws SQLException, ParseException {
//		PreparedStatement stmt = FFCDataValidation.conn.prepareStatement(query);
//		ResultSet rs = stmt.executeQuery();
//		MapListHandler mapListHandler = new MapListHandler();
//		List<Map<String, Object>> result = mapListHandler.handle(rs);
//		return result;
//	}
//
//	public void validate_Data(int org_id, int numberOfRows, String requestedTimeStamp)
//			throws SQLException, ParseException {
//		String query = "Select max(sent_time) as max_time from " + dbTableName + " where org_id = " + org_id;
//		List<Map<String, Object>> results = executeQuery(query);
//		System.out.println("The API Triggered time is " + requestedTimeStamp);
//		System.out.println("The MaxTime in DB is " + results.get(0).get("max_time").toString());
//		System.out.println("The triggered time is "
//				+ TimeUtil.verifyTriggeredTime(requestedTimeStamp, results.get(0).get("max_time").toString()));
//		if (TimeUtil.verifyTriggeredTime(requestedTimeStamp, results.get(0).get("max_time").toString())) {
//			String day = TimeUtil.getFormatTime(results.get(0).get("max_time").toString());
//			String min = TimeUtil.getmodifiedTime("SECONDS", -3, day);
//			String max = TimeUtil.getmodifiedTime("SECONDS", 1, day);
//			query = " SELECT count(*) as total FROM `ffc_notification`.`notifications` where org_id = 0 AND sent_time BETWEEN '"
//					+ min + "' AND '" + max + "'";
//			System.out.println(query);
//			results = executeQuery(query);
//			System.out.println("The total count is " + results.get(0).get("total").toString());
//			assertEquals(numberOfRows, Integer.parseInt(results.get(0).get("total").toString()));
//			query = "SELECT user_id,role,user_enitity_type,message,defaulter_entites FROM " + dbTableName + " "
//					+ "WHERE org_id = " + org_id + " AND notification_config_id = "
//					+ ExecutionConfig.FFC_CONFIG_ID
//					+ " AND type = 'DATA-SANITY' AND kpis = 'VISITOR' AND priority_type = 'INFO' AND sent_time BETWEEN  '"
//					+ min + "' AND '" + max + "' AND status = 'SENT' AND readAt IS NULL AND channel = 'PUSH'";
//			System.out.println("The query is " + query);
//			results = executeQuery(query);
//			for (int i = 0; i < results.size(); i++) {
//				System.out.println(results.get(i).get("defaulter_entites"));
//			}			
//		}
//	}
//}
