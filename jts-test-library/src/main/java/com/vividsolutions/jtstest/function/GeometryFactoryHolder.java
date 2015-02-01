package com.vividsolutions.jtstest.function;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class GeometryFactoryHolder {
	
	private static GeometryFactoryHolder sInstance = new GeometryFactoryHolder();
	
	public static GeometryFactoryHolder getInstance() {
		return sInstance;
	}
	
	private GeometryFactory mGeometryFactory;
	private PrecisionModel mPrecisionModel = new PrecisionModel();
	
	private GeometryFactoryHolder() {
		
	}
	
	public GeometryFactory getGeometryFactory() {
		if(mGeometryFactory == null) {
			mGeometryFactory = new GeometryFactory(getPrecisionModel());
		}
		
		return mGeometryFactory;
	}
	
	public PrecisionModel getPrecisionModel() {
		return mPrecisionModel;
	}
	
	public void setPrecisionModel(PrecisionModel pm) {
		mPrecisionModel = pm;
		
		mGeometryFactory = null;
	}
}
