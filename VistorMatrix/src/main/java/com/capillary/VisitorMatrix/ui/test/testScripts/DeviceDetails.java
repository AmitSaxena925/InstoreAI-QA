package com.capillary.VisitorMatrix.ui.test.testScripts;

public class DeviceDetails {
	@Override
	public String toString() {
		return "DeviceDetail is [deviceId=" + deviceId + ", deviceType=" + deviceType + ", deviceName=" + deviceName
				+ ", tillId=" + tillId + ", storeId=" + storeId + ", orgId=" + orgId + ", notes=" + notes
				+ ", is_active=" + is_active + "]";
	}
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getTillId() {
		return tillId;
	}
	public void setTillId(int tillId) {
		this.tillId = tillId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public DeviceDetails() {
	}
	public long deviceId;
	public DeviceDetails(long deviceId, String deviceType, String deviceName, int tillId, int storeId, int orgId,
			String notes, int is_active) {
		super();
		this.deviceId = deviceId;
		this.deviceType = deviceType;
		this.deviceName = deviceName;
		this.tillId = tillId;
		this.storeId = storeId;
		this.orgId = orgId;
		this.notes = notes;
		this.is_active = is_active;
	}
	public String deviceType;
	public String deviceName;
	public int tillId;
	public int storeId;
	public int orgId;
	public String notes;
	public int is_active;
//	public 
	
	

}