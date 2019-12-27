package com.capillary.VisitorMatrix.api.test.heatMap;

import java.util.List;

public class Layout {
	public String layoutName;
	public String layoutId;
	public boolean isCurrent;
	public String image;
	public String startDate;
	public String endDate;
	public List<Zone> zones;
	public Layout()
	{		
	}
	
	public Layout(String Image, String LayoutName, String Id, boolean IsCurrent, String StartDate)
	{
		this.image = Image;
		this.layoutName = LayoutName;
		this.layoutId = Id;
		this.isCurrent = IsCurrent;
		this.startDate = StartDate;
	}
	public String getLayoutName() {
		return layoutName;
	}
	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String id) {
		this.layoutId = id;
	}
	public boolean getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Zone> getZones() {
		return zones;
	}
	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	@Override
	public String toString() {
		return "Layout [layoutName=" + layoutName + ", layoutId=" + layoutId + ", isCurrent=" + isCurrent + ", image="
				+ image + ", startDate=" + startDate + ", endDate=" + endDate + ", zones=" + zones + "]";
	}
}
