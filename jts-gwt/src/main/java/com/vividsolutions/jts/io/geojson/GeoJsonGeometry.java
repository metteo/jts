package com.vividsolutions.jts.io.geojson;

import java.util.List;

import com.google.web.bindery.autobean.shared.Splittable;

interface GeoJsonGeometry extends GeoJsonObject {
	
	String TYPE_POINT = "Point";
	String TYPE_LINE_STRING = "LineString";
	String TYPE_POLYGON = "Polygon";
	
	String TYPE_MULTI_POINT = "MultiPoint";
	String TYPE_MULTI_LINE_STRING = "MultiLineString";
	String TYPE_MULTI_POLYGON = "MultiPolygon";
	
	String TYPE_GEOMETRY_COLLECTION = "GeometryCollection";

	Splittable getCoordinates();
	void setCoordinates(Splittable coords);
	
	List<GeoJsonGeometry> getGeometries();
	void setGeometries(List<GeoJsonGeometry> geoms);
}
