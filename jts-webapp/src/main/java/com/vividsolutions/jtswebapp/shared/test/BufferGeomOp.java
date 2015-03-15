package com.vividsolutions.jtswebapp.shared.test;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.operation.buffer.BufferOp;

public class BufferGeomOp implements GeomOp {

	@Override
	public Object invoke(Object[] args) throws Exception {
		BufferOp op = new BufferOp((Geometry) args[0]);
		double distance = Double.parseDouble((String)args[1]);
		return op.getResultGeometry(distance);
	}
	
	@Override
	public String toString() {
		return "buffer";
	}

}
