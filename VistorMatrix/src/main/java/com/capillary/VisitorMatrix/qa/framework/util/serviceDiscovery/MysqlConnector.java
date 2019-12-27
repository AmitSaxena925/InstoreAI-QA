//package com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery;
//
//import java.sql.Connection;
//
//import javax.sql.DataSource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import com.capillary.servicediscovery.model.Key;
//import com.capillary.servicediscovery.model.KnownService;
//import com.capillary.servicediscovery.services.MySQLDBService;
//
//public class MysqlConnector extends FFCNotifServiceDiscoveryModule {
//
//	private static final Logger logger = LoggerFactory.getLogger(MysqlConnector.class);
//
//	protected MySQLDBService sqldbService;
//	protected Connection connection;
//	protected DataSource ds;
//
//	public MysqlConnector(KnownService knownService, int orgId) throws Exception {
//		System.out.println("Code is in mysqlConnector");
//		if (getDBService() == null) {
//			sqldbService = (MySQLDBService) m_serviceDiscovery.get(knownService, new Key(orgId));
//		}
//		if (ClusterInfo.getInstance().getCluster().equals("NIGHTLY")) {
//
//			ds = new DriverManagerDataSource(getDBService().getURI() + "?user=capillary&password=123"
//					+ "&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
//		} else {
//			ds = new DriverManagerDataSource(
//					getDBService().getURI() + "?user=" + getDBService().getUserName() + "&password="
//							+ getDBService().getPassword() + "&useUnicode=true&characterEncoding=UTF-8&&useSSL=false");
//		}
//		connection = ds.getConnection();
//	}
//
//	public MysqlConnector(KnownService knownService) throws Exception {
//		if (getDBService() == null) {
//			sqldbService = (MySQLDBService) m_serviceDiscovery.get(knownService);
//		}
//		if (ClusterInfo.getInstance().getCluster().equals("NIGHTLY")) {
//
//			ds = new DriverManagerDataSource(getDBService().getURI() + "?user=capillary&password=123"
//					+ "&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
//		} else {
//			ds = new DriverManagerDataSource(
//					getDBService().getURI() + "?user=" + getDBService().getUserName() + "&password="
//							+ getDBService().getPassword() + "&useUnicode=true&characterEncoding=UTF-8&&useSSL=false");
//		}
//		connection = ds.getConnection();
//	}
//
//	public Connection getConnection() {
//		return connection;
//	}
//
//	public MySQLDBService getDBService() {
//		return sqldbService;
//	}
//}