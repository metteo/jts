package com.vividsolutions.jts.io.geojson;

import static com.vividsolutions.jts.io.geojson.GeoJsonTestUtils.readGeoJsonFile;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeoJsonReaderTest {

	private static GeoJsonReader sReader;
	private static WKTReader sWkt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GeoJsonUtils.setFactory(AutoBeanFactorySource
				.create(GeoJsonFactory.class));
		sReader = new GeoJsonReader();
		sWkt = new WKTReader(sReader.getGeometryFactory());
	}

	@Test
	public void itShouldReadPoint() throws IOException, ParseException {
		
		Point p = (Point) sReader.read(readGeoJsonFile("point1"));
		assertEquals(10, p.getX(), 0);
		assertEquals(20, p.getY(), 0);
		assertEquals(Double.NaN, p.getZ(), 0);
		
		p = (Point) sReader.read(readGeoJsonFile("point2"));
		assertEquals(10, p.getX(), 0);
		assertEquals(20, p.getY(), 0);
		assertEquals(30, p.getZ(), 0);
	}
	
	@Test(expected = ParseException.class)
	public void itShouldReadEmptyCoords() throws IOException, ParseException {
		sReader.read(readGeoJsonFile("point3"));
	}
	
	@Test
	public void itShouldReadLineString() throws IOException, ParseException {
		LineString actual = (LineString) sReader.read(readGeoJsonFile("line1"));
		LineString expected = (LineString) sWkt.read("LINESTRING (10 20, 30 40, 50 60)");
		
		assertEquals(expected, actual);
		
		actual = (LineString) sReader.read(readGeoJsonFile("line2"));
		expected = (LineString) sWkt.read("LINESTRING (10 20 30, 30 40 50, 50 60 70)");
		
		assertEquals(expected, actual);
		
		actual = (LineString) sReader.read(readGeoJsonFile("line3"));
		expected = (LineString) sWkt.read("LINESTRING EMPTY");
		
		assertEquals(expected, actual);
	}
}
