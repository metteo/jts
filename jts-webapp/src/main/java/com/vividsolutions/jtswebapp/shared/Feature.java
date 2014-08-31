package com.vividsolutions.jtswebapp.shared;

import java.io.Serializable;
import java.util.HashMap;

import com.vividsolutions.jts.geom.Geometry;

public class Feature implements Serializable {

	private static final long serialVersionUID = -1011284887546671990L;
	
	public String fid;
	public Geometry geometry;
	public HashMap<String, String> properties;
	
	@Override
	public String toString() {
		return "Feature [fid=" + fid + ", geometry=" + geometry
				+ ", properties=" + properties + "]";
	}
}
