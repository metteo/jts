package com.vividsolutions.jtstest.function;

import java.util.List;

import com.vividsolutions.jts.geom.*;

public class FunctionsUtil {

	public static final Envelope DEFAULT_ENVELOPE = new Envelope(0, 100, 0, 100);
	
	public static Envelope getEnvelopeOrDefault(Geometry g)
	{
		if (g == null) return DEFAULT_ENVELOPE;
		return g.getEnvelopeInternal();
	}
	
	public static GeometryFactory getFactoryOrDefault(Geometry g)
	{
		if (g == null) return GeometryFactoryHolder.getInstance().getGeometryFactory();
		return g.getFactory();
	}
  
  public static Geometry buildGeometry(List geoms, Geometry parentGeom)
  {
    if (geoms.size() <= 0)
      return null;
    if (geoms.size() == 1) 
      return (Geometry) geoms.get(0);
    // if parent was a GC, ensure returning a GC
    if (parentGeom.getGeometryType().equals("GeometryCollection"))
      return parentGeom.getFactory().createGeometryCollection(GeometryFactory.toGeometryArray(geoms));
    // otherwise return MultiGeom
    return parentGeom.getFactory().buildGeometry(geoms);
  }
  

}
