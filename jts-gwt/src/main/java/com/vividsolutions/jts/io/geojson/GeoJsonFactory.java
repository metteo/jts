package com.vividsolutions.jts.io.geojson;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

interface GeoJsonFactory extends AutoBeanFactory {

	AutoBean<GeoJsonCrs> crs();

	AutoBean<GeoJsonGeometry> geometry();

	AutoBean<GeoJsonFeature> feature();

	AutoBean<GeoJsonFeatureCollection> featureCollection();
}
