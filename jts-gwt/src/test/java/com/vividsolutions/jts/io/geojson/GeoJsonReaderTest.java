package com.vividsolutions.jts.io.geojson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

public class GeoJsonReaderTest {
	
	private static GeoJsonReader mReader;

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		GeoJsonUtils.setFactory(AutoBeanFactorySource.create(GeoJsonFactory.class));
		mReader = new GeoJsonReader();
	}

	@Test
	public void testRead() {
		Geometry g = mReader.read("{\"type\":\"Point\", \"coordinates\":[10, 20]}");

		if(g instanceof Point) {
			Point p = (Point) g;
			
			assertEquals(10, p.getX(), 0);
			assertEquals(20, p.getY(), 0);
		} else {
			fail("not Point");
		}
	}

}
