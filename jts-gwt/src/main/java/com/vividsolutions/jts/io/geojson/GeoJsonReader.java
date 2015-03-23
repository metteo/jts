package com.vividsolutions.jts.io.geojson;

import java.util.List;

import static com.vividsolutions.jts.io.geojson.GeoJsonUtils.*;

import com.google.web.bindery.autobean.shared.Splittable;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class GeoJsonReader {
	
	private GeometryFactory mFactory;

	public GeoJsonReader() {
		this(new GeometryFactory());
	}
	
	public GeoJsonReader(GeometryFactory gf) {
		mFactory = gf;
	}
	
	public Geometry read(String json) {
		Splittable data = decode(json);
		
		String type = data.get("type").asString();
		
		if("Point".equals(type)) {
			GeoJsonGeometry g = decodeGeometry(data);
			Splittable coords = g.getCoordinates();
			double x = coords.get(0).asNumber();
			double y = coords.get(1).asNumber();
			Coordinate c = new Coordinate(x, y);
			Point p = mFactory.createPoint(c);
			return p;
		} else if("LineString".equals(type)) {
			
		} else {
			
		}
		
		return null;
	}
	
	public List<Geometry> readCollection(String json) {
		return null;
	}
}
