package com.capillary.VisitorMatrix.api.test.heatMap;

import org.json.simple.JSONArray;

public class Zone {
	public String zoneName;
	public boolean active;
	public JSONArray gridInfo;

	public Zone(String name, boolean active, JSONArray info) {
		this.zoneName = name;
		this.active = active;
		this.gridInfo = info;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public JSONArray getGridInfo() {
		return gridInfo;
	}

	public void setGridInfo(JSONArray gridInfo) {
		this.gridInfo = gridInfo;
	}

	@Override
	public String toString() {
		return "Zone [zoneName=" + zoneName + ", active=" + active + ", gridInfo=" + gridInfo + "]";
	}
	
}
