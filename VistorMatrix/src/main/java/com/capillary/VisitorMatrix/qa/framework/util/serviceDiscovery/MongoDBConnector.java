//package com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery;
//
//import java.sql.Connection;
//
//import javax.sql.DataSource;
//
//import org.apache.logging.log4j.core.appender.db.jdbc.DriverManagerConnectionSource;
//
//import com.capillary.servicediscovery.model.KnownService;
//import com.capillary.servicediscovery.services.AuthenticatedMongoDBService;
//import com.capillary.servicediscovery.services.MongoDBService;
//import com.mongodb.DB;
//import com.mongodb.Mongo;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.client.MongoDatabase;
//
//public class MongoDBConnector extends FFCNotifServiceDiscoveryModule {
//	protected MongoDBService mongodbService;
//	protected Connection connection;
//	protected DB db;
//	protected MongoClient mongo;
//	protected AuthenticatedMongoDBService mongoDBservice;
//	public MongoDBConnector(KnownService knownService) throws Exception {
//		if (getDBService() == null) {
//			mongoDBservice = (AuthenticatedMongoDBService) m_serviceDiscovery.get(knownService);
//		}
//		if (ClusterInfo.getInstance().getCluster().equals("NIGHTLY")) {
////			mongodbService.
//			String uri = getDBService().getURI();
//			System.out.println("the uri is "+uri);
//			mongo = new MongoClient(uri);
//			MongoDatabase db =mongo.getDatabase("dbName");
////			boolean auth = db.au("pankaj", "pankaj123".toCharArray());
//			
//
//		}
//	}
//
//	public Connection getConnection() {
//		return connection;
//	}
//
//	public MongoDBService getDBService() {
//		return mongodbService;
//	}
//
//}