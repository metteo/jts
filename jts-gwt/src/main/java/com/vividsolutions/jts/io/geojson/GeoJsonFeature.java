package com.vividsolutions.jts.io.geojson;

import com.google.web.bindery.autobean.shared.Splittable;

interface GeoJsonFeature extends GeoJsonObject {

	Splittable getId();
	void setId(Splittable id);

	GeoJsonGeometry getGeometry();
	void setGeometry(GeoJsonGeometry geometry);

	Splittable getProperties();
	void setProperties(Splittable properties);
}
