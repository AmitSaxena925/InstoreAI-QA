package com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery;

public class ClusterInfo {
	public static ClusterInfo ci;

	public ClusterInfo() {

	}

	public static synchronized ClusterInfo getInstance() {
		if (ci == null) {
			return new ClusterInfo();
		}
		return ci;
	}

	public String getCluster() {
		if (System.getProperty("Cluster") == null) {
			return "NIGHTLY";
		}
		if (System.getProperty("Cluster").equals("nightly")) {
			return "NIGHTLY";
		} else if (System.getProperty("Cluster").equals("production")) {
			return "PROD";
		} else
			return "NIGHTLY";
	}
}