package com.vividsolutions.jtsexample.shared;

import java.io.Serializable;
import java.util.Map;

import com.vividsolutions.jts.geom.Coordinate;

public class Feature implements Serializable {

	private static final long serialVersionUID = -1011284887546671990L;
	
	public String fid;
	public Coordinate geometry;
	public Map<String, String> properties;
	
	@Override
	public String toString() {
		return "Feature [fid=" + fid + ", geometry=" + geometry
				+ ", properties=" + properties + "]";
	}
}
