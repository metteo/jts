
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
package com.vividsolutions.jts.geom.util;

import java.util.*;
import com.vividsolutions.jts.geom.*;

/**
 * Extracts the components of a given type from a {@link Geometry}.
 *
 * @version 1.7
 */
public class GeometryExtracter
  implements GeometryFilter
{
	
	protected static boolean isOfClass(Object o, Class clz)
	{
		
//#if REFLECT
		
		return clz.isAssignableFrom(o.getClass());
		
//#else
		
		//$return isOfClassNoReflect((Geometry) o, clz);
		
//#endif
		
	}
	
	private static Map<Class<?>, List<Class<?>>> mGeomClassMap;
	
	private static void initGeomClassMap() {
		if(mGeomClassMap != null){
			return;
		}
		
		mGeomClassMap = new HashMap<Class<?>, List<Class<?>>>();
		
		mGeomClassMap.put(Point.class, 
				Arrays.asList(new Class<?>[]{Point.class}));
		
		mGeomClassMap.put(LineString.class, 
				Arrays.asList(new Class<?>[]{LineString.class, LinearRing.class}));
		
		mGeomClassMap.put(Polygon.class, 
				Arrays.asList(new Class<?>[]{Polygon.class}));
		
		mGeomClassMap.put(GeometryCollection.class, 
				Arrays.asList(new Class<?>[]{MultiPoint.class, MultiLineString.class, 
						MultiPolygon.class, GeometryCollection.class}));
	}
	
	@SuppressWarnings("unused")
	private static boolean isOfClassNoReflect(Geometry g, Class<?> clazz) {
		initGeomClassMap();
		
		return mGeomClassMap.get(clazz).contains(g.getClass());
	}
	
  /**
   * Extracts the components of type <tt>clz</tt> from a {@link Geometry}
   * and adds them to the provided {@link List}.
   * 
   * @param geom the geometry from which to extract
   * @param list the list to add the extracted elements to
   */
  public static List extract(Geometry geom, Class clz, List list)
  {
  	if (isOfClass(geom, clz)) {
  		list.add(geom);
  	}
  	else if (geom instanceof GeometryCollection) {
  		geom.apply(new GeometryExtracter(clz, list));
  	}
  	// skip non-LineString elemental geometries
  	
    return list;
  }

  /**
   * Extracts the components of type <tt>clz</tt> from a {@link Geometry}
   * and returns them in a {@link List}.
   * 
   * @param geom the geometry from which to extract
   */
  public static List extract(Geometry geom, Class clz)
  {
    return extract(geom, clz, new ArrayList());
  }

  private Class clz;
  private List comps;
  
  /**
   * Constructs a filter with a list in which to store the elements found.
   * 
   * @param clz the class of the components to extract (null means all types)
   * @param comps the list to extract into
   */
  public GeometryExtracter(Class clz, List comps)
  {
  	this.clz = clz;
    this.comps = comps;
  }

  public void filter(Geometry geom)
  {
    if (clz == null || isOfClass(geom, clz)) comps.add(geom);
  }

}
