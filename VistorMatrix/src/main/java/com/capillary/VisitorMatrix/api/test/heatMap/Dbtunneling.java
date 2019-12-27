package com.capillary.VisitorMatrix.api.test.heatMap;

import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.capillary.VisitorMatrix.qa.framework.util.JsonUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class Dbtunneling {
	public MongoCredential credential;
	public MongoClient mongoClient;

	public static Dbtunneling tunnel;

	public static Dbtunneling getInstance() {
		if (tunnel == null) {
			return new Dbtunneling();
		} else {
			return tunnel;
		}
	}

	@SuppressWarnings("deprecation")
	public void createConnection() throws Exception {
////		ServiceDiscovery.setModule(new Module("BIUItests", "1.0"));
////		Service mongoServerEndpoint = ServiceDiscovery.getInstance().get(KnownService.STORECARE_DB_MONGO);
////		String mongoIP = mongoServerEndpoint.getAddress();
////		int mongoPort = mongoServerEndpoint.getPort();
//		credential = MongoCredential.createCredential(ExecutionConfig.MONGODB_USER_NAME, ExecutionConfig.MONGODB_ROLE,
//				ExecutionConfig.MONGODB_PASSWORD.toCharArray());
////		mongoClient = new MongoClient(
////				new ServerAddress(ExecutionConfig.HM_MONGODB_IP, Integer.parseInt(ExecutionConfig.HM_MONGODB_PORT)),
////				Arrays.asList(credential));
//		 mongoClient = new MongoClient(new ServerAddress("localhost", 27000),Arrays.asList(credential));
		credential = MongoCredential.createCredential(ExecutionConfig.MONGODB_USER_NAME,
				ExecutionConfig.MONGODB_ROLE, ExecutionConfig.MONGODB_PASSWORD.toCharArray());
		mongoClient = new MongoClient(new ServerAddress("localhost", 27001), Arrays.asList(credential));
	}

	@SuppressWarnings("unchecked")
	public JSONArray executequery(BasicDBObject query, String databaseName, String collectionName)
			throws ParseException {
		@SuppressWarnings("deprecation")
		DB database = mongoClient.getDB(databaseName);
		System.out.println(query.toJson().toString());
		DBCollection coll = database.getCollection(collectionName);
		DBCursor cursor = coll.find(query);
		JSONArray array = new JSONArray();
		try {
			while (cursor.hasNext()) {
				String doc = String.format("%s", cursor.next());
				JSONObject obj = JsonUtil.StringtoJson(doc);
				array.add(obj);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			cursor.close();
		}
		return array;
	}

	public void closeMongoConnection() {
		mongoClient.close();
	}
}
