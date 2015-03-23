package com.vividsolutions.jtswebapp.client;

import java.util.logging.Logger;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.geojson.GeoJsonReader;

public class GeoJson {
	
	private static final Logger sLogger = Logger.getLogger("GeoJson");

	public void check() {
		GeoJsonReader r = new GeoJsonReader();
		Geometry g = r.read("{\"type\":\"Point\", \"coordinates\":[10, 20]}");
		sLogger.info(g.toString());
	}
}
