package com.vividsolutions.jts.geom;

import junit.framework.TestCase;

public class CoordinateTest extends TestCase
{
  
  public void testConstructor3D() 
  {
    Coordinate c = new Coordinate(350.2, 4566.8, 5266.3);
    assertEquals(c.x, 350.2);
    assertEquals(c.y, 4566.8);
    assertEquals(c.z, 5266.3);
  }
  
  public void testConstructor2D() 
  {
    Coordinate c = new Coordinate(350.2, 4566.8);
    assertEquals(c.x, 350.2);
    assertEquals(c.y, 4566.8);
    assertEquals(c.z, Coordinate.NULL_ORDINATE);
  }
  public void testDefaultConstructor() 
  {
    Coordinate c = new Coordinate();
    assertEquals(c.x, 0.0);
    assertEquals(c.y, 0.0);
    assertEquals(c.z, Coordinate.NULL_ORDINATE);
  }
  public void testCopyConstructor3D() 
  {
    Coordinate orig = new Coordinate(350.2, 4566.8, 5266.3);
    Coordinate c = new Coordinate(orig);
    assertEquals(c.x, 350.2);
    assertEquals(c.y, 4566.8);
    assertEquals(c.z, 5266.3);
  }
  public void testSetCoordinate() 
  {
    Coordinate orig = new Coordinate(350.2, 4566.8, 5266.3);
    Coordinate c = new Coordinate();
    c.setCoordinate(orig);
    assertEquals(c.x, 350.2);
    assertEquals(c.y, 4566.8);
    assertEquals(c.z, 5266.3);
  }
  public void testGetOrdinate() 
  {
    Coordinate c = new Coordinate(350.2, 4566.8, 5266.3);
    assertEquals(c.getOrdinate(Coordinate.X), 350.2);
    assertEquals(c.getOrdinate(Coordinate.Y), 4566.8);
    assertEquals(c.getOrdinate(Coordinate.Z), 5266.3);
  }
  public void testSetOrdinate() 
  {
    Coordinate c = new Coordinate();
    c.setOrdinate(Coordinate.X, 111);
    c.setOrdinate(Coordinate.Y, 222);
    c.setOrdinate(Coordinate.Z, 333);
    assertEquals(c.getOrdinate(Coordinate.X), 111.0);
    assertEquals(c.getOrdinate(Coordinate.Y), 222.0);
    assertEquals(c.getOrdinate(Coordinate.Z), 333.0);
  }
  public void testEquals()
  {
    Coordinate c1 = new Coordinate(1,2,3);
    String s = "Not a coordinate";
    assertFalse(c1.equals(s));
    
    Coordinate c2 = new Coordinate(1,2,3);
    assertTrue(c1.equals2D(c2));

    Coordinate c3 = new Coordinate(1,22,3);
    assertFalse(c1.equals2D(c3));
    
    Coordinate c4 = new Coordinate(-1,2,3);
    assertFalse(c1.equals2D(c4));
    
    Coordinate c5 = new Coordinate(1,2,4);
    assertTrue(c1.equals2D(c5));
  }
  public void testEquals2D()
  {
    Coordinate c1 = new Coordinate(1,2,3);
    Coordinate c2 = new Coordinate(1,2,3);
    assertTrue(c1.equals2D(c2));
    
    Coordinate c3 = new Coordinate(1,22,3);
    assertFalse(c1.equals2D(c3));
    
    Coordinate c4 = new Coordinate(1,22);
    assertTrue(c3.equals2D(c4));
  }
  public void testEquals3D()
  {
    Coordinate c1 = new Coordinate(1,2,3);
    Coordinate c2 = new Coordinate(1,2,3);
    assertTrue(c1.equals3D(c2));
    
    Coordinate c3 = new Coordinate(1,22,3);
    assertFalse(c1.equals3D(c3));
    
    Coordinate c4 = new Coordinate(1,2,33);
    assertFalse(c1.equals3D(c4));
  }
  public void testEquals2DWithinTolerance() 
  {
    Coordinate c = new Coordinate(100.0, 200.0, 50.0);
    Coordinate aBitOff = new Coordinate(100.1, 200.1, 50.0);
    assertTrue(c.equals2D(aBitOff, 0.2));
    
    Coordinate c1 = new Coordinate(100.0, 200.0, -50.0);
    Coordinate aBitOff1 = new Coordinate(100.1, 200.1);
    assertTrue(c1.equals2D(aBitOff1, 0.2));
  }

  public void testEqualInZ() {
    
    Coordinate c = new Coordinate(100.0, 200.0, 50.0);
    Coordinate withSameZ = new Coordinate(100.1, 200.1, 50.1);
    assertTrue(c.equalInZ(withSameZ, 0.2));
    assertFalse(c.equalInZ(withSameZ, 0.01));
  }

  public void testCompareTo() 
  {
    Coordinate lowest = new Coordinate(10.0, 100.0, 50.0);
    Coordinate highest = new Coordinate(20.0, 100.0, 50.0);
    Coordinate equalToHighest = new Coordinate(20.0, 100.0, 50.0);
    Coordinate higherStill = new Coordinate(20.0, 200.0, 50.0);
    
    assertEquals(-1, lowest.compareTo(highest));
    assertEquals(1, highest.compareTo(lowest));
    assertEquals(-1, highest.compareTo(higherStill));
    assertEquals(0, highest.compareTo(equalToHighest));
  }
  public void testToString() 
  {
    String expectedResult = "(100.0, 200.0, 50.0)";
    String actualResult = new Coordinate(100.0, 200.0, 50.0).toString();
    assertEquals(expectedResult, actualResult);
    
    expectedResult = "(-100.1, -200.1, NaN)";
    actualResult = new Coordinate(-100.1, -200.1).toString();
    assertEquals(expectedResult, actualResult);
  }

  @SuppressWarnings("deprecation")
  public void testClone() {
    Coordinate c = new Coordinate(100.0, 200.0, 50.0);
    Coordinate clone = (Coordinate) c.clone();
    assertTrue(c.equals3D(clone));
  }
  
  public void testCopy() {
    Coordinate c = new Coordinate(100.0, 200.0, 50.0);
    Coordinate copy = c.copy();
    assertTrue(c.equals3D(copy));
  }

  public void testDistance() {
    Coordinate coord1 = new Coordinate(0.0, 0.0, 0.0);
    Coordinate coord2 = new Coordinate(100.0, 200.0, 50.0);
    double distance = coord1.distance(coord2);
    assertEquals(distance, 223.60679774997897, 0.00001);
  }
  public void testDistance3D() {
    Coordinate coord1 = new Coordinate(0.0, 0.0, 0.0);
    Coordinate coord2 = new Coordinate(100.0, 200.0, 50.0);
    double distance = coord1.distance3D(coord2);
    assertEquals(distance, 229.128784747792, 0.000001);
  }


}
