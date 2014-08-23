/*
 * The JTS Topology Suite is a collection of Java classes that
 * implement the fundamental operations required to validate a given
 * geo-spatial data set to a known topological specification.
 *
 * Copyright (C) 2001 Vivid Solutions
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * For more information, contact:
 *
 *     Vivid Solutions
 *     Suite #1A
 *     2328 Government Street
 *     Victoria BC  V8T 5G5
 *     Canada
 *
 *     (250)385-6040
 *     www.vividsolutions.com
 */
package com.vividsolutions.jts.io.gml2;

import java.io.IOException;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.xml.sax.SAXException;

import test.jts.TestFiles;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;

public class GMLReaderTestCase extends TestCase 
{
	
	public GMLReaderTestCase(String name) {
		super(name);
	}
	
	protected static PrecisionModel precisionModel = new PrecisionModel(1000);
	protected static GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
	
	public void testPointRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "points.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			Point p = (Point) gc.getGeometryN(i);
			assertNotNull(p);
		}
	}



	public void testLineStringRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "linestrings.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			LineString ls = (LineString) gc.getGeometryN(i);
			assertNotNull(ls);
		}
	}

	public void testPolygonRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "polygons.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			Polygon p = (Polygon) gc.getGeometryN(i);
			assertNotNull(p);
		}
	}
	
	public void testMultiPointRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "multipoints.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			MultiPoint p = (MultiPoint) gc.getGeometryN(i);
			assertNotNull(p);
		}
	}

	public void testMultiLineStringRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "multilinestrings.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			MultiLineString ls = (MultiLineString) gc.getGeometryN(i);
			assertNotNull(ls);
		}
	}

	public void testMultiPolygonRead() throws SAXException, IOException, ParserConfigurationException{
		Reader fr = TestFiles.getReader(TestFiles.GML_DIR + "multipolygons.xml");
		
		GMLReader gr = new GMLReader();
		Geometry g = gr.read(fr,geometryFactory);
		
		GeometryCollection gc = (GeometryCollection)g;
		assertTrue(gc.getNumGeometries() == 25);
		
		for(int i=0;i<25;i++){
			MultiPolygon p = (MultiPolygon) gc.getGeometryN(i);
			assertNotNull(p);
		}
	}

}
