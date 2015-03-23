package com.vividsolutions.jts.io.geojson;

import java.util.List;

interface GeoJsonFeatureCollection extends GeoJsonObject {

	public List<GeoJsonFeature> getFeatures();

	public void setFeatures(List<GeoJsonFeature> features);
}