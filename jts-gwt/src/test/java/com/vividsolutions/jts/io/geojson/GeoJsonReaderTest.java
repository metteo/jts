package com.vividsolutions.jts.io.geojson;

import static com.vividsolutions.jts.io.geojson.GeoJsonTestUtils.readGeoJsonFile;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;

public class GeoJsonReaderTest {

	private static GeoJsonReader sReader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GeoJsonUtils.setFactory(AutoBeanFactorySource
				.create(GeoJsonFactory.class));
		sReader = new GeoJsonReader();
	}

	@Test
	public void itShouldReadPoint() throws IOException, ParseException {
		String s = readGeoJsonFile("point1");
		Geometry g = sReader.read(s);

		Point p = (Point) g;
		assertEquals(10, p.getX(), 0);
		assertEquals(20, p.getY(), 0);
		assertEquals(Double.NaN, p.getZ(), 0);
		
		String s2 = readGeoJsonFile("point2");
		g = sReader.read(s2);
		
		p = (Point) g;
		assertEquals(10, p.getX(), 0);
		assertEquals(20, p.getY(), 0);
		assertEquals(30, p.getZ(), 0);
	}
	
	@Test(expected = ParseException.class)
	public void itShouldReadEmptyCoords() throws IOException, ParseException {
		String s = readGeoJsonFile("point3");
		sReader.read(s);
	}
}
