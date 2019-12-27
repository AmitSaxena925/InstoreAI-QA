//package com.capillary.VisitorMatrix.qa.framework.util.serviceDiscovery;
//
//import com.capillary.servicediscovery.IServiceDiscovery;
//import com.capillary.servicediscovery.ServiceDiscovery;
//import com.capillary.servicediscovery.model.Module;
//
//public abstract class ServiceDiscoveryAdapter implements ServiceAdapter{
//	protected ServiceDiscovery m_serviceDiscovery;
//	protected boolean isLocal = false;
//	public ServiceDiscoveryAdapter(String module, String version,boolean local){
//		isLocal = local;
//		if (!local){
//			ServiceDiscovery.setModule(new Module(module, version));
//			m_serviceDiscovery = ServiceDiscovery.getInstance();
//		}
//	}
//	public IServiceDiscovery getDiscoveryService() {
//		return m_serviceDiscovery;
//	}
//}