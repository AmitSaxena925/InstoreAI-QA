package com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery;

//import com.capillary.servicediscovery.model.KnownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BaseQueryExecutorPool {

//	private static final Logger logger = LoggerFactory.getLogger(BaseQueryExecutorPool.class);
//    private final Semaphore available = new Semaphore(10, true);
//    /**
//     *
//     * This is for sharded databases
//     * @param knownService
//     * @param orgId
//     * @return
//     * @throws Exception
//     */
//    public Connection getShardedDBConnection(KnownService knownService, int orgId) throws Exception {
//        logger.info("Getting a sharded connection for ORGID: " + orgId + " for DB: " + knownService.name());
//        available.tryAcquire(120, TimeUnit.SECONDS);
//        logger.info("Available connections: " + available.availablePermits());
//        try {
//            return new MysqlConnector(knownService,orgId).getConnection();
//        } catch (Throwable t) {
//            available.release();
//            throw new RuntimeException(t);
//        }
//    }
//
//    public void releaseConnection(Connection connection) {
//        available.release();
//        logger.info("Available connections: " + available.availablePermits());
//    }
    /**
     *
     * This is for non sharded databases
     * @param knownService
     * @return
     * @throws Exception
     */
//    public Connection getDBConnection(KnownService knownService) throws Exception {
//        logger.info("Getting a connection for DB: " + knownService.name());
//        return new MysqlConnector(knownService).getConnection();
//    }
}
