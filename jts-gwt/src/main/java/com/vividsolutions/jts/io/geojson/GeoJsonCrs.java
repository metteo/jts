package com.vividsolutions.jts.io.geojson;

import com.google.web.bindery.autobean.shared.Splittable;

interface GeoJsonCrs {

	String TYPE_NAME = "name";
	String TYPE_LINK = "link";

	String getType();
	void setType(String type);

	Splittable getProperties();
	void setProperties(Splittable properties);
}
