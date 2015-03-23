package com.vividsolutions.jts.io.geojson;

import java.util.List;

interface GeoJsonObject {

	public String getType();
	public void setType(String type);

	public GeoJsonCrs getCrs();
	public void setCrs(GeoJsonCrs crs);

	public List<Double> getBbox();
	public void setBbox(List<Double> bbox);
}
