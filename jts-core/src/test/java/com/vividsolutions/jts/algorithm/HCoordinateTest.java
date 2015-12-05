package com.vividsolutions.jts.algorithm;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

import static org.junit.Assert.*;

public class HCoordinateTest {

	@Test
	public void testDefaultConstructor() {
		HCoordinate hc = new HCoordinate();
		
		assertEquals(0, hc.x, 0);
		assertEquals(0, hc.y, 0);
		assertEquals(1, hc.w, 0);
	}
	
	@Test
	public void testSingleCoordConstructor() {
		Coordinate c = new Coordinate(3, 2, 1);
		HCoordinate hc = new HCoordinate(c);
		
		assertEquals(3, hc.x, 0);
		assertEquals(2, hc.y, 0);
		assertEquals(1, hc.w, 0);
	}
	
	@Test
	public void testIntersection() throws NotRepresentableException {
		//y = 2/5 * x + 3/5
		Coordinate p1 = new Coordinate(1,1);
		Coordinate p2 = new Coordinate(6,3);
		
		//y = 6 - x
		Coordinate q1 = new Coordinate(1,5);
		Coordinate q2 = new Coordinate(4,2);
		
		double expX = 27.0/7.0;
		double expY = 15.0/7.0;
		
		Coordinate c = HCoordinate.intersection(p1, p2, q1, q2);
		
		//result from wolfram alfa
		assertEquals(expX, c.x, 0.000000001);
		assertEquals(expY, c.y, 0.000000001);
		
		HCoordinate hc = new HCoordinate(p1, p2, q1, q2);
		Coordinate hcc = hc.getCoordinate();
		
		assertEquals(expX, hcc.x, 0.000000001);
		assertEquals(expY, hcc.y, 0.000000001);
	}
	
	@Test
	public void testIntersectionOutsideOfBbox() throws NotRepresentableException {
		//y = 2/5 * x + 3/5
		Coordinate p1 = new Coordinate(1,1);
		Coordinate p2 = new Coordinate(6,3);
		
		//y = 6 - x
		Coordinate q1 = new Coordinate(1,5);
		Coordinate q2 = new Coordinate(2,4);
		
		//Intersection point is outside of the bbox of second line
		Coordinate c = HCoordinate.intersection(p1, p2, q1, q2);
		
		//result from wolfram alfa
		assertEquals(27.0/7.0, c.x, 0.000000001);
		assertEquals(15.0/7.0, c.y, 0.000000001);
	}
	
	@Test(expected = NotRepresentableException.class)
	public void testNoIntersection() throws NotRepresentableException {
		//y = 2
		Coordinate p1 = new Coordinate(1,2);
		Coordinate p2 = new Coordinate(6,2);
		
		//y = 1
		Coordinate q1 = new Coordinate(1,1);
		Coordinate q2 = new Coordinate(2,1);
		
		//parallel lines, exception expected
		HCoordinate.intersection(p1, p2, q1, q2);
	}
}
