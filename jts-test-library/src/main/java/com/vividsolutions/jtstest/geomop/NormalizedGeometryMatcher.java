package com.vividsolutions.jtstest.geomop;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jtstest.testrunner.GeometryResult;

public class NormalizedGeometryMatcher
implements GeometryMatcher
{
	private double tolerance;
	
	public NormalizedGeometryMatcher()
	{
		
	}
	
	public void setTolerance(double tolerance)
	{
		this.tolerance = tolerance;
	}
	
	public boolean match(Geometry a, Geometry b)
	{
    Geometry aClone = a.copy();
    Geometry bClone = b.copy();
    aClone.normalize();
    bClone.normalize();
    return aClone.equalsExact(bClone, tolerance);
	}

}
