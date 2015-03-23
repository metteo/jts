package com.vividsolutions.jts.io.geojson;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.OutStream;

public class GeoJsonWriter {

	public GeoJsonWriter() {
		this(2);
	}
	
	public GeoJsonWriter(int outputDimension) {
		this(outputDimension, false, false);
	}
	
	public GeoJsonWriter(int outputDimension, boolean includeCrs, boolean includeBbox) {
		
	}
	
	public String write(Geometry geometry) {
		return null;
	}
	
	public void write(Geometry geometry, OutStream output) {
		
	}
	
	public String write(GeoJsonObject object) {
		return null;
	}
	
	public void write(GeoJsonObject object, OutStream output) {
		
	}
}
