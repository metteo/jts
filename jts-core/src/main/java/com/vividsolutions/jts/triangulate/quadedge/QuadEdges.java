package com.vividsolutions.jts.triangulate.quadedge;

public class QuadEdges {

	public static QuadEdge[] inNewArray(QuadEdge[] src) {
		QuadEdge[] result = new QuadEdge[src.length];
		
		for(int i = 0; i < result.length; i++) {
			result[i] = src[i];
		}
		
		return result;
	}
}
