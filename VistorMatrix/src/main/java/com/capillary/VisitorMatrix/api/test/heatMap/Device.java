package com.capillary.VisitorMatrix.api.test.heatMap;

import java.util.List;

public class Device {
	String deviceName;
	String SectionName;
	int storeId;
	int orgId;
	long deviceId;
	List<Layout> layouts;
	
	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}
	
	public List<Layout> getLayouts()
	{
		return layouts;
	}
	
	public Device() {
		
	}

	public Device(String deviceName, String sectionName, int storeId, int orgId, long deviceId) {
		this.deviceName = deviceName;
		this.SectionName = sectionName;
		this.storeId = storeId;
		this.orgId = orgId;
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSectionName() {
		return SectionName;
	}

	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	
	public void setOrgId(int orgId)
	{
		this.orgId = orgId;
	}
	public int getOrgId()
	{
		return orgId;
	}
	
	
	
}
