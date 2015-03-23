package com.vividsolutions.jts.io.geojson;

import java.util.List;

import com.google.web.bindery.autobean.shared.Splittable;

interface GeoJsonGeometry extends GeoJsonObject {

	Splittable getCoordinates();
	void setCoordinates(Splittable coords);
	
	List<GeoJsonGeometry> getGeometries();
	void setGeometries(List<GeoJsonGeometry> geoms);
}
