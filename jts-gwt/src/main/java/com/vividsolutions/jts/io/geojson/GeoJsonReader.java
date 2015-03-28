package com.vividsolutions.jts.io.geojson;

import static com.vividsolutions.jts.io.geojson.GeoJsonUtils.decode;
import static com.vividsolutions.jts.io.geojson.GeoJsonUtils.decodeGeometry;

import java.util.List;

import com.google.web.bindery.autobean.shared.Splittable;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

public class GeoJsonReader {

	private GeometryFactory mFactory;

	public GeoJsonReader() {
		this(new GeometryFactory());
	}

	public GeoJsonReader(GeometryFactory gf) {
		mFactory = gf;
	}

	public Geometry read(String json) throws ParseException {
		Splittable data = decode(json);

		return readGeometry(data);
	}

	public Geometry readFeature(String json) throws ParseException {
		Splittable data = decode(json);

		return readFeature(data);
	}

	public List<Geometry> readFeatureCollection(String json)
			throws ParseException {
		Splittable data = decode(json);

		return readFeatureCollection(data);
	}

	private Geometry readGeometry(Splittable data) throws ParseException {
		GeoJsonGeometry g = decodeGeometry(data);
		String type = g.getType();

		if (GeoJsonGeometry.TYPE_POINT.equals(type)) {
			return readPoint(g);
		} else if (GeoJsonGeometry.TYPE_LINE_STRING.equals(type)) {

		} else {
			throw new ParseException("Unknown geometry type: " + type);
		}

		return null;
	}

	private Geometry readPoint(GeoJsonGeometry g) throws ParseException {
		Splittable coords = g.getCoordinates();
		if(coords == null || !coords.isIndexed() || coords.size() < 2) {
			throw new ParseException("Broken coordinates array");
		}
		double x = coords.get(0).asNumber();
		double y = coords.get(1).asNumber();
		
		Coordinate c;
		if(coords.size() == 3) {
			double z = coords.get(2).asNumber();
			c = new Coordinate(x, y, z);
		} else {
			c = new Coordinate(x, y);
		}
		
		Point p = mFactory.createPoint(c);
		return p;
	}

	private Geometry readFeature(Splittable data) throws ParseException {
		return null;
	}

	private List<Geometry> readFeatureCollection(Splittable data)
			throws ParseException {
		return null;
	}

}